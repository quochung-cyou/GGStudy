package web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import web.common.dto.*;
import web.common.exception.NotFoundException;
import web.common.shared.ContentType;
import web.common.shared.EffectType;
import web.common.shared.SlideType;
import web.common.utils.PageableUtils;
import web.dao.repository.FieldStyleRepository;
import web.dao.repository.ProjectRepository;
import web.dao.repository.TemplateRepository;
import web.dao.spec.ProjectSpecification;
import web.mapper.ProjectMapper;
import web.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static web.common.shared.Constants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    public static final String BASIC_PROMPT_PATH = "classpath:data/prompt.txt";
    public static final String OUTLINE_PROMPT_PATH = "classpath:data/outlineprompt.txt";
    public static final String CHAT_PROMPT_PATH = "classpath:data/chatprompt.txt";

    private final ProjectRepository projectRepository;
    private final TemplateRepository templateRepository;
    private final FieldStyleRepository fieldStyleRepository;

    private final ProjectMapper projectMapper;
    private final int scores = Runtime.getRuntime().availableProcessors();
    public final ExecutorService executorService = Executors.newFixedThreadPool(scores*2);

    private final ObjectMapper objectMapper;
    private final ResourceLoader loader;
    private final Random random;

    private final GeminiClient geminiClient;
    private final ImageService imageService;

    @Override
    public Page<ProjectDTO> findAll(int size, int page, String sortBy, String currentUser) {
        Pageable pageable = PageableUtils.createPageable(size, page, sortBy);
        Specification<Project> specification = ProjectSpecification.fromCreatedBy(currentUser);
        Page<Project> projectList = projectRepository.findAll(specification, pageable);
        return projectList.map(projectMapper::toDTO);
    }

    @Override
    public Project findById(String id) {
        var project = projectRepository.findById(id);
        if (project.isEmpty()) throw new NotFoundException("Project not found with the given ID.");
        var projectGet = project.get();
        var slides = projectGet.getSlides();
        for (Slide slide : slides) {
            var elements = slide.getElements();
            List<String> elementRootTemplateIds = elements.stream().map(Element::getRootElementTemplateId).collect(Collectors.toList());
            List<FieldStyle> fieldStyles = fieldStyleRepository.findByEntityIdInAndEntityType(elementRootTemplateIds, "ELEMENT");
            Map<String, List<FieldStyle>> mapFieldStyle = fieldStyles.stream().collect(Collectors.groupingBy(FieldStyle::getEntityId));
            for (Element element : elements) {
                List<FieldStyle> fieldStyle = mapFieldStyle.get(element.getRootElementTemplateId());
                if (fieldStyle != null) {
                    element.setFieldStyles(fieldStyle);
                }
            }
        }
        return projectGet;
    }


    @Override
    public Project createProjectsFromOutlines(String topicName, List<Outline> outlines){
        Instant allStart = Instant.now();
        Project theProject = new Project();
        if(topicName != null) theProject.setTitle(topicName);
        List<CompletableFuture<List<Slide>>> futures = new ArrayList<>();
        Instant start = Instant.now();
        for (Outline outline : outlines) {
            Mono<List<Slide>> mono = Mono.fromCallable(() -> {
                try {
                    return createProjectsFromGemini(outline.getTitle(), outline.getDescription(), theProject);
                } catch (Exception e) {
                    log.error("Error creating slides from outline: {}", e.getMessage(), e);
                    return new ArrayList<Slide>();
                }
            }).subscribeOn(Schedulers.fromExecutor(executorService));
            futures.add(mono.toFuture());
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        List<List<Slide>> listSlides = new ArrayList<>();
        CompletableFuture<List<List<Slide>>> allImagesFuture = allOf.thenApply(v -> futures.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        log.error("Error creating slides from outline: {}", e.getMessage(), e);
                        return new ArrayList<Slide>();
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        allImagesFuture.thenAccept(listSlides::addAll).join();
        log.info("Time taken to get slides from Gemini: {}", Instant.now().toEpochMilli() - start.toEpochMilli());
        for (List<Slide> slides : listSlides) {
            theProject.getSlides().addAll(slides);
        }
        start = Instant.now();
        imageService.setUrlImageElement(theProject);
        log.info("Time taken to get image links: {}", Instant.now().toEpochMilli() - start.toEpochMilli());
        projectRepository.save(theProject);
        log.info("project saved successfully");
        log.info("Time taken for the whole process: {}", Instant.now().toEpochMilli() - allStart.toEpochMilli());
        return theProject;
    }

    @Override
    public List<OutlineResponse> createProjectOutlines(String topicName) throws IOException {
        String prompt = getPromptFromFile(OUTLINE_PROMPT_PATH);
        prompt = prompt.replace("{{topic_name}}", topicName);
        String response = geminiClient.getDataFromPrompt(prompt);
        log.info(response);
        var outlineInputFormat = objectMapper.readValue(formatString(response), OutlineInputFormat.class);
        return outlineInputFormat.getOutlines();
    }

    public List<Slide> createProjectsFromGemini(String topicName, String additionalInfo, Project theProject) throws IOException {
        String prompt = constructTopicPrompt(topicName, additionalInfo);
        String response = geminiClient.getDataFromPrompt(prompt);
        log.info("Response from Gemini: {}", response);
        var projectInputFormat = objectMapper.readValue(formatString(response), ProjectInputFormat.class);
        return extractSlide(projectInputFormat, theProject);
    }

    @Override
    public String getAnswerFromGemini(List<String> history, String question) throws IOException {
        String prompt = getPromptFromFile(CHAT_PROMPT_PATH);
        StringBuilder joinOfHistoryList = new StringBuilder();
        for(String chat : history){
            joinOfHistoryList.append(chat).append("\n");
        }
        prompt = prompt.replace("{{history}}", joinOfHistoryList.toString());
        prompt = prompt.replace("{{question}}", question);
        log.info(prompt);
        return formatString(geminiClient.getDataFromPrompt(prompt));
    }

    @NotNull
    private String constructTopicPrompt(String topicName, String additionalInfo) throws IOException {
        String prompt = getPromptFromFile(BASIC_PROMPT_PATH);
        prompt = prompt.replace("{{topic_name}}", topicName);
        prompt = prompt.replace("{{additional_info}}", additionalInfo);
        return prompt;
    }

    @NotNull
    private String getPromptFromFile(String path) throws IOException {
        Resource resource = loader.getResource(path);
        InputStream input = resource.getInputStream();
        byte[] buffer = FileCopyUtils.copyToByteArray(input);
        return new String(buffer, StandardCharsets.UTF_8);
    }

    private List<Slide> extractSlide(ProjectInputFormat projectInputFormat, Project theProject) {
        List<Slide> listSlides = new ArrayList<>();
        for (SlideInputFormat geminiSlide : projectInputFormat.getSlides()) {
            Slide theSlide = new Slide();
            theSlide.setTopicName(geminiSlide.getTopicName());
            theSlide.setHeadingTitle(geminiSlide.getHeadingTitle());

            switch (SlideType.valueOf(geminiSlide.getSlideType())) {
                case TEXT_ONLY:
                    textOnlyTemplateProcess(theSlide, geminiSlide);
                    break;
                case ONE_IMAGE_AND_TEXT:
                    oneImageAndTextTemplateProcess(theSlide, geminiSlide);
                    break;
                case TWO_IMAGES_AND_TEXT:
                    twoImagesAndTextTemplateProcess(theSlide, geminiSlide);
                    break;
                case TWO_IMAGES_AND_TWO_TEXTS:
                    twoImagesAndTwoTextsTemplateProcess(theSlide, geminiSlide);
                    break;
                default:
                    throw new NotFoundException("Slide template not found - " + geminiSlide.getSlideType());
            }
            theSlide.setProject(theProject);
            listSlides.add(theSlide);
        }
        return listSlides;
    }

    private String formatString(String jsonString) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode text = rootNode.path("candidates").get(0).path("content").path("parts").get(0);
        return text.path("text").asText();
    }

    @Override
    public Project save(Project theProject) {
        return projectRepository.save(theProject);
    }

    @Override
    public void deleteById(String id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) throw new NotFoundException("Project not found with the given ID.");
        projectRepository.delete(project.get());
    }

    private void textOnlyTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        List<Template> templateList = templateRepository.findByTemplateType(TEXT_ONLY);
        Template onlyTextTemplate = templateList.get(random.nextInt(templateList.size()));
        for (Element templateElement : onlyTextTemplate.getElements()) {
            Element newSlideElement = extractTemplateElement(templateElement);
            newSlideElement.setContent(geminiSlide.getSlideTopicName());
            newSlideElement.setSlideId(theSlide.getId());
            newSlideElement.setRootElementTemplateId(templateElement.getId());
            theSlide.getElements().add(newSlideElement);
            extractAnimationEffect(theSlide, geminiSlide, templateElement);
        }
        theSlide.setHeadingTitle(geminiSlide.getSlideTopicName());
        theSlide.setTemplate(onlyTextTemplate);
    }

    private void oneImageAndTextTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        List<Template> templateList = templateRepository.findByTemplateType(ONE_IMAGE_AND_TEXT);
        Template oneImageTemplate = templateList.get(random.nextInt(templateList.size()));
        for (Element templateElement : oneImageTemplate.getElements()) {
            Element newSlideElement = extractTemplateElement(templateElement);
            String elementType = templateElement.getElementType();
            if (elementType.equals(ContentType.TEXT.toString())) {
                newSlideElement.setContent(geminiSlide.getParagraphText());
            } else if (elementType.equals(ContentType.IMAGE.toString())) {
                newSlideElement.setImageUrl(geminiSlide.getSingleImageUrl());
            } else if (elementType.equals(ContentType.HEADING.toString())) {
                newSlideElement.setContent(geminiSlide.getHeadingTitle());
            }
            extractAnimationEffect(theSlide, geminiSlide, templateElement);
            newSlideElement.setRootElementTemplateId(templateElement.getId());
            newSlideElement.setSlideId(theSlide.getId());
            theSlide.getElements().add(newSlideElement);
        }
        theSlide.setTemplate(oneImageTemplate);
    }

    private void twoImagesAndTextTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        List<Template> templateList = templateRepository.findByTemplateType(TWO_IMAGES_AND_TEXT);
        Template twoImagesTemplate = templateList.get(random.nextInt(templateList.size()));
        boolean firstImageIsTaken = false;
        for (int templateElementIndex = 0; templateElementIndex < twoImagesTemplate.getElements().size(); templateElementIndex++) {
            Element templateElement = twoImagesTemplate.getElements().get(templateElementIndex);
            Element newSlideElement = extractTemplateElement(templateElement);
            String elementType = templateElement.getElementType();
            if (elementType.equals(ContentType.TEXT.toString())) {
                newSlideElement.setContent(geminiSlide.getParagraphText());
            } else if (elementType.equals(ContentType.IMAGE.toString())) {
                if (!firstImageIsTaken) {
                    newSlideElement.setImageUrl(geminiSlide.getFirstImageUrl());
                    firstImageIsTaken = true;
                } else {
                    newSlideElement.setImageUrl(geminiSlide.getSecondImageUrl());
                }
            } else if (elementType.equals(ContentType.HEADING.toString())) {
                newSlideElement.setContent(geminiSlide.getHeadingTitle());
            }
            extractAnimationEffect(theSlide, geminiSlide, templateElement);
            newSlideElement.setRootElementTemplateId(templateElement.getId());
            newSlideElement.setSlideId(theSlide.getId());
            theSlide.getElements().add(newSlideElement);
        }
        theSlide.setTemplate(twoImagesTemplate);
    }

    private void twoImagesAndTwoTextsTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        List<Template> templateList = templateRepository.findByTemplateType(TWO_IMAGES_AND_TWO_TEXTS);
        Template twoImagestwoTextsTemplate = templateList.get(random.nextInt(templateList.size()));
        for (int templateElementIndex = 0; templateElementIndex < twoImagestwoTextsTemplate.getElements().size(); templateElementIndex++) {
            Element templateElement = twoImagestwoTextsTemplate.getElements().get(templateElementIndex);
            Element newSlideElement = extractTemplateElement(templateElement);
            String elementType = templateElement.getElementType();
            if (elementType.equals(ContentType.TEXT1.toString())) {
                newSlideElement.setTopicName(geminiSlide.getTopicName());
                newSlideElement.setHeadingTitle(geminiSlide.getFirstImageTitle());
                newSlideElement.setContent(geminiSlide.getFirstImageText());
            } else if (elementType.equals(ContentType.TEXT2.toString())) {
                newSlideElement.setTopicName(geminiSlide.getTopicName());
                newSlideElement.setHeadingTitle(geminiSlide.getSecondImageTitle());
                newSlideElement.setContent(geminiSlide.getSecondImageText());
            } else if (elementType.equals(ContentType.IMAGE1.toString())) {
                newSlideElement.setHeadingTitle(geminiSlide.getFirstImageTitle());
                newSlideElement.setContent(geminiSlide.getFirstImageText());
                newSlideElement.setImageUrl(geminiSlide.getFirstImageUrl());
            } else if (elementType.equals(ContentType.IMAGE2.toString())) {
                newSlideElement.setHeadingTitle(geminiSlide.getSecondImageTitle());
                newSlideElement.setContent(geminiSlide.getSecondImageText());
                newSlideElement.setImageUrl(geminiSlide.getSecondImageUrl());
            }
            extractAnimationEffect(theSlide, geminiSlide, templateElement);
            newSlideElement.setSlideId(theSlide.getId());
            theSlide.getElements().add(newSlideElement);
            theSlide.setTemplate(twoImagestwoTextsTemplate);
        }
    }

    private Element extractTemplateElement(Element templateElement) {
        Element newElement = new Element();
        BeanUtils.copyProperties(templateElement, newElement, "id", "slideId", "templateId");
        return newElement;
    }

    private void extractAnimationEffect(Slide theSlide, SlideInputFormat geminiSlide, Element templateElement) {
        if (geminiSlide.getAnimations() != null) {
            Element newAnimationElement = new Element();
            List<AnimationInputFormat> animationInputFormats = geminiSlide.getAnimations();
            for(AnimationInputFormat animation : animationInputFormats) {
                if (animation.getElementId().equals(templateElement.getElementType())){
                    BeanUtils.copyProperties(templateElement, newAnimationElement, "id", "elementType", "appearOrder", "duration");
                    newAnimationElement.setElementType(animation.getElementType());
                    newAnimationElement.setAppearOrder(animation.getAppearOrder());
                    newAnimationElement.setDuration(animation.getDuration());
                    if (animation.getElementType().equals(EffectType.BOLD.toString())) {
                        newAnimationElement.setElementType(EffectType.BOLD.toString());
                    } else if (animation.getElementType().equals(EffectType.CIRCLE.toString())) {
                        newAnimationElement.setElementType(EffectType.CIRCLE.toString());
                    }
                    newAnimationElement.setSlideId(theSlide.getId());
                    newAnimationElement.setTemplateId(templateElement.getTemplateId());
                    newAnimationElement.setRootElementTemplateId(templateElement.getId());
                    theSlide.getElements().add(newAnimationElement);
                }
            }
        }
    }

}



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
import web.common.dto.ProjectDTO;
import web.common.dto.ProjectInputFormat;
import web.common.dto.SlideInputFormat;
import web.common.exception.NotFoundException;
import web.common.shared.ContentType;
import web.common.shared.SlideType;
import web.common.utils.PageableUtils;
import web.dao.repository.ProjectRepository;
import web.dao.repository.TemplateRepository;
import web.dao.spec.ProjectSpecification;
import web.mapper.ProjectMapper;
import web.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static web.common.shared.Constants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    public static final String BASIC_PROMPT_PATH = "classpath:data/prompt.txt";


    private final ProjectRepository projectRepository;
    private final TemplateRepository templateRepository;

    private final ProjectMapper projectMapper;

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
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found with the given ID."));
    }

    @Override
    public Project createProjectsFromGemini(String topicName, String additionalInfo) throws IOException {
        String prompt = constructTopicPrompt(topicName, additionalInfo);
        Instant start = Instant.now();
        String response = geminiClient.getDataFromPrompt(prompt);
        log.info("Response from Gemini: {}", response);
        log.info("Time taken to get response from Gemini: {}", Instant.now().toEpochMilli() - start.toEpochMilli());
        var projectInputFormat = objectMapper.readValue(formatString(response), ProjectInputFormat.class);

        Project theProject = new Project();
        theProject.setTitle(topicName);
        extractSlide(projectInputFormat, theProject);
        setImageUrlElement(theProject);
        projectRepository.save(theProject);
        return theProject;
    }

    private void setImageUrlElement(Project project) throws JsonProcessingException {
        for (Slide slide : project.getSlides()) {
            for (Element element : slide.getElements()) {
                if (element.getElementType().equals(ContentType.IMAGE.toString())) {
                    Image image = imageService.searchImages(element.getImageUrl());
                    element.setImageUrl(image.getLink());
                }
            }
        }
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

    private void extractSlide(ProjectInputFormat projectInputFormat, Project theProject) {
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
                default:
                    throw new NotFoundException("Slide template not found - " + geminiSlide.getSlideType());
            }
            theSlide.setProject(theProject);
            theProject.getSlides().add(theSlide);
        }
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
            theSlide.getElements().add(newSlideElement);
        }
        theSlide.setHeadingTitle(geminiSlide.getSlideTopicName());
        theSlide.setTemplate(onlyTextTemplate);
    }

    private void oneImageAndTextTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        List<Template> templateList = templateRepository.findByTemplateType(ONE_IMAGE_AND_TEXT);
        Template oneImageTemplate = templateList.get(random.nextInt(templateList.size()));
        for (Element templateElement : oneImageTemplate.getElements()) {
            Element newSlideElement = extractTemplateElement(templateElement);
            if (templateElement.getElementType().equals(ContentType.TEXT.toString())) {
                newSlideElement.setContent(geminiSlide.getParagraphText());
            } else if (templateElement.getElementType().equals(ContentType.IMAGE.toString())) {
                newSlideElement.setImageUrl(geminiSlide.getSingleImageUrl());
            } else if (templateElement.getElementType().equals(ContentType.HEADING.toString())) {
                newSlideElement.setContent(geminiSlide.getHeadingTitle());
            }
            newSlideElement.setSlideId(theSlide.getId());
            theSlide.getElements().add(newSlideElement);
        }
        theSlide.setTemplate(oneImageTemplate);
    }

    private void twoImagesAndTextTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        List<Template> templateList = templateRepository.findByTemplateType(TWO_IMAGES_AND_TEXT);
        Template twoImagesTemplate = templateList.get(random.nextInt(templateList.size()));
        boolean firstImageIsTaken = false;
        for (int templateElementIndex = 0; templateElementIndex < 3; templateElementIndex++) {
            Element templateElement = twoImagesTemplate.getElements().get(templateElementIndex);
            Element newSlideElement = extractTemplateElement(templateElement);
            if (templateElement.getElementType().equals(ContentType.TEXT.toString())) {
                newSlideElement.setContent(geminiSlide.getParagraphText());
            } else if (templateElement.getElementType().equals(ContentType.IMAGE.toString())) {
                if (!firstImageIsTaken) {
                    newSlideElement.setImageUrl(geminiSlide.getFirstImageUrl());
                    firstImageIsTaken = true;
                } else {
                    newSlideElement.setImageUrl(geminiSlide.getSecondImageUrl());
                }
            } else if (templateElement.getElementType().equals(ContentType.HEADING.toString())) {
                newSlideElement.setContent(geminiSlide.getHeadingTitle());
            }
            newSlideElement.setSlideId(theSlide.getId());
            theSlide.getElements().add(newSlideElement);
        }
        theSlide.setTemplate(twoImagesTemplate);
    }

    private Element extractTemplateElement(Element templateElement) {
        Element newElement = new Element();
        BeanUtils.copyProperties(templateElement, newElement, "id", "slideId", "templateId");
        return newElement;
    }
}



package web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import web.dao.ProjectRepository;
import web.dao.TemplateRepository;
import web.dto.CustomResponse;
import web.dto.OutlineResponse;
import web.dto.ProjectDTO;
import web.model.*;

import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static web.utils.Constants.*;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    public static final String BASIC_PROMPT_PATH = "classpath:data/prompt.txt";
    public static final String OUTLINE_PROMPT_PATH = "classpath:data/outlineprompt.txt";

    private enum ContentType {TEXT, IMAGE, TEXT1, TEXT2, IMAGE1, IMAGE2}

    private final ProjectRepository projectRepository;
    private final TemplateRepository templateRepository;
    private final GeminiService geminiService;
    private final ObjectMapper objectMapper;
    private final ResourceLoader loader;
    private final Random random;
    private final ImageService imageService;

    @Override
    public Page<ProjectDTO> findAll(int size, int page, String sortBy) {
        Pageable pageable = PageRequest.of(Math.max(page,0), Math.min(Math.max(size,1), 20), Sort.Direction.ASC, sortBy);
        List<Project> projectList = projectRepository.findAll();
        if(projectList.isEmpty()) throw new NotFoundException("No project found.");
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        for(Project project : projectList) {
            ProjectDTO projectDTO = new ProjectDTO(project);
            projectDTOList.add(projectDTO);
        }
        return new PageImpl<>(projectDTOList, pageable, projectDTOList.size());
    }


    @Override
    public Project findById(String id) {
        return projectRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Project not found with the given ID."));
    }

    @Override
    public List<OutlineResponse> createProjectOutlines(String topicName) throws IOException {
        String prompt = getPromptFromFile(OUTLINE_PROMPT_PATH);
        prompt = prompt.replace("{{topic_name}}", topicName);
        String response = geminiService.getDataFromPrompt(prompt);
        System.out.println(response);
        var outlineInputFormat = objectMapper.readValue(formatString(response), OutlineInputFormat.class);
        return outlineInputFormat.getOutlines();
    }

    @Override
    public Project createProjectsFromGemini(String topicName, String additionalInfo) throws IOException {
        String prompt = constructTopicPrompt(topicName, additionalInfo);
        String response = geminiService.getDataFromPrompt(prompt);
        var projectInputFormat = objectMapper.readValue(formatString(response), ProjectInputFormat.class);

        Project theProject = new Project();
        theProject.setTitle(topicName);
        extractSlide(projectInputFormat, theProject);
        theProject = setImageUrlElement(theProject);
        projectRepository.save(theProject);

        System.out.println("Slides saved successfully");
        return theProject;
    }

    private Project setImageUrlElement(Project project) throws JsonProcessingException {
        for (Slide slide : project.getSlides()) {
            for (Element element : slide.getElements()){
                Image image = imageService.searchImages(element.getHeadingTitle());
                element.setImageUrl(image.getLink());
            }
        }
        return project;
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
            switch (geminiSlide.getSlideType()) {
                case "TEXT_ONLY": {
                    textOnlyTemplateProcess(theSlide, geminiSlide);
                    break;
                }
                case "ONE_IMAGE_AND_TEXT": {
                    oneImageAndTextTemplateProcess(theSlide, geminiSlide);
                    break;
                }
                case "TWO_IMAGES_AND_TEXT": {
                    twoImagesAndTextTemplateProcess(theSlide, geminiSlide);
                    break;
                }
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
        StringBuilder str = new StringBuilder(text.path("text").asText());
        String json = "```json\n";
        str.delete(0, json.length());
        String endJson = "\n```";
        str.delete(str.length() - endJson.length(), str.length());
        return str.toString();
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
            newSlideElement.setTopicName(geminiSlide.getTopicName());
            newSlideElement.setHeadingTitle(geminiSlide.getHeadingTitle());
            newSlideElement.setContent(geminiSlide.getSlideTopicName());
            newSlideElement.setSlideId(theSlide.getId());
            theSlide.getElements().add(newSlideElement);
        }
        theSlide.setTemplate(onlyTextTemplate);
    }

    private void oneImageAndTextTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        List<Template> templateList = templateRepository.findByTemplateType(ONE_IMAGE_AND_TEXT);
        Template oneImageTemplate = templateList.get(random.nextInt(templateList.size()));
        for (Element templateElement : oneImageTemplate.getElements()) {
            Element newSlideElement = extractTemplateElement(templateElement);
            if (templateElement.getElementType().equals(ContentType.TEXT.toString())) {
                newSlideElement.setTopicName(geminiSlide.getTopicName());
                newSlideElement.setHeadingTitle(geminiSlide.getHeadingTitle());
                newSlideElement.setContent(geminiSlide.getParagraphText());
            } else if (templateElement.getElementType().equals(ContentType.IMAGE.toString())) {
                newSlideElement.setHeadingTitle(geminiSlide.getHeadingTitle());
                newSlideElement.setContent(geminiSlide.getParagraphText());
                newSlideElement.setImageUrl(geminiSlide.getSingleImageUrl());
            }
            newSlideElement.setSlideId(theSlide.getId());
            theSlide.setTemplate(oneImageTemplate);
            theSlide.getElements().add(newSlideElement);
        }
    }

    private void twoImagesAndTextTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        List<Template> templateList = templateRepository.findByTemplateType(TWO_IMAGES_AND_TEXT);
        Template twoImagesTemplate = templateList.get(random.nextInt(templateList.size()));
        for (int templateElementIndex = 0; templateElementIndex < 4; templateElementIndex++) {
            Element templateElement = twoImagesTemplate.getElements().get(templateElementIndex);
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
            newSlideElement.setSlideId(theSlide.getId());
            theSlide.setTemplate(twoImagesTemplate);
            theSlide.getElements().add(newSlideElement);
        }
    }

    private Element extractTemplateElement(Element templateElement) {
        Element newElement = new Element();
        BeanUtils.copyProperties(templateElement, newElement, "id", "slideId", "templateId");
        return newElement;
    }
}



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
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import web.common.dto.ProjectInputFormat;
import web.common.dto.SlideInputFormat;
import web.common.exception.NotFoundException;
import web.common.shared.ContentType;
import web.common.shared.SlideType;
import web.common.utils.PageableUtils;
import web.dao.repository.OutlineRepository;
import web.dao.repository.TemplateRepository;
import web.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static web.common.shared.Constants.*;
import static web.common.shared.Constants.TWO_IMAGES_AND_TWO_TEXTS;

@RequiredArgsConstructor
@Slf4j
@Service
public class OutlineServiceImpl implements OutlineService {
    private final OutlineRepository outlineRepository;
    private final ObjectMapper objectMapper;
    private final TemplateRepository templateRepository;
    private final GeminiClient geminiClient;
    public static final String BASIC_PROMPT_PATH = "classpath:data/prompt.txt";
    private final Random random;
    private final ResourceLoader loader;
    private final ImageService imageService;

    @Override
    public Outline createOutlineFromGemini(String topicName, String additionalInfo) throws IOException {
        Instant allStart = Instant.now();
        String prompt = constructTopicPrompt(topicName, additionalInfo);
        Instant start = Instant.now();
        String response = geminiClient.getDataFromPrompt(prompt);
        log.info("Response from Gemini: {}", response);
        log.info("Time taken to get response from Gemini: {}", Instant.now().toEpochMilli() - start.toEpochMilli());
        var projectInputFormat = objectMapper.readValue(formatString(response), ProjectInputFormat.class);

        Outline outline = new Outline();
        extractSlide(projectInputFormat, outline);
        start = Instant.now();
        imageService.setUrlImageElement(outline);
        log.info("Time taken to get image links: {}", Instant.now().toEpochMilli() - start.toEpochMilli());
        log.info("Time taken for the whole process: {}", Instant.now().toEpochMilli() - allStart.toEpochMilli());
        return outline;
    }

    private String formatString(String jsonString) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(jsonString);
        JsonNode text = rootNode.path("candidates").get(0).path("content").path("parts").get(0);
        return text.path("text").asText();
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

    private void extractSlide(ProjectInputFormat projectInputFormat, Outline outline) {
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
            theSlide.setOutline(outline);
            outline.getSlides().add(theSlide);
        }
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

    @Override
    public Page<Outline> findAll(int size, int page, String sortBy) {
        Pageable pageable = PageableUtils.createPageable(size, page, sortBy);
        return outlineRepository.findAll(pageable);
    }

    @Override
    public Outline findById(String id) {
        Optional<Outline> outline = outlineRepository.findById(id);
        if (outline.isEmpty()) throw new NotFoundException("Image not found - " + id);
        return outline.get();
    }

    @Override
    public Outline save(Outline outline) {
        return outlineRepository.save(outline);
    }

    @Override
    public void deleteById(String id) {
        Optional<Outline> outline = outlineRepository.findById(id);
        if (outline.isEmpty()) throw new NotFoundException("Image not found - " + id);
        outlineRepository.delete(outline.get());
    }
}

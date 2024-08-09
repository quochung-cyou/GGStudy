package web.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import web.dao.ProjectRepository;
import web.dao.TemplateRepository;
import web.dto.ProjectDTO;
import web.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static web.utils.Constants.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    private enum ContentType {TEXT, IMAGE}

    ;
    private final ProjectRepository projectRepository;
    private final TemplateRepository templateRepository;
    private final Random random;

    public ProjectServiceImpl(ProjectRepository projectRepository, TemplateRepository templateRepository) {
        this.projectRepository = projectRepository;
        this.templateRepository = templateRepository;
        this.random = new Random();
    }

    @Override
    public Page<ProjectDTO> findAll(int size, int page, String sortBy) {
        Pageable pageable = PageRequest.of(Math.max(page,0), Math.min(Math.max(size,1), 20), Sort.Direction.ASC, sortBy);
        List<Project> projectList = projectRepository.findAll();
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        for(Project project : projectList) {
            ProjectDTO projectDTO = new ProjectDTO(project);
            projectDTOList.add(projectDTO);
        }
        return new PageImpl<>(projectDTOList, pageable, projectDTOList.size());
    }

    @Override
    public Project findById(String id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) throw new RuntimeException("Project not found - " + id);
        return project.get();
    }

    @Override
    public Project createProjectsFromGemini(ProjectInputFormat projectInputFormat) {
        Project theProject = new Project();
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
                    throw new RuntimeException("Slide template not found - " + geminiSlide.getSlideType());
            }
            theSlide.setProject(theProject);
            theProject.getSlides().add(theSlide);
        }
        projectRepository.save(theProject);
        System.out.println("Slides saved successfully");
        return theProject;
    }

    @Override
    public Project save(Project theProject) {
        return projectRepository.save(theProject);
    }

    @Override
    public void deleteById(String id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) throw new RuntimeException("Project not found - " + id);
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
            theSlide.setTemplate(oneImageTemplate);
            theSlide.getElements().add(newSlideElement);
        }
    }

    private void twoImagesAndTextTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        List<Template> templateList = templateRepository.findByTemplateType(TWO_IMAGES_AND_TEXT);
        Template twoImagesTemplate = templateList.get(random.nextInt(templateList.size()));
        boolean firstImageIsTaken = false;
        for (int templateElementIndex = 0; templateElementIndex < 3; templateElementIndex++) {
            Element templateElement = twoImagesTemplate.getElements().get(templateElementIndex);
            Element newSlideElement = extractTemplateElement(templateElement);
            if (templateElement.getElementType().equals(ContentType.TEXT.toString())) {
                newSlideElement.setTopicName(geminiSlide.getTopicName());
                newSlideElement.setHeadingTitle(geminiSlide.getHeadingTitle());
                newSlideElement.setContent(geminiSlide.getParagraphText());
            } else if (templateElement.getElementType().equals(ContentType.IMAGE.toString())) {
                if (!firstImageIsTaken) {
                    newSlideElement.setHeadingTitle(geminiSlide.getFirstImageTitle());
                    newSlideElement.setContent(geminiSlide.getFirstImageText());
                    newSlideElement.setImageUrl(geminiSlide.getFirstImageUrl());
                    firstImageIsTaken = true;
                } else {
                    newSlideElement.setHeadingTitle(geminiSlide.getSecondImageTitle());
                    newSlideElement.setContent(geminiSlide.getSecondImageText());
                    newSlideElement.setImageUrl(geminiSlide.getSecondImageUrl());
                }
            }
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



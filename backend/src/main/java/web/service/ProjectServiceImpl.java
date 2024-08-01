package web.service;

import org.springframework.stereotype.Service;
import web.dao.ProjectRepository;
import web.dao.TemplateRepository;
import web.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static web.utils.Constaints.*;

@Service
public class ProjectServiceImpl implements ProjectService{
    private enum ContentType {TEXT, IMAGE};
    private final ProjectRepository projectRepository;
    private final TemplateRepository templateRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, TemplateRepository templateRepository) {
        this.projectRepository = projectRepository;
        this.templateRepository = templateRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(UUID id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public List<Project> createProjectsFromGemini(GeminiJsonFormat geminiJsonFormat) {
        List<Project> theProjects = new ArrayList<>();
        for(ProjectInputFormat gemminiProject : geminiJsonFormat.getProjects()){
            Project theProject = new Project(gemminiProject.getProjectTitle());
            for(SlideInputFormat geminiSlide : gemminiProject.getSlides()) {
                Slide theSlide = new Slide();
                switch (geminiSlide.getSlideType()) {
                    case TEXT_ONLY: {
                        textOnlyTemplateProcess(theSlide,geminiSlide);
                        break;
                    }
                    case ONE_IMAGE_AND_TEXT: {
                        oneImageAndTextTemplateProcess(theSlide,geminiSlide);
                        break;
                    }
                    default: twoImagesAndTextTemplateProcess(theSlide,geminiSlide);
                }
                theSlide.setProjectId(theProject.getId());
                theProject.getSlides().add(theSlide);
            }
            projectRepository.save(theProject);
            theProjects.add(theProject);
        }
        return theProjects;
    }

    @Override
    public Project save(Project theProject) {
        return projectRepository.save(theProject);
    }
    @Override
    public void deleteById(UUID id) {
        Optional<Project> project = projectRepository.findById(id);
        if(project == null){
            throw new RuntimeException("Project not found - " + id);
        }
        projectRepository.delete(project.get());
    }
    private void textOnlyTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        Template onlyTextTemplate = templateRepository.findById(TEXT_ONLY_TEMPLATE_ID).get();
        theSlide.setTemplate(onlyTextTemplate);
        for(TemplateElement templateElement : onlyTextTemplate.getTemplateElements()){
            Element newSlideElement = templateElement.getElement();
            newSlideElement.setSlideId(theSlide.getId());
            newSlideElement.setHeadingTitle(geminiSlide.getHeadingTitle());
            newSlideElement.setContent(geminiSlide.getParagraphText());
            theSlide.getElements().add(newSlideElement);
        }
    }
    private void oneImageAndTextTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        Template oneImageTemplate = templateRepository.findById(ONE_IMAGE_AND_TEXT_TEMPLATE_ID).get();
        theSlide.setTemplate(oneImageTemplate);
        for(TemplateElement templateElement : oneImageTemplate.getTemplateElements()){
            Element elementFromTemplate = templateElement.getElement();
            Element newSlideElement = null;
            if(elementFromTemplate.getElementType().equals(ContentType.TEXT.toString())){
                newSlideElement = templateElement.getElement();
                newSlideElement.setId(null);
                newSlideElement.setHeadingTitle(geminiSlide.getHeadingTitle());
                newSlideElement.setContent(geminiSlide.getParagraphText());
                theSlide.getElements().add(newSlideElement);
            }
            else if(elementFromTemplate.getElementType().equals(ContentType.IMAGE.toString())){
                newSlideElement = templateElement.getElement();
                newSlideElement.setId(null);
                newSlideElement.setHeadingTitle(geminiSlide.getHeadingTitle());
                newSlideElement.setContent(geminiSlide.getParagraphText());
                newSlideElement.setImageUrl(geminiSlide.getSingleImageUrl());
                theSlide.getElements().add(newSlideElement);
            }
            newSlideElement.setSlideId(theSlide.getId());
            theSlide.getElements().add(newSlideElement);
        }
    }
    private void twoImagesAndTextTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        Template twoImagesTemplate = templateRepository.findById(TWO_IMAGES_AND_TEXT_TEMPLATE_ID).get();
        theSlide.setTemplate(twoImagesTemplate);
        for(int templateElementIndex = 0; templateElementIndex < 3; templateElementIndex++) {
            TemplateElement templateElement = twoImagesTemplate.getTemplateElements().get(templateElementIndex);
            Element newSlideElement = null;
            if (templateElement.getElement().getElementType().equals(ContentType.TEXT.toString())) {
                newSlideElement = templateElement.getElement();
                newSlideElement.setId(null);
                newSlideElement.setHeadingTitle(geminiSlide.getHeadingTitle());
                newSlideElement.setContent(geminiSlide.getParagraphText());
                theSlide.getElements().add(newSlideElement);
            }
            else if(templateElement.getElement().getElementType().equals(ContentType.IMAGE.toString())
                    && templateElementIndex == 1){
                newSlideElement = templateElement.getElement();
                newSlideElement.setId(null);
                newSlideElement.setHeadingTitle(geminiSlide.getFirstImageTitle());
                newSlideElement.setContent(geminiSlide.getFirstImageText());
                newSlideElement.setImageUrl(geminiSlide.getFirstImageUrl());
                theSlide.getElements().add(newSlideElement);
            }
            else if(templateElement.getElement().getElementType().equals(ContentType.IMAGE.toString())
                    && templateElementIndex == 2) {
                newSlideElement = templateElement.getElement();
                newSlideElement.setId(null);
                newSlideElement.setHeadingTitle(geminiSlide.getSecondImageTitle());
                newSlideElement.setContent(geminiSlide.getSecondImageText());
                newSlideElement.setImageUrl(geminiSlide.getSecondImageUrl());
                theSlide.getElements().add(newSlideElement);
            }
            newSlideElement.setSlideId(theSlide.getId());
            theSlide.getElements().add(newSlideElement);
        }
    }
}

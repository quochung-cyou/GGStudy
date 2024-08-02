package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.ProjectRepository;
import web.dao.TemplateRepository;
import web.model.*;

import java.util.List;
import java.util.UUID;

import static web.utils.Constants.*;

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
    public Project findByUUIdString(String format){return projectRepository.findByUUIdString(format);}

    @Override
    public Project createProjectsFromGemini(ProjectInputFormat projectInputFormat) {
        Project theProject = new Project();
        for(SlideInputFormat geminiSlide : projectInputFormat.getSlides()) {
            Slide theSlide = new Slide();
            theSlide.setTopicName(geminiSlide.getTopicName());
            theSlide.setHeadingTitle(geminiSlide.getHeadingTitle());
            switch (geminiSlide.getSlideType()) {
                case "TEXT_ONLY": {
                    textOnlyTemplateProcess(theSlide,geminiSlide);
                    break;
                }
                case "ONE_IMAGE_AND_TEXT": {
                    oneImageAndTextTemplateProcess(theSlide,geminiSlide);
                    break;
                }
                case "TWO_IMAGES_AND_TEXT": {
                    twoImagesAndTextTemplateProcess(theSlide,geminiSlide);
                    break;
                }
                default: throw new RuntimeException("Slide template not found - " + geminiSlide.getSlideType());
            }
            theProject.getSlides().add(theSlide);
        }
        projectRepository.save(theProject);
        return theProject;
    }

    @Override
    public Project save(Project theProject) {
        return projectRepository.save(theProject);
    }
    @Override
    public void deleteById(UUID id) {
        Project project = projectRepository.findByUUIdString(String.valueOf(id));
        if(project == null) throw new RuntimeException("Project not found - " + id);
        projectRepository.delete(project);
    }
    private void textOnlyTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        Template onlyTextTemplate = templateRepository.findByUUIdString(TEXT_ONLY_TEMPLATE_ID);
        for(Element templateElement : onlyTextTemplate.getElements()){
            Element newSlideElement = extractTemplateElement(templateElement);
            newSlideElement.setTopicName(geminiSlide.getTopicName());
            newSlideElement.setHeadingTitle(geminiSlide.getHeadingTitle());
            newSlideElement.setContent(geminiSlide.getSlideTopicName());
            theSlide.getElements().add(newSlideElement);
        }
    }
    private void oneImageAndTextTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        Template oneImageTemplate = templateRepository.findByUUIdString(ONE_IMAGE_AND_TEXT_TEMPLATE_ID);
        for(Element templateElement : oneImageTemplate.getElements()){
            Element newSlideElement = extractTemplateElement(templateElement);
            if(templateElement.getElementType().equals(ContentType.TEXT.toString())){
                newSlideElement.setTopicName(geminiSlide.getTopicName());
                newSlideElement.setHeadingTitle(geminiSlide.getHeadingTitle());
                newSlideElement.setContent(geminiSlide.getParagraphText());
            }
            else if(templateElement.getElementType().equals(ContentType.IMAGE.toString())){
                newSlideElement.setHeadingTitle(geminiSlide.getHeadingTitle());
                newSlideElement.setContent(geminiSlide.getParagraphText());
                newSlideElement.setImageUrl(geminiSlide.getSingleImageUrl());
            }
            theSlide.getElements().add(newSlideElement);
        }
    }
    private void twoImagesAndTextTemplateProcess(Slide theSlide, SlideInputFormat geminiSlide) {
        Template twoImagesTemplate = templateRepository.findByUUIdString(TWO_IMAGES_AND_TEXT_TEMPLATE_ID);
        for(int templateElementIndex = 0; templateElementIndex < 3; templateElementIndex++) {
            Element templateElement = twoImagesTemplate.getElements().get(templateElementIndex);
            Element newSlideElement = extractTemplateElement(templateElement);
            if (templateElement.getElementType().equals(ContentType.TEXT.toString())) {
                newSlideElement.setTopicName(geminiSlide.getTopicName());
                newSlideElement.setHeadingTitle(geminiSlide.getHeadingTitle());
                newSlideElement.setContent(geminiSlide.getParagraphText());
            }
            else if(templateElement.getElementType().equals(ContentType.IMAGE.toString())
                    && templateElementIndex == 1){
                newSlideElement.setHeadingTitle(geminiSlide.getFirstImageTitle());
                newSlideElement.setContent(geminiSlide.getFirstImageText());
                newSlideElement.setImageUrl(geminiSlide.getFirstImageUrl());
            }
            else if(templateElement.getElementType().equals(ContentType.IMAGE.toString())
                    && templateElementIndex == 2) {
                newSlideElement.setHeadingTitle(geminiSlide.getSecondImageTitle());
                newSlideElement.setContent(geminiSlide.getSecondImageText());
                newSlideElement.setImageUrl(geminiSlide.getSecondImageUrl());
                theSlide.getElements().add(newSlideElement);
            }
            theSlide.getElements().add(newSlideElement);
        }
    }

    private Element extractTemplateElement(Element templateElement){
        return new Element(
                templateElement.getElementType(),
                templateElement.getLayer(),
                templateElement.getAppearOrder(),
                templateElement.getSizeX(),
                templateElement.getSizeY(),
                templateElement.getPosX(),
                templateElement.getPosY(),
                templateElement.getDuration()
        );
    }
}



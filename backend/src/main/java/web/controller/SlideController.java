package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.Slide;
import web.service.SlideService;

import java.util.List;

@RestController
@RequestMapping("/v1/projects")
public class SlideController {
    @Autowired
    private SlideService slideService;

    public SlideController(SlideService slideService) {
        this.slideService = slideService;
    }

    @GetMapping("/{projectId}/slides")
    public List<Slide> getSlides(@PathVariable String projectId) {
        return slideService.findByProjectId(projectId);
    }

    @GetMapping("/{projectId}/slides/{slideId}")
    public Slide getSlideById(@PathVariable String projectId, @PathVariable String slideId) {

        return slideService.findById(slideId);
    }
}
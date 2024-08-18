package web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.common.dto.CustomResponse;
import web.model.Slide;
import web.service.SlideService;

import java.util.List;

@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
public class SlideController {
    private final SlideService slideService;

    @GetMapping("/{projectId}/slides")
    public CustomResponse<List<Slide>> getSlides(@PathVariable String projectId) {
        return new CustomResponse<>(slideService.findByProjectId(projectId));
    }

    @GetMapping("/{projectId}/slides/{slideId}")
    public CustomResponse<Slide> getSlideById(@PathVariable String slideId) {
        return new CustomResponse<>(slideService.findById(slideId));
    }
}
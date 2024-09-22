package web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.common.dto.CustomResponse;
import web.common.dto.ProjectDTO;
import web.common.utils.SecurityUtils;
import web.model.Outline;
import web.model.OutlineResponse;
import web.model.Project;
import web.model.UserChatRequest;
import web.service.ProjectService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {
    private final ProjectService projectService;
    private final SecurityUtils securityUtils;

    @GetMapping()
    public CustomResponse<Page<ProjectDTO>> findAll(
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "id") String sortBy) {
        return new CustomResponse<>(projectService.findAll(size, page, sortBy, securityUtils.getCurrentUser()));
    }

    @GetMapping("/{id}")
    public CustomResponse<Project> findById(@PathVariable String id) {
        return new CustomResponse<>(projectService.findById(id));
    }

    @PostMapping("")
    public CustomResponse<Project> createProjectsFromOutlines(@RequestParam(required = false) String topicName, @RequestBody List<Outline> outlines) throws IOException {
        return new CustomResponse<>(projectService.createProjectsFromOutlines(topicName, outlines));
    }

    @PostMapping("/answers")
    public CustomResponse<String> getAnswerFromGemini(@RequestBody UserChatRequest userChatRequest) throws IOException {
        return new CustomResponse<>(
                projectService.getAnswerFromGemini(userChatRequest.getHistory(), userChatRequest.getQuestion())
        );
    }

    @PostMapping("/outlines")
    private CustomResponse<List<OutlineResponse>> createProjectOutlines(@RequestParam String topicName) throws IOException {
        return new CustomResponse<>(projectService.createProjectOutlines(topicName));
    }

    @PutMapping()
    public CustomResponse<Project> updateProject(@RequestBody Project theProject) {
        return new CustomResponse<>(projectService.save(theProject));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable String id) {
        projectService.deleteById(id);
    }

}

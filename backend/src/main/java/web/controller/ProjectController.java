package web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.dto.CustomResponse;
import web.dto.ProjectDTO;
import web.model.Project;
import web.service.ProjectService;

import java.io.IOException;

@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("")
    public CustomResponse<Page<ProjectDTO>> findAll(
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "id") String sortBy) {
        return new CustomResponse<>(projectService.findAll(size, page, sortBy),"Project list.");
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable String id) {
        return projectService.findById(id);
    }

    @PostMapping
    public Project createProject(@RequestParam String topicName,
                                 @RequestParam(required = false, defaultValue = "") String additionalInfo) throws IOException {
        return projectService.createProjectsFromGemini(topicName, additionalInfo);
    }

    @PutMapping("")
    public Project updateProject(@RequestBody Project theProject) {
        return projectService.save(theProject);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable String id) {
        projectService.deleteById(id);
    }

}

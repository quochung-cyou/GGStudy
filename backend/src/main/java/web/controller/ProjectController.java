package web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.model.Project;
import web.model.View;
import web.service.GeminiService;
import web.service.ProjectService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    GeminiService geminiService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @JsonView(View.SummaryProject.class)
    @GetMapping("")
    public Page<Project> findAll(
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "id") String sortBy) {
        return projectService.findAll(PageRequest.of(page < 0 ? 0 : page, size > 20 ? 20 : size, Sort.Direction.ASC, sortBy));
    }

    @JsonView(View.FullProject.class)
    @GetMapping("/{id}")
    public Project findById(@PathVariable String id) {
        return projectService.findById(id);
    }

    @PostMapping("")
    public Project createProject() throws IOException {
        return geminiService.callApi("data/prompt.txt");
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

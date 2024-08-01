package web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.model.*;
import web.service.ProjectService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/projects")
public class ProjectController {
    private ProjectService projectService;
    public ProjectController(ProjectService projectService) {this.projectService = projectService;}
    @GetMapping("")
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable UUID id){return projectService.findById(id);}

    @PostMapping("")
    public List<Project> createProject(@RequestBody GeminiJsonFormat geminiJsonFormat){
        return projectService.createProjectsFromGemini(geminiJsonFormat);
    }
    @PutMapping("")
    public Project updateProject(@RequestBody Project theProject){
        return projectService.save(theProject);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable UUID id) {projectService.deleteById(id);}

}

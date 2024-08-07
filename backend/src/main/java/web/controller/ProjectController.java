package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.common.io.ResourceLoader;
import web.model.*;
import web.service.GeminiService;
import web.service.ProjectService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    GeminiService geminiService;

    @Autowired
    private ResourceLoader loader;
    @Value("${gemini.api-key}")
    private String geminiKey;

    public ProjectController(ProjectService projectService) {this.projectService = projectService;}
    @GetMapping("")
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable String id){return projectService.findById(id);}

    @PostMapping("")
    public Project createProject(){
        String prompt = loader.loadPromptTemplate("src/main/resources/data/prompt.txt");
        return geminiService.callApi(prompt, geminiKey);
    }
    @PutMapping("")
    public Project updateProject(@RequestBody Project theProject){
        return projectService.save(theProject);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable String id) {projectService.deleteById(id);}

}

package web.service;

import web.model.GeminiJsonFormat;
import web.model.Project;
import web.model.Template;

import java.util.List;
import java.util.UUID;


public interface ProjectService {
    List<Project> findAll();
    Project findById(UUID id);
    List<Project> createProjectsFromGemini(GeminiJsonFormat geminiJsonFormat);
    Project save(Project theSlide);
    void deleteById(UUID id);
}

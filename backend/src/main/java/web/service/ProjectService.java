package web.service;

import web.model.Project;
import web.model.ProjectInputFormat;

import java.util.List;
import java.util.UUID;


public interface ProjectService {
    List<Project> findAll();
    Project findByUUIdString(String format);
    Project createProjectsFromGemini(ProjectInputFormat projectInputFormat);
    Project save(Project theSlide);
    void deleteById(UUID id);

}

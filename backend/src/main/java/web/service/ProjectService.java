package web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import web.model.Project;
import web.model.ProjectInputFormat;

import java.util.List;
import java.util.UUID;


public interface ProjectService {
    Page<Project> findAll(PageRequest paging);
    Project findById(String id);
    Project createProjectsFromGemini(ProjectInputFormat projectInputFormat);
    Project save(Project theSlide);
    void deleteById(String id);

}

package web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import web.model.Project;

import java.io.IOException;


public interface ProjectService {
    Page<Project> findAll(PageRequest paging);

    Project findById(String id);

    Project createProjectsFromGemini(String topicName, String additionalInfo) throws IOException;

    Project save(Project theSlide);

    void deleteById(String id);

}

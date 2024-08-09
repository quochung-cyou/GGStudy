package web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import web.dto.ProjectDTO;
import web.model.Project;


public interface ProjectService {
    Page<ProjectDTO> findAll(int size, int page, String sortBy);
  
    Project findById(String id);

    Project createProjectsFromGemini(String topicName, String additionalInfo) throws IOException;

    Project save(Project theSlide);

    void deleteById(String id);
}

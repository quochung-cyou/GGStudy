package web.service;

import org.springframework.data.domain.Page;
import web.common.dto.ProjectDTO;
import web.model.Project;
import java.io.IOException;

public interface ProjectService {
    Page<ProjectDTO> findAll(int size, int page, String sortBy, String currentUser);

    Project findById(String id);

    Project createProjectsFromGemini(String topicName, String additionalInfo) throws IOException;

    Project save(Project theSlide);

    void deleteById(String id);
}

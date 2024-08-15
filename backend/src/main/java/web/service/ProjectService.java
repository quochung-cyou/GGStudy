package web.service;

import org.springframework.data.domain.Page;
import web.dto.ProjectDTO;
import web.model.OutlineResponse;
import web.model.Project;
import java.io.IOException;
import java.util.List;

public interface ProjectService {
    Page<ProjectDTO> findAll(int size, int page, String sortBy);

    Project findById(String id);

    Project createProjectsFromGemini(String topicName, String additionalInfo) throws IOException;

    List<OutlineResponse> createProjectOutlines(String topicName) throws IOException;

    Project save(Project theSlide);

    void deleteById(String id);
}

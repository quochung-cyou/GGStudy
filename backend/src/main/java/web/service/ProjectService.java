package web.service;

import org.springframework.data.domain.Page;
import web.dto.OutlineResponse;
import web.dto.ProjectDTO;
import web.model.Project;
import java.io.IOException;
import java.util.List;

public interface ProjectService {
    Page<ProjectDTO> findAll(int size, int page, String sortBy);

    List<OutlineResponse> createProjectOutlines(String topicName) throws IOException;

    Project findById(String id);

    Project createProjectsFromGemini(String topicName, String additionalInfo) throws IOException;

    Project save(Project theSlide);

    void deleteById(String id);
}

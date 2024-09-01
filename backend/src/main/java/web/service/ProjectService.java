package web.service;

import org.springframework.data.domain.Page;
import web.common.dto.ProjectDTO;
import web.model.Outline;
import web.model.OutlineResponse;
import web.model.Project;
import java.io.IOException;
import java.util.List;

public interface ProjectService {
    Page<ProjectDTO> findAll(int size, int page, String sortBy, String currentUser);

    Project findById(String id);

    Project createProjectsFromOutlines(List<OutlineResponse> outlines) throws IOException;

    List<OutlineResponse> createProjectOutlines(String topicName) throws IOException;

    String getAnswerFromGemini(List<String> history, String question) throws IOException;

    Project save(Project theSlide);

    void deleteById(String id);
}

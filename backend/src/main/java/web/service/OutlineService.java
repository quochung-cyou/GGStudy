package web.service;

import org.springframework.data.domain.Page;
import web.model.Outline;

import java.io.IOException;

public interface OutlineService {
    Outline createOutlineFromGemini(String topicName, String additionalInfo) throws IOException;
    Page<Outline> findAll(int size, int page, String sortBy);
    Outline findById(String id);
    Outline save(Outline outline);
    void deleteById(String id);
}

package web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import web.model.Image;

public interface ImageService {
    Image searchImages(String prompt) throws JsonProcessingException;
    Page<Image> findAll(int size, int page, String sortBy);
    Image findById(String id);
    Image save(Image image);
    void deleteById(String id);
}

package web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import web.model.Image;
import web.model.Outline;

public interface ImageService {
    Image searchImage(String prompt) throws JsonProcessingException;
    void setUrlImageElement(Outline outline);
    Page<Image> findAll(int size, int page, String sortBy);
    Image findById(String id);
    Image save(Image image);
    void deleteById(String id);
}

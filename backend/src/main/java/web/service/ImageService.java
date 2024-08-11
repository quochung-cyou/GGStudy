package web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import web.dto.ImageDTO;
import web.model.Image;
import web.model.Slide;

import java.util.List;

public interface ImageService {
    Image searchImages(String prompt) throws JsonProcessingException;
    Page<ImageDTO> findAll(int size, int page, String sortBy);
    Image findById(String id);
    Image save(Image image);
    void deleteById(String id);
}

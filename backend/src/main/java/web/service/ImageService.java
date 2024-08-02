package web.service;

import web.model.Image;
import web.model.Slide;

import java.util.List;

public interface ImageService {
    List<Image> collectImages(String prompt, String apiKey, String searchEngineID);
    List<Image> findAll();
    Image findById(String id);
    Image save(Image image);
    void deleteById(String id);
}

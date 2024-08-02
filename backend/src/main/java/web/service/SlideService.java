package web.service;

import web.model.Slide;

import java.util.List;

public interface SlideService {
    List<Slide> findAll();
    Slide findById(String id);
    Slide save(Slide theSlide);
    void deleteById(String id);
}

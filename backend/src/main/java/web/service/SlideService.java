package web.service;

import web.model.Slide;

import java.util.List;

public interface SlideService {
    List<Slide> findByProjectId(String projectId);
    Slide findById(String id);
}

package web.service;

import org.springframework.stereotype.Service;
import web.dao.SlideRepository;
import web.model.Slide;

import java.util.List;

@Service
public class SlideServiceImpl implements SlideService {
    private SlideRepository slideRepository;
    public SlideServiceImpl(SlideRepository slideRepository) {
        this.slideRepository = slideRepository;
    }

    @Override
    public List<Slide> findAll() {
        return slideRepository.findAll();
    }

    @Override
    public List<Slide> findByProjectId(String projectId) {
        return slideRepository.findByProjectId(projectId);
    }

    @Override
    public Slide findById(String id) {
        return slideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Did not find employee id - " + id));
    }

    @Override
    public Slide save(Slide theSlide) {
        return slideRepository.save(theSlide);
    }

    @Override
    public void deleteById(String id) {
        slideRepository.deleteById(id);
    }
}

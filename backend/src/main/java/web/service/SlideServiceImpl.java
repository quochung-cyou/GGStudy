package web.service;

import org.springframework.stereotype.Service;
import web.dao.repository.SlideRepository;
import web.common.exception.NotFoundException;
import web.model.Slide;

import java.util.List;

@Service
public class SlideServiceImpl implements SlideService {
    private SlideRepository slideRepository;
    public SlideServiceImpl(SlideRepository slideRepository) {
        this.slideRepository = slideRepository;
    }

    @Override
    public List<Slide> findByProjectId(String projectId) {
        return slideRepository.findByProjectId(projectId);
    }

    @Override
    public Slide findById(String id) {
        return slideRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Slide not found with the given ID."));
    }
}

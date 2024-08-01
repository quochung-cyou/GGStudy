package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.ElementRepository;
import web.dao.SlideRepository;
import web.dao.TemplateRepository;
import web.model.Element;
import web.model.Slide;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

package web.service;

import org.springframework.stereotype.Service;
import web.dao.ElementRepository;
import web.model.Element;

@Service
public class ElementServiceImpl implements ElementService{
    private ElementRepository elementRepository;
    public ElementServiceImpl(ElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }

    @Override
    public Element findById(String id) {
        return null;
    }

    @Override
    public Element save(Element theElement) {
        return elementRepository.save(theElement);
    }
    @Override
    public void deleteById(String id) {
        elementRepository.deleteById(id);
    }
}

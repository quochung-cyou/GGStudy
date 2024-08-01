package web.service;

import web.model.Element;

public interface ElementService {
    Element findById(String id);
    Element save(Element theElement);
    void deleteById(String id);
}

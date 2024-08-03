package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.TemplateElement;

import java.util.UUID;

public interface TemplateElementRepository extends JpaRepository<TemplateElement, String> {
}

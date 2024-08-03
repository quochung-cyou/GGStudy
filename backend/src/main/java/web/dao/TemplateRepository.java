package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Slide;
import web.model.Template;

import java.util.UUID;

public interface TemplateRepository extends JpaRepository<Template, String> {
}

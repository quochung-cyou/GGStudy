package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.model.Template;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template, String> {
    List<Template> findByTemplateType(String templateType);
}

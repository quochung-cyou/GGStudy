package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.model.Template;

public interface TemplateRepository extends JpaRepository<Template, String> {
    @Query(value = "SELECT * FROM templates WHERE id = UUID_TO_BIN(:id)", nativeQuery = true)
    Template findByUUIdString(@Param("id") String id);
}

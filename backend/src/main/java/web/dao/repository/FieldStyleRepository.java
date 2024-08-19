package web.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.FieldStyle;

import java.util.List;

@Repository
public interface FieldStyleRepository extends JpaRepository<FieldStyle, String> {

    List<FieldStyle> findByEntityIdInAndEntityType(List<String> entityId, String entityType);
}

package web.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Outline;

public interface OutlineRepository extends JpaRepository<Outline, String> {
}

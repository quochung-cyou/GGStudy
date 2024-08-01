package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Project;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}

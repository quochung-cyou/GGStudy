package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.model.Project;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    @Query(value = "SELECT * FROM projects WHERE id = UUID_TO_BIN(:id)", nativeQuery = true)
    Project findByUUIdString(@Param("id") String id);
}

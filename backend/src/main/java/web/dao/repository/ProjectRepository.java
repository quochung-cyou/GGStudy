package web.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    Page<Project> findAll(Specification<Project> specification, Pageable pageable);
}

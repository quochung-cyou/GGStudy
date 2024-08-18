package web.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.Slide;

import java.util.List;

@Repository
public interface SlideRepository extends JpaRepository<Slide, String> {
    List<Slide> findByProjectId(String projectId);
}

package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.Slide;
@Repository
public interface SlideRepository extends JpaRepository<Slide, String> {
}

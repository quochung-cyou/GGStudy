package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Slide;

public interface SlideRepository extends JpaRepository<Slide, String> {
}

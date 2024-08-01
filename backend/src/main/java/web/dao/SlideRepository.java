package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Slide;

import java.util.UUID;

public interface SlideRepository extends JpaRepository<Slide, String> {
}

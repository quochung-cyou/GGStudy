package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Image;

public interface ImageRepository extends JpaRepository<Image, String> {
}

package web.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
}

package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Usernote;

public interface UsernoteRepository extends JpaRepository<Usernote, String> {
}

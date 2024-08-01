package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Usernote;

import java.util.UUID;

public interface UsernoteRepository extends JpaRepository<Usernote, String> {
}

package web.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.Usernote;

@Repository
public interface UsernoteRepository extends JpaRepository<Usernote, String> {
}

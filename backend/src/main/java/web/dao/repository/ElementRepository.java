package web.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.Element;

@Repository
public interface ElementRepository extends JpaRepository<Element, String> {
}

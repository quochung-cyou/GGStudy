package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.model.Element;

public interface ElementRepository extends JpaRepository<Element, String> {
    @Query(value = "SELECT * FROM elements WHERE id = UUID_TO_BIN(:id)", nativeQuery = true)
    Element findByUUIdString(@Param("id") String id);
}

package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Element;

import java.util.UUID;

public interface ElementRepository extends JpaRepository<Element, String> {
}

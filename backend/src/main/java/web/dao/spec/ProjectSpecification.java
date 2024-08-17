package web.dao.spec;

import org.springframework.data.jpa.domain.Specification;
import web.model.Project;

public class ProjectSpecification {

    public static Specification<Project> fromCreatedBy(String createBy) {
        return (root, query, cb) -> cb.equal(root.get("createBy"), createBy);
    }

    private ProjectSpecification() {
    }
}
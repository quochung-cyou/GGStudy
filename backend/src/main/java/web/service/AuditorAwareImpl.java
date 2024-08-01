package web.service;

import org.springframework.data.domain.AuditorAware;
import web.dao.ProjectRepository;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    private ProjectRepository projectRepository;
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("admin");
    }
}

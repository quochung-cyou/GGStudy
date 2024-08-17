package web.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private final AuditorAware<String> auditorAware;

    public String getCurrentUser() {
        return auditorAware.getCurrentAuditor().orElse("UNKNOWN");
    }
}

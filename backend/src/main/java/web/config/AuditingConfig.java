package web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import web.model.Project;
import web.service.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorInit")
public class AuditingConfig {
    @Bean
    public AuditorAware<String> auditorInit(){
        return new AuditorAwareImpl();
    }
}

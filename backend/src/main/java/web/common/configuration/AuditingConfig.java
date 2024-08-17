package web.common.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorInit")
public class AuditingConfig {
    @Bean
    public AuditorAware<String> auditorInit(){
        return new AuditorAwareImpl();
    }
}

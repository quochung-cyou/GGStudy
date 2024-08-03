package web.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class AppConfig {


    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
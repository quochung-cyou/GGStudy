package web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class GeminiJsonFormat {
    @JsonProperty("topic_name")
    private String topicName;
    @JsonProperty("chapters")
    private List<ProjectInputFormat> projects;
}

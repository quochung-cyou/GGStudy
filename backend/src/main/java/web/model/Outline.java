package web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Outline {
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
}

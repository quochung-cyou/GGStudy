package web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProjectInputFormat {
    @JsonProperty("chapters")
    private List<SlideInputFormat> slides;
}

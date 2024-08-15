package web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OutlineInputFormat {
    @JsonProperty("outlines")
    List<OutlineResponse> outlines;
}
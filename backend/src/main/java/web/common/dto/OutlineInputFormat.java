package web.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import web.model.OutlineResponse;

import java.util.List;

@Getter
@Setter
public class OutlineInputFormat {
    @JsonProperty("outlines")
    List<OutlineResponse> outlines;
}
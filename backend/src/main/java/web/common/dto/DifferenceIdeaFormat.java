package web.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DifferenceIdeaFormat {
    @JsonProperty("difference")
    String differenceTitle = "";
    @JsonProperty("idea_1")
    String firstIdea = "";
    @JsonProperty("idea_2")
    String secondIdea = "";
    @JsonProperty("idea_3")
    String thirdIdea = "";
}

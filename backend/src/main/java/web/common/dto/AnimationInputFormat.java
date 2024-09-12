package web.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimationInputFormat {
    @JsonProperty("element_id")
    private String elementId;
    @JsonProperty("effect_type")
    private String elementType;
    @JsonProperty("appear_order")
    private int appearOrder;
    @JsonProperty("duration")
    private int duration;
}

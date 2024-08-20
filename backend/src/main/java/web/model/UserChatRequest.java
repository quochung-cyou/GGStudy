package web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserChatRequest {
    @JsonProperty("history")
    private List<String> history;
    @JsonProperty("question")
    private String question;
}

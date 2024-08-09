package web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponse<T> {
    private T data;
    private String message;

    public CustomResponse(String message) {
        this.message = message;
    }
}

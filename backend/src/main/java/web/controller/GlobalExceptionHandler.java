package web.controller;

import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import web.dto.CustomResponse;
import web.model.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler({NotFoundException.class, NoResourceFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CustomResponse<Object> handleNotFoundException(Exception exception) {
        return new CustomResponse<>(exception.getMessage());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomResponse<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return new CustomResponse<>("Missing required parameter: " + ex.getParameterName());
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CustomResponse<Object> handleRuntimeException(RuntimeException ex) {
        return new CustomResponse<>(ex.getMessage());
    }
}

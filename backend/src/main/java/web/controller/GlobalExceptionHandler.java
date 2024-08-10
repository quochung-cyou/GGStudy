package web.controller;

import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}

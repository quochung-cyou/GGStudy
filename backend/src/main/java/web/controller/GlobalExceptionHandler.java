package web.controller;

import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import web.dto.CustomResponse;
import web.model.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new CustomResponse<>(exception.getMessage()));
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<Object> handleNoResourceFoundException(ServletException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new CustomResponse<>(exception.getMessage()));
    }
}

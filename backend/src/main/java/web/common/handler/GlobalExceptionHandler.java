package web.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import web.common.dto.CustomResponse;
import web.common.exception.NotFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({NotFoundException.class, NoResourceFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CustomResponse<Object> handleNotFoundException(Exception exception) {
        return handleException(exception);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomResponse<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return handleException("Missing required parameter: " + ex.getParameterName());
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CustomResponse<Object> handleJsonProcessingException(Exception exception) {
        return handleException(exception);
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public CustomResponse<Object> handleAccessDeniedException(AccessDeniedException exception) {
        return handleException("Access denied: " + exception.getMessage());
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public CustomResponse<Object> handleAuthenticationException(AuthenticationException exception) {
        return handleException("Authentication failed: " + exception.getMessage());
    }

    private CustomResponse<Object> handleException(Exception exception) {
        log.error("Exception caught: {}", exception);
        return handleException(exception.getMessage());
    }

    private CustomResponse<Object> handleException(String message) {
        log.error("Exception caught: {}", message);
        return new CustomResponse<>(message);
    }
}

package greenify.server.rest;

import greenify.common.ApplicationException;
import greenify.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//class that handles exceptions for the rest server
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ErrorResponse applicationException(ApplicationException ex) {
        return new ErrorResponse(ex.getMessage());
    }
}

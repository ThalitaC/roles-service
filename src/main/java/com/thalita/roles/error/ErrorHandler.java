package com.thalita.roles.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
@RestController
public class ErrorHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse exceptionResponse(HttpClientErrorException exception) {


        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(exception.getStatusCode().toString());
        errorResponse.setMessage(exception.getMessage());

        return errorResponse;
    }
}

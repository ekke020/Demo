package com.backend.demo.error;

import com.backend.demo.error.exceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable
            (HttpMessageNotReadableException ex, HttpHeaders headers,
             HttpStatus status, WebRequest request) {
        Error error = new Error(HttpStatus.BAD_REQUEST);
        // TODO: Maybe add more info ??
        error.setMessage("Malformed JSON request");
        return buildResponseEntity(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        Error error = new Error(HttpStatus.NOT_FOUND);
        error.setMessage(ex.getMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        Error error = new Error(ex.getStatus());
        error.setMessage(ex.getReason());
        return buildResponseEntity(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String errorMessage = "Invalid method arguments";
        Error error = new Error(HttpStatus.BAD_REQUEST, errorMessage);
        error.addValidationErrors(ex.getBindingResult().getAllErrors());
        return buildResponseEntity(error);
    }


    private ResponseEntity<Object> buildResponseEntity(Error error) {
        return new ResponseEntity<>(error, error.getStatus());
    }

}

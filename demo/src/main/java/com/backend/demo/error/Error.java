package com.backend.demo.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Error {

    private HttpStatus status;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message = "Unexpected error";
    private List<SubError> subErrors = new ArrayList<>();


    public Error(HttpStatus status) {
        timestamp = LocalDateTime.now();
        this.status = status;
    }

    public Error(HttpStatus status, String message) {
        this(status);
        this.message = message;
    }

    public void addValidationErrors(List<ObjectError> objectErrors) {
        objectErrors.stream()
                .map(this::createSubError)
                .forEach(error -> subErrors.add(error));
    }

    private SubError createSubError(ObjectError error) {
        return new SubError(
                error.getObjectName(),
                ((FieldError) error).getField(),
                ((FieldError) error).getRejectedValue(),
                error.getDefaultMessage()
        );
    }

    @Data
    @AllArgsConstructor
    private static class SubError {
        private String object;
        private String field;
        private Object rejectedValue;
        private String message;

    }
}

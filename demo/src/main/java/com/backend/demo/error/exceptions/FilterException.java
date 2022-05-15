package com.backend.demo.error.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class FilterException extends AuthenticationException {

    private final int status;

    public FilterException(String msg, Throwable cause, int status) {
        super(msg, cause);
        this.status = status;
    }

    public FilterException(String msg, int status) {
        super(msg);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return HttpStatus.valueOf(this.status);
    }

}

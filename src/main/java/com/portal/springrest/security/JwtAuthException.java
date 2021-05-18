package com.portal.springrest.security;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthException extends AuthenticationException {

    private HttpStatus httpStatus;


    public JwtAuthException(String msg) {
        super(msg);
    }

    public JwtAuthException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

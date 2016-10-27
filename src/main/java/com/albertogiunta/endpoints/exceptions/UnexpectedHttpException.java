package com.albertogiunta.endpoints.exceptions;

import org.springframework.http.HttpStatus;

public class UnexpectedHttpException extends RuntimeException {

    HttpStatus statusCode;

    public UnexpectedHttpException(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}

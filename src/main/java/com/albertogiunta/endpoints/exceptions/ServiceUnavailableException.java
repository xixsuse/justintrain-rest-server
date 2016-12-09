package com.albertogiunta.endpoints.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Service Unavailable")
public class ServiceUnavailableException extends RuntimeException {
}

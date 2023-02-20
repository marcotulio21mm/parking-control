package com.api.parkingcontrol.exceptions;

// import org.springframework.http.HttpStatus;

public class ApiException {
    private final String message;
    // private final Throwable throwable;
    // private final HttpStatus httpStatus;
    private final boolean type;

    public ApiException(String message, boolean type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public boolean getType() {
        return type;
    }

    // public Throwable getThrowable() {
    // return throwable;
    // }

    // public HttpStatus getHttpStatus() {
    // return httpStatus;
    // }
}

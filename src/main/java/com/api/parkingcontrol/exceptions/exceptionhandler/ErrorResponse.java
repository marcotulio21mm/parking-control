package com.api.parkingcontrol.exceptions.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String code;
    private String message;

    // Construtor para inicializar o objeto
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

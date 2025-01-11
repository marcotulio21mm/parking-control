package com.api.parkingcontrol.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionCustomized extends RuntimeException{
    private String code;
    private String message;

    public ExceptionCustomized(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

}


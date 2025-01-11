package com.api.parkingcontrol.exceptions.exceptiontypes;

import com.api.parkingcontrol.exceptions.ExceptionCustomized;

public class BadRequestException extends ExceptionCustomized {
    public BadRequestException(String code, String message) {
        super(code, message);
    }
}

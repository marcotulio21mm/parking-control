package com.api.parkingcontrol.exceptions.exceptiontypes;

import com.api.parkingcontrol.exceptions.ExceptionCustomized;

public class NotFoundException extends ExceptionCustomized {
    public NotFoundException(String code, String message) {
        super(code, message);
    }
}


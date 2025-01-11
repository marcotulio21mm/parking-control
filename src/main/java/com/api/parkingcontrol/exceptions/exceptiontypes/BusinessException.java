package com.api.parkingcontrol.exceptions.exceptiontypes;

import com.api.parkingcontrol.exceptions.ExceptionCustomized;

public class BusinessException extends ExceptionCustomized {
    public BusinessException(String code, String message) {
        super(code, message);
    }
}

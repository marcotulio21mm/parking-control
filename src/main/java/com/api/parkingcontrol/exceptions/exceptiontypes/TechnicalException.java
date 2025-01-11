package com.api.parkingcontrol.exceptions.exceptiontypes;

import com.api.parkingcontrol.exceptions.ExceptionCustomized;

public class TechnicalException extends ExceptionCustomized  {
    public TechnicalException(String code, String message) {
        super(code, message);
    }
}


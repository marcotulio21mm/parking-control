package com.api.parkingcontrol.exceptions.exceptionhandler;

import com.api.parkingcontrol.exceptions.ExceptionCustomized;
import com.api.parkingcontrol.exceptions.exceptiontypes.BadRequestException;
import com.api.parkingcontrol.exceptions.exceptiontypes.BusinessException;
import com.api.parkingcontrol.exceptions.exceptiontypes.NotFoundException;
import com.api.parkingcontrol.exceptions.exceptiontypes.TechnicalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExceptionCustomized.class)
    public final ResponseEntity<Object> expectionHandling(Exception ex) {
        int statusCode = 0;

        if (ex instanceof BusinessException)
            statusCode = HttpStatus.UNPROCESSABLE_ENTITY.value();
        else if(ex instanceof NotFoundException)
            statusCode = HttpStatus.NOT_FOUND.value();
        else if(ex instanceof TechnicalException)
            statusCode = 512;
        else if(ex instanceof BadRequestException)
            statusCode = 400;
        else {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            return new ResponseEntity<>(new ErrorResponse(String.valueOf(statusCode), "An unexpected error has occurred"), HttpStatus.valueOf(statusCode));
        }

        return new ResponseEntity<>(new ErrorResponse(((ExceptionCustomized) ex).getCode(), ex.getMessage()), HttpStatus.valueOf(statusCode));
    }

}

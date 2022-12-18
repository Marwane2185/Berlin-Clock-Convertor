package com.alzohra.berlinclock.exceptions;

import com.alzohra.berlinclock.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
   Advice to provide application support for exceptions and send correct error response to client
*/
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value
            = InvalidBerlinClockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse
    handleException(InvalidBerlinClockException ex) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value
            = InvalidDigitalTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse
    handleException(InvalidDigitalTimeException ex) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value
            = NullParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse
    handleException(NullParameterException ex) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
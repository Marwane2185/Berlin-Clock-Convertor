package com.alzohra.berlinclock.exceptions;

/*
   Custom class for invalid digital time format exception
*/
public class InvalidDigitalTimeException extends RuntimeException {

    private String message;

    public InvalidDigitalTimeException() {
    }

    public InvalidDigitalTimeException(String msg) {
        super(msg);
        this.message = msg;
    }
}

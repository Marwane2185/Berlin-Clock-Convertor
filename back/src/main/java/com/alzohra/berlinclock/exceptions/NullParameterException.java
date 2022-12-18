package com.alzohra.berlinclock.exceptions;

/*
   Custom class for null or empty parameter exception
*/

public class NullParameterException extends RuntimeException {

    private String message;

    public NullParameterException() {
    }

    public NullParameterException(String msg) {
        super(msg);
        this.message = msg;
    }
}

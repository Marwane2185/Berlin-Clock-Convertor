package com.alzohra.berlinclock.exceptions;

/*
   Custom class for invalid berlin clock format exception
*/
public class InvalidBerlinClockException extends RuntimeException {

    private String message;

    public InvalidBerlinClockException() {
    }

    public InvalidBerlinClockException(String msg) {
        super(msg);
        this.message = msg;
    }
}

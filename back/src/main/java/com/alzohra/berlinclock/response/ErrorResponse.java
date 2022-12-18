package com.alzohra.berlinclock.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
   Response format in case an exception is thrown
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;

    public ErrorResponse(String message) {
        super();
        this.message = message;
    }
}
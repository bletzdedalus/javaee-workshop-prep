package com.dedalus.error;

public class ErrorResponse {

    public int statusCode;

    public String message;

    public String messageCode;

    public ErrorResponse(int statusCode, String message, String messageCode) {
        this.statusCode = statusCode;
        this.message = message;
        this.messageCode = messageCode;
    }
}

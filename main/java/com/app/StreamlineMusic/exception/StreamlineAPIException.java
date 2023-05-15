package com.app.StreamlineMusic.exception;

import org.springframework.http.HttpStatus;

public class StreamlineAPIException extends RuntimeException{

    private HttpStatus status;
    private String massage;

    public StreamlineAPIException(HttpStatus status, String massage) {
        this.status = status;
        this.massage = massage;
    }

    public StreamlineAPIException(String message, HttpStatus status, String massage) {
        super(message);
        this.status = status;
        this.massage = massage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMassage() {
        return massage;
    }
}

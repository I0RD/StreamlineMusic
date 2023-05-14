package com.app.StreamlineMusic.payload;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String massage;
    private String details;

    public ErrorDetails(Date timestamp, String massage, String details) {
        this.timestamp = timestamp;
        this.massage = massage;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMassage() {
        return massage;
    }

    public String getDetails() {
        return details;
    }
}

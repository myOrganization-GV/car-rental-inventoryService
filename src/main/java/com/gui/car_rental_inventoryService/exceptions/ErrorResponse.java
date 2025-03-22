package com.gui.car_rental_inventoryService.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private List<String> errors;

    public ErrorResponse(){

    }
    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
    public ErrorResponse(int statusCode, String message, List<String> errors) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

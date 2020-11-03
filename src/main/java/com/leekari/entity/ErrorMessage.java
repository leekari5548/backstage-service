package com.leekari.entity;

/**
 * @author litao
 * @date 2020/9/25 09:33
 * @description
 */
public class ErrorMessage {
    private String message;
    private String cause;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, String cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}

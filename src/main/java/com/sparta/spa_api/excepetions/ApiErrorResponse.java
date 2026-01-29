package com.sparta.spa_api.excepetions;

public class ApiErrorResponse {
    private int status;
    private String message;
    private long timestamp;
    private String path;

    public ApiErrorResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
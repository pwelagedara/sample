package com.devopstools.portal.model.dto;

/**
 * Created by pubudu on 8/14/17.
 */
public class ErrorDto {

    private int status;

    private String error;

    private String message;

    public ErrorDto(int status, String error, String message) {
        super();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

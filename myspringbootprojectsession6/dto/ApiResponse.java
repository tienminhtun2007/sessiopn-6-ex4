package com.example.myspringbootprojectsession6.dto;

public class ApiResponse<T> {

    private Boolean success;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}

package com.example.afinal.helpers;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {
    @SerializedName("error")
    private boolean error;
    @SerializedName("description")
    private String message;
    @SerializedName("results")
    private T data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.springboot3.restapis.dto;

import java.time.Instant;

/**
 * Generic response wrapper used by controllers/services.
 * Carries a payload (e.g. Student), a success flag, message and status code.
 *
 * @param <T> payload type
 */
public class Response<T> {

    private boolean success;
    private String message;
    private int status;
    private T data;
    private Instant timestamp;

    public Response() {
        this.timestamp = Instant.now();
    }

    public Response(boolean success, String message, int status, T data) {
        this.success = success;
        this.message = message;
        this.status = status;
        this.data = data;
        this.timestamp = Instant.now();
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(true, "OK", 200, data);
    }

    public static <T> Response<T> ok(T data, String message) {
        return new Response<>(true, message, 200, data);
    }

    public static <T> Response<T> created(T data) {
        return new Response<>(true, "Created", 201, data);
    }

    public static <T> Response<T> error(String message, int status) {
        return new Response<>(false, message, status, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}

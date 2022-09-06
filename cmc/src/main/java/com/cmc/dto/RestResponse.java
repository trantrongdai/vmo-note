package com.cmc.dto;

import com.cmc.enums.StatusCode;

/**
 * Rest response generic
 * @param <T>
 */
public class RestResponse<T> {
    private Integer statusCode;

    private String devMessage;

    private String userMessage;

    private T data;

    private RestResponse(RestResponseBuilder<T> builder) {
        this.statusCode = builder.statusCode;
        this.devMessage = builder.devMessage;
        this.userMessage = builder.userMessage;
        this.data = builder.data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class RestResponseBuilder<T> {
        private Integer statusCode;
        private String devMessage;
        private String userMessage;

        private T data;

        public RestResponseBuilder() {
        }

        public RestResponseBuilder(StatusCode statusCode, T data) {
            this.statusCode = statusCode.getValue();
            this.data = data;
        }

        public RestResponseBuilder(Integer statusCode, T data) {
            this.statusCode = statusCode;
            this.data = data;
        }

        public RestResponseBuilder statusCode(Integer statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public RestResponseBuilder devMessage(String devMessage) {
            this.devMessage = devMessage;
            return this;
        }

        public RestResponseBuilder userMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public RestResponse<T> build() {
            RestResponse<T> response = new RestResponse<T>(this);
            return response;
        }
    }
}

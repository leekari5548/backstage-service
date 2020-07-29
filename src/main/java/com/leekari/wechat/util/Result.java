package com.leekari.wechat.util;

/**
 * @author litao
 * @date 2020/7/29 14:49
 * @description
 */
public class Result<T> {
    private int code;
    private String type;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Result(Builder<T> builder){
        this.code = builder.code;
        this.data = builder.data;
        this.message = builder.message;
        this.type = builder.type;
    }

    public Result(){}

    public static class Builder<T>{
        private int code;
        private String message;
        private String type;
        private T data;

        public Builder<T> code(int code){
            this.code = code;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> type(String type) {
            this.type = type;
            return this;
        }

        public Builder<T> data(T data){
            this.data = data;
            return this;
        }

        public Result<T> builder(){
            return new Result<T>(this);
        }
    }
}

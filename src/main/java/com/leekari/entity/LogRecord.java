package com.leekari.entity;

import java.util.Date;

/**
 * @author litao
 * @date 2020/7/29 20:53
 * @description
 */
public class LogRecord {
    private int id;
    private String operator;
    private Date operateTime;
    private int type;
    private String operation;

    @Override
    public String toString() {
        return "LogRecord{" +
                "id=" + id +
                ", operator='" + operator + '\'' +
                ", operateTime=" + operateTime +
                ", type=" + type +
                ", operation='" + operation + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public LogRecord(Builder builder){
        this.type = builder.type;
        this.operateTime = builder.operateTime;
        this.operator = builder.operator;
        this.operation = builder.operation;
    }

    public LogRecord(){}

    public static class Builder {
        private int type;
        private String operator;
        private Date operateTime;
        private String operation;

        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder operator(String operator) {
            this.operator = operator;
            return this;
        }

        public Builder operation(String operation) {
            this.operation = operation;
            return this;
        }

        public Builder operateTime(Date operateTime) {
            this.operateTime = operateTime;
            return this;
        }

        public LogRecord builder(){
            return new LogRecord(this);
        }
    }
}

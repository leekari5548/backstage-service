package com.leekari.entity;

import java.util.Date;

/**
 * @author litao
 * @date 2020/8/31 09:28
 * @description
 */
public class BlogType {
    private String id;
    private String typeName;
    private String lastOperationUser;
    private Date lastOperationTime;
    private Integer deleted;

    @Override
    public String toString() {
        return "BlogType{" +
                "id='" + id + '\'' +
                ", typeName='" + typeName + '\'' +
                ", lastOperationUser='" + lastOperationUser + '\'' +
                ", lastOperationTime=" + lastOperationTime +
                ", deleted=" + deleted +
                '}';
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getLastOperationUser() {
        return lastOperationUser;
    }

    public void setLastOperationUser(String lastOperationUser) {
        this.lastOperationUser = lastOperationUser;
    }

    public Date getLastOperationTime() {
        return lastOperationTime;
    }

    public void setLastOperationTime(Date lastOperationTime) {
        this.lastOperationTime = lastOperationTime;
    }
}

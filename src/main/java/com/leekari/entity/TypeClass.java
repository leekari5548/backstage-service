package com.leekari.entity;

import java.util.Date;

/**
 * @author litao
 * @date 2020/8/19 13:26
 * @description
 */
public class TypeClass {
    private String id;
    private String typeKey;
    private String typeName;
    private String createUser;
    private Date createTime;
    private Integer deleted;

    @Override
    public String toString() {
        return "TypeClass{" +
                "id='" + id + '\'' +
                ", typeKey='" + typeKey + '\'' +
                ", typeName='" + typeName + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", deleted=" + deleted +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}

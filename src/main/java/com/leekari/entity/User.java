package com.leekari.entity;

import java.util.Date;

/**
 * @author litao
 * @date 2020/7/30 17:34
 * @description
 */
public class User {
    private String id;
    private String loginName;
    private String username;
    private String phone;
    private String password;
    private String role;
    private String picture;
    private Date createTime;
    private Date lastOperationTime;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", loginName='" + loginName + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", picture='" + picture + '\'' +
                ", createTime=" + createTime +
                ", lastOperationTime=" + lastOperationTime +
                '}';
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastOperationTime() {
        return lastOperationTime;
    }

    public void setLastOperationTime(Date lastOperationTime) {
        this.lastOperationTime = lastOperationTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

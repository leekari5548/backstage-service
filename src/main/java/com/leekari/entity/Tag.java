package com.leekari.entity;

import java.util.Date;

/**
 * @author litao
 * @date 2020/8/28 10:59
 * @description
 */
public class Tag {
    private String id;
    private String tagName;
    private String associateUser;
    private Date lastOperationTime;
    private Integer deleted;

    @Override
    public String toString() {
        return "Tag{" +
                "id='" + id + '\'' +
                ", tagName='" + tagName + '\'' +
                ", associateUser='" + associateUser + '\'' +
                ", lastOperationTime='" + lastOperationTime + '\'' +
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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getAssociateUser() {
        return associateUser;
    }

    public void setAssociateUser(String associateUser) {
        this.associateUser = associateUser;
    }

    public Date getLastOperationTime() {
        return lastOperationTime;
    }

    public void setLastOperationTime(Date lastOperationTime) {
        this.lastOperationTime = lastOperationTime;
    }
}

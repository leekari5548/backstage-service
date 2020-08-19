package com.leekari.entity;

import java.util.Date;

/**
 * @author litao
 * @date 2020/8/19 20:44
 * @description
 */
public class CarouselImage {
    private String id;
    private String imageId;
    private Date createTime;
    private String createUser;
    private Integer deleted;
    private Integer source;
    private Integer order;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "CarouselImage{" +
                "id='" + id + '\'' +
                ", imageId='" + imageId + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", deleted=" + deleted +
                ", source=" + source +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}

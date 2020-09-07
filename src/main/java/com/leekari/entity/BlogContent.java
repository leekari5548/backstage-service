package com.leekari.entity;

import java.util.Date;

/**
 * @author litao
 * @date 2020/8/25 16:58
 * @description
 */
public class BlogContent {
    private String id;
    private String articleId;
    private Date createTime;
    private Integer deleted;
    private String createUser;
    private Integer status;
    private Integer stars;
    private Integer likes;
    private Integer comments;
    private Integer views;
    private String tags;
    private String type;

    @Override
    public String toString() {
        return "BlogContent{" +
                "id='" + id + '\'' +
                ", articleId='" + articleId + '\'' +
                ", createTime=" + createTime +
                ", deleted=" + deleted +
                ", createUser='" + createUser + '\'' +
                ", status=" + status +
                ", stars=" + stars +
                ", likes=" + likes +
                ", comments=" + comments +
                ", views=" + views +
                ", tags='" + tags + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }
}

package com.leekari.entity.view;

import java.util.Date;

/**
 * @author litao
 * @date 2020/8/25 20:15
 * @description
 */
public class BlogList {
    private String id;
    private String title;
    private String description;
    private Date createTime;
    private String createUser;
    private String createUserImageUrl;
    private Integer stars;
    private Integer likes;
    private Integer comments;
    private Integer views;
    private String mdContent;
    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "BlogList{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", createUserImageUrl='" + createUserImageUrl + '\'' +
                ", stars=" + stars +
                ", likes=" + likes +
                ", comments=" + comments +
                ", views=" + views +
                ", mdContent='" + mdContent + '\'' +
                '}';
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCreateUserImageUrl() {
        return createUserImageUrl;
    }

    public void setCreateUserImageUrl(String createUserImageUrl) {
        this.createUserImageUrl = createUserImageUrl;
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

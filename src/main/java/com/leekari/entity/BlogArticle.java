package com.leekari.entity;

import java.util.Date;

/**
 * @author litao
 * @date 2020/8/25 16:56
 * @description
 */
public class BlogArticle {
    private String id;
    private String articleContentMd;
    private String articleContentHtml;
    private String createUser;
    private Date createTime;
    private Integer deleted;
    private String articleTitle;

    @Override
    public String toString() {
        return "BlogArticle{" +
                "id='" + id + '\'' +
                ", articleContentMd='" + articleContentMd + '\'' +
                ", articleContentHtml='" + articleContentHtml + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", deleted=" + deleted +
                ", articleTitle='" + articleTitle + '\'' +
                '}';
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleContentMd() {
        return articleContentMd;
    }

    public void setArticleContentMd(String articleContentMd) {
        this.articleContentMd = articleContentMd;
    }

    public String getArticleContentHtml() {
        return articleContentHtml;
    }

    public void setArticleContentHtml(String articleContentHtml) {
        this.articleContentHtml = articleContentHtml;
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

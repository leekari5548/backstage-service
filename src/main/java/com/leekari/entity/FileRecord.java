package com.leekari.entity;

import java.util.Date;

/**
 * @author litao
 * @date 2020/7/29 15:38
 * @description
 */
public class FileRecord {
    private String id;
    private String filename;
    private String md5;
    private Date createTime;
    private String createUser;
    private String bucket;
    private String fileType;
    private String storeFilename;
    private Integer delete;

    @Override
    public String toString() {
        return "FileRecord{" +
                "id='" + id + '\'' +
                ", filename='" + filename + '\'' +
                ", md5='" + md5 + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", bucket='" + bucket + '\'' +
                ", fileType='" + fileType + '\'' +
                ", storeFilename='" + storeFilename + '\'' +
                ", delete=" + delete +
                '}';
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    public String getStoreFilename() {
        return storeFilename;
    }

    public void setStoreFilename(String storeFilename) {
        this.storeFilename = storeFilename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
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

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}

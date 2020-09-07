package com.leekari.define;

/**
 * @author litao
 * @date 2020/9/7 10:57
 * @description
 */
public enum ContentTypeEnum {
    BLOG(0, "blog");

    public int code;
    public String name;
    ContentTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }
}

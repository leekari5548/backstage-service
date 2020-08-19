package com.leekari.define;

/**
 * @author litao
 * @date 2020/7/31 11:36
 * @description
 */
public enum RoleEnum {
    NORMAL_USER(0, "normal");
    public int code;
    public String name;

    RoleEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}

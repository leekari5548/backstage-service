package com.leekari.define;

/**
 * @author litao
 * @date 2020/8/21 18:44
 * @description
 */
public enum MenuTypeEnum {
    PC_USER_MENU(0, "PC端用户界面菜单"),PC_ADMIN_MENU(1, "PC端普通管理员菜单"),
    PC_SUPER_ADMIN_MENU(2, "PC端超级管理原界面菜单");
    public int code;
    public String name;

    MenuTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}

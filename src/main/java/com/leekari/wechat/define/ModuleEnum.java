package com.leekari.wechat.define;

/**
 * @author litao
 * @date 2020/7/29 14:58
 * @description
 */
public enum ModuleEnum {
    FILE_MODULE(1, "file_module"),LOGIN_MODULE(2, "login_module");
    public int code;
    public String name;

    ModuleEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}

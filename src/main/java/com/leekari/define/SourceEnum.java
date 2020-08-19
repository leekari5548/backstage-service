package com.leekari.define;

/**
 * @author litao
 * @date 2020/8/18 09:18
 * @description
 */
public enum SourceEnum {
    COMMON_SOURCE(0, "common-source"), WECHAT_SOURCE(1, "wechat-source"),
    VUE_SOURCE(2, "vue-source"), REACT_SOURCE(3, "react-source"), TEMP_SOURCE(-1, "temp");

    public int code;
    public String name;
    SourceEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SourceEnum getSourceEnum(Integer code) {
        if (code == null) return null;
        for (SourceEnum sourceEnum: SourceEnum.values()) {
            if (code.equals(sourceEnum.code)) {
                return sourceEnum;
            }
        }
        return null;
    }
}

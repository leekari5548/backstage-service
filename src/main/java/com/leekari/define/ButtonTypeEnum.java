package com.leekari.define;

/**
 * @author litao
 * @date 2020/9/7 10:28
 * @description
 */
public enum ButtonTypeEnum {
    LIKE(0, "like"), STAR(1, "star"), VIEW(2, "view");

    public int code;
    public String name;
    ButtonTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public static ButtonTypeEnum getButtonTypeEnum(Integer code) {
        if (code == null) return null;
        for (ButtonTypeEnum buttonTypeEnum: ButtonTypeEnum.values()) {
            if (code.equals(buttonTypeEnum.code)) {
                return buttonTypeEnum;
            }
        }
        return null;
    }
}

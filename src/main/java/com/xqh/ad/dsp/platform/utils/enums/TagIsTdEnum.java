package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Created by samson.huang on 2019/7/29
 */
@Getter
@AllArgsConstructor
public enum TagIsTdEnum {
    YES(1, "请求TD"),
    NO(2, "不请求TD"),

    ;

    private int code;
    private String name;

    public static TagIsTdEnum get(int code) {
        for (TagIsTdEnum typeEnum : TagIsTdEnum.values()) {
            if (Objects.equals(code, typeEnum.getCode())) {
                return typeEnum;
            }
        }
        return null;
    }
}

package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Created by samson.huang on 2019/7/29
 */
@Getter
@AllArgsConstructor
public enum TagTypeEnum {

    NONE(0, "无"),
    WHILE(1, "白名单"),
    BLACK(2, "黑名单"),
    ;

    private int code;
    private String name;

    public static TagTypeEnum get(int code) {
        for (TagTypeEnum typeEnum : TagTypeEnum.values()) {
            if (Objects.equals(code, typeEnum.getCode())) {
                return typeEnum;
            }
        }
        return null;
    }
}

package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Created by samson.huang on 2019/5/19
 */
@Getter
@AllArgsConstructor
public enum AdplacementPlatformEnum {

    PC(1, "pc"),
    MOBILE(2, "移动"),
    ;

    private int code;
    private String name;

    public static String getName(Integer code) {
        if (null == code) {
            return "";
        }

        for (AdplacementPlatformEnum en : AdplacementPlatformEnum.values()) {
            if (Objects.equals(en.getCode(), code)) {
                return en.getName();
            }
        }
        return "";
    }
}

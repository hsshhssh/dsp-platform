package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 广告位类型
 * Created by samson.huang on 2019/5/19
 */
@Getter
@AllArgsConstructor
public enum AdplacementTypeEnum {

    BANNER(13, "banner"),
    NATIVE(14, "原生"),
    VIDEO(15, "视频贴片"),
    ;

    private int code;
    private String name;

    public static String getName(Integer code) {
        if (null == code) {
            return "";
        }

        for (AdplacementTypeEnum en : AdplacementTypeEnum.values()) {
            if (Objects.equals(en.getCode(), code)) {
                return en.getName();
            }
        }
        return "";
    }
}

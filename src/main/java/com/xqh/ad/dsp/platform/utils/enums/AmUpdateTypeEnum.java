package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Created by samson.huang on 2019/8/10
 */
@Getter
@AllArgsConstructor
public enum AmUpdateTypeEnum {

    PRICE(1, "修改价格"),
    STATUS(2, "状态修改"),
    BUDGET(3, "修改预算"),
    ;

    private int code;
    private String name;

    public static String getName(Integer code) {
        if (null == code) {
            return "";
        }

        for (AmUpdateTypeEnum en : AmUpdateTypeEnum.values()) {
            if (Objects.equals(en.getCode(), code)) {
                return en.getName();
            }
        }
        return "";
    }
}

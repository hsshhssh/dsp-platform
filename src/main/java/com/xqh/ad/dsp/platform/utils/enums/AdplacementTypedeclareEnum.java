package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Created by samson.huang on 2019/5/19
 */
@Getter
@AllArgsConstructor
public enum AdplacementTypedeclareEnum {

    S_PIC(0, "单图"),
    S_PIC_S_ART(1, "一图一文"),
    S_PIC_T_ART(2, "一图二文"),
    ;

    private int code;
    private String name;

    public static String getName(Integer code) {
        if (null == code) {
            return "";
        }

        for (AdplacementTypedeclareEnum en : AdplacementTypedeclareEnum.values()) {
            if (Objects.equals(en.getCode(), code)) {
                return en.getName();
            }
        }
        return "";
    }
}

package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Created by samson.huang on 2019/7/4
 */
@Getter
@AllArgsConstructor
public enum OdsTypeEnum {

    PMEDIAID(1, "平台媒体id"),
    MEDIAID(2, "媒体id"),
    PADPLACEMENTID(3, "广告位id"),
    MATERIALID(4, "素材id"),
    ;

    private int code;
    private String name;

    public static String getName(Integer code) {
        if (null == code) {
            return "";
        }

        for (OdsTypeEnum en : OdsTypeEnum.values()) {
            if (Objects.equals(en.getCode(), code)) {
                return en.getName();
            }
        }
        return "";
    }
}

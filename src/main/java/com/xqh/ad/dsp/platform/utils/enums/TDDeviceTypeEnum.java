package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Created by samson.huang on 2019/7/28
 */
@Getter
@AllArgsConstructor
public enum TDDeviceTypeEnum {

    IMEI(66, "IMEI"),
    IDFA(67, "IDFA"),
    ;

    private int tdCode;
    private String code;

    public static TDDeviceTypeEnum getByCode(String code) {
        for (TDDeviceTypeEnum typeEnum : TDDeviceTypeEnum.values()) {
            if (Objects.equals(code, typeEnum.getCode())) {
                return typeEnum;
            }
        }
        return null;
    }
}

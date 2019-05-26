package com.xqh.ad.dsp.platform.utils.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 展现类型
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS("00000", "成功"),

    AD_NOT_EXIT("10000", "广告位不存在"),
    ;

    private String code;
    private String message;


}

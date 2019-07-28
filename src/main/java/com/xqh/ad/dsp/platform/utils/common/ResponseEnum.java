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

    UNKNOWN("00001", "未知异常"),
    AD_NOT_EXIT("10000", "广告位不存在"),
    ADPLACEMENT_MATERIAL_NOT_EXIT("10001", "策略不存在"),
    MATERIAL_NOT_EXIT("10002", "素材不存在"),
    ;

    private String code;
    private String message;


}

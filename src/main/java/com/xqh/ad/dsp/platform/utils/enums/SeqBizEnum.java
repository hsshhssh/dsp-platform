package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeqBizEnum {

    PADPLACEMENT("AD", "平台广告位"),

    BIDRESPONSE_ID("RESP", "BidResponse id"),

    CRID("CRID", "创意id"),

    ADM_AD_ID("AAID", "dsp段素材id"),
    ;

    private String code;
    private String name;

}

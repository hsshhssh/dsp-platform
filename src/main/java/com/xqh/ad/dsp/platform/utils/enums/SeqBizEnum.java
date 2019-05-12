package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeqBizEnum {

    PADPLACEMENT("AD", "平台广告位"),

    BIDRESPONSE_ID("RESP", "BidResponse id"),
    ;

    private String code;
    private String name;

}

package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by samson.huang on 2019/7/28
 */
@Getter
@AllArgsConstructor
public enum TagResultEnum {

    YES(1, "匹配"),
    NO(2, "不匹配"),
    ;

    private int code;
    private String name;
}

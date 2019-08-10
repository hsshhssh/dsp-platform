package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by samson.huang on 2019/8/10
 */
@Getter
@AllArgsConstructor
public enum DelEnum {

    NO(1, "恢复"),
    YES(2, "删除"),
    ;

    private int code;
    private String name;

}

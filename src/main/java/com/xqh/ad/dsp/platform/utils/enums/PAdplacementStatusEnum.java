package com.xqh.ad.dsp.platform.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Created by samson.huang on 2019/5/4
 */
@Getter
@AllArgsConstructor
public enum PAdplacementStatusEnum {

    INIT(1, "初始化"),
    UPLOAD_MATERIAL(2, "已上传素材"),
    REQUEST(3, "已竞价"),
    CALLBACK(4, "已回调"),
    ;

    private int code;
    private String name;

    public static String getName(Integer code) {
        if (null == code) {
            return "";
        }

        for (PAdplacementStatusEnum en : PAdplacementStatusEnum.values()) {
            if (Objects.equals(en.getCode(), code)) {
                return en.getName();
            }
        }
        return "";
    }
}

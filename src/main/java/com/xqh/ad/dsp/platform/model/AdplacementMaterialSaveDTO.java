package com.xqh.ad.dsp.platform.model;

import lombok.Data;

/**
 * Created by samson.huang on 2019/7/28
 */
@Data
public class AdplacementMaterialSaveDTO {

    private Long id;
    private String name;

    private Integer pmediaid;
    private String adplacementid;

    private Long materialid;

    private String hours;
    private String city;
    private String network;
    private String tag;

    private String sex;
    private String age;

}

package com.xqh.ad.dsp.platform.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

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

    private List<String> hoursList;
    private List<List<String>> cityList;
    private List<String> networkList;
    private List<String> tagList;

    private BigDecimal price;
    private String remark;
    private String pmoUrl;
    private String cmoUrl;

    private String sex;
    private String age;

}

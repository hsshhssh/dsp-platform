package com.xqh.ad.dsp.platform.model;

import com.xqh.ad.dsp.platform.utils.common.PageDTO;
import lombok.Data;

/**
 * Created by samson.huang on 2019/5/19
 */
@Data
public class MaterialListDTO extends PageDTO {

    // 平台媒体
    private Integer pmediaid;
    // 广告位
    private String adplacementid;
    // 创意id
    private String crid;
}

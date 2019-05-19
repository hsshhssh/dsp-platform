package com.xqh.ad.dsp.platform.model;

import com.xqh.ad.dsp.platform.utils.common.PageDTO;
import lombok.Data;

/**
 * Created by samson.huang on 2019/5/19
 */
@Data
public class AdplacementListDTO extends PageDTO {

    // 平台媒体
    private Integer pmediaid;
    // 广告位
    private String adplacementid;
    // 展现类型
    private Integer type;
    // 渠道
    private Integer platform;
    // 广告位类型
    private Integer typedeclare;
    // 状态
    private Integer status;


}

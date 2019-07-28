package com.xqh.ad.dsp.platform.model;

import com.xqh.ad.dsp.platform.utils.common.PageDTO;
import lombok.Data;

/**
 * Created by samson.huang on 2019/7/28
 */
@Data
public class AdpMaterialListDTO extends PageDTO {

    private String name_like;
    private String adplacementid;
    private String mediaid;
    private Long materialid;
}

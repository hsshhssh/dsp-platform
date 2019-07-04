package com.xqh.ad.dsp.platform.model;

import com.xqh.ad.dsp.platform.utils.common.PageDTO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by samson.huang on 2019/7/4
 */
@Data
public class OdsBidDateListDTO extends PageDTO {

    /**
     * 统计日期时间戳
     */
    private Long odsTimestamp_gte_ignore;
    private Long odsTimestamp_lte_ignore;
    private LocalDateTime ods_date_gte;
    private LocalDateTime ods_date_lte;
    /**
     * 统计维度
     */
    private Integer ods_type;

    /**
     * 统计维度具体值
     */
    private String ods_value_like;

}

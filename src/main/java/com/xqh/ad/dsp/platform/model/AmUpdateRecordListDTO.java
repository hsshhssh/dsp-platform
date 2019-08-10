package com.xqh.ad.dsp.platform.model;

import com.xqh.ad.dsp.platform.utils.common.PageDTO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by samson.huang on 2019/8/10
 */
@Data
public class AmUpdateRecordListDTO extends PageDTO {

    /**
     * 时间
     */
    private Long timestamp_gte_ignore;
    private Long timestamp_lte_ignore;
    private LocalDateTime record_time_gte;
    private LocalDateTime record_time_lte;

    private Integer type;
    private String amname_like;
}

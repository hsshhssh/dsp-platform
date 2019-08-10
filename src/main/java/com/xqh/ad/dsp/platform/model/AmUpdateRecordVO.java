package com.xqh.ad.dsp.platform.model;

import com.xqh.ad.dsp.platform.mybatisplus.entity.TAmUpdateRecord;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.enums.AmUpdateTypeEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * Created by samson.huang on 2019/8/10
 */
@Data
public class AmUpdateRecordVO {

    private Long id;
    private Long amid;
    private String amname;
    private Integer type;
    private String updateBefore;
    private String updateAfter;
    private LocalDateTime recordTime;

    private String recordTimeStr;
    private String typeStr;

    public AmUpdateRecordVO() {
    }

    public AmUpdateRecordVO(TAmUpdateRecord record) {
        BeanUtils.copyProperties(record, this);
        this.recordTimeStr = CommonUtils.localDateTimeToStr(this.recordTime);
        this.typeStr = AmUpdateTypeEnum.getName(this.type);
    }
}

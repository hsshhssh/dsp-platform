package com.xqh.ad.dsp.platform.model;

import com.xqh.ad.dsp.platform.mybatisplus.entity.TOdsBidDate;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.enums.OdsTypeEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by samson.huang on 2019/7/4
 */
@Data
public class OdsBidDateVO {

    private Integer id;
    private LocalDateTime odsDate;
    private Integer odsType;
    private String odsValue;
    private Integer bid;
    private Integer bidsucc;
    private BigDecimal bidprice;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    private String odsTypeStr;
    private String odsDateStr;

    public OdsBidDateVO() {
    }

    public OdsBidDateVO(TOdsBidDate odsBidDate) {
        BeanUtils.copyProperties(odsBidDate, this);
        this.odsTypeStr = OdsTypeEnum.getName(this.odsType);
        this.odsDateStr = CommonUtils.localDateTimeToStr(this.odsDate);
    }
}

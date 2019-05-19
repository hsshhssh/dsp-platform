package com.xqh.ad.dsp.platform.model;

import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.utils.enums.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * Created by samson.huang on 2019/5/19
 */
@Data
public class AdplacementVO {

    private Long id;
    private String padplacementid;
    private Integer pmediaid;
    private String adplacementid;
    private Integer mediaid;
    private String medianame;
    private Integer type;
    private Integer platform;
    private Integer typedeclare;
    private String size;
    private Integer bidfloor;
    private String allowmaterial;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    private String pmediaStr;
    private String typeStr;
    private String platformStr;
    private String typedeclareStr;
    private String statusStr;


    public AdplacementVO() {
    }

    public AdplacementVO(TPlatformAdplacement adplacement) {
        BeanUtils.copyProperties(adplacement, this);

        this.pmediaStr = PMediaEnum.getName(this.pmediaid);
        this.typeStr = AdplacementTypeEnum.getName(this.type);
        this.platformStr = AdplacementPlatformEnum.getName(this.platform);
        this.typedeclareStr = AdplacementTypedeclareEnum.getName(this.typedeclare);
        this.statusStr = PAdplacementStatusEnum.getName(this.status);

    }

}

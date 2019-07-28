package com.xqh.ad.dsp.platform.model;

import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformMaterial;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by samson.huang on 2019/5/19
 */
@Data
public class MaterialVO {

    private Long id;
    private String name;
    private String padplacementid;
    private Integer pmediaid;
    private String adplacementid;
    private String adplacementname;
    private BigDecimal price;
    private String adm;
    private String crid;
    private String adtype;
    private String ext;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    private String pmediaStr;

    public MaterialVO() {
    }


    public MaterialVO(TPlatformMaterial material) {
        BeanUtils.copyProperties(material, this);

        this.pmediaStr = PMediaEnum.getName(this.pmediaid);
    }
}

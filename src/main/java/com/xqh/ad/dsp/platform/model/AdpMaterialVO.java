package com.xqh.ad.dsp.platform.model;

import com.alibaba.fastjson.JSONObject;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TAdplacementMaterial;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * Created by samson.huang on 2019/7/28
 */
@Data
public class AdpMaterialVO {

    private Long id;
    private String name;
    private String padplacementid;
    private Integer pmediaid;
    private String adplacementid;
    private String adplacementname;
    private Integer mediaid;
    private Long materialid;
    private String status;
    private String hours;
    private String city;
    private String network;
    private String sex;
    private String age;
    private String tag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String pmediaStr;

    // 广告位信息
    private JSONObject adplacementInfo;
    // 素材信息
    private JSONObject materialInfo;

    public AdpMaterialVO() {
    }

    public AdpMaterialVO(TAdplacementMaterial tAdplacementMaterial) {
        BeanUtils.copyProperties(tAdplacementMaterial, this);
        this.pmediaStr = PMediaEnum.getName(this.pmediaid);
    }
}

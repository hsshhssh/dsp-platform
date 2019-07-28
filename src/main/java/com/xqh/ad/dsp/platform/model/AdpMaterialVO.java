package com.xqh.ad.dsp.platform.model;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TAdplacementMaterial;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

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
    private String medianame;
    private Long materialid;
    private String materialname;
    private String status;
    private String hours;
    private String city;
    private String network;
    private String sex;
    private String age;
    private String tag;
    private String remark;
    private String pmoUrl;
    private String cmoUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String pmediaStr;
    private String createTimeStr;
    private String updateTimeStr;

    // 广告位信息
    private JSONObject adplacementInfo;
    // 素材信息
    private JSONObject materialInfo;

    private List<String> hoursList;
    private List<String> cityList;
    private List<String> networkList;
    private List<String> tagList;

    public AdpMaterialVO() {
    }

    public AdpMaterialVO(TAdplacementMaterial tAdplacementMaterial) {
        BeanUtils.copyProperties(tAdplacementMaterial, this);
        this.pmediaStr = PMediaEnum.getName(this.pmediaid);
        this.createTimeStr = CommonUtils.localDateTimeToStr(this.createTime);
        this.updateTimeStr = CommonUtils.localDateTimeToStr(this.updateTime);
    }
}

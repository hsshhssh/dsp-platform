package com.xqh.ad.dsp.platform.model;

import com.alibaba.fastjson.JSONObject;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TAdplacementMaterial;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.enums.DelEnum;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
    private BigDecimal price;
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
    private Integer del;
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
    private List<List<String>> cityList;
    private List<String> networkList;
    private List<String> tagList;

    private boolean priceEdit = false;
    private boolean statusBoolean;
    private boolean delBoolean;

    public AdpMaterialVO() {
    }

    public AdpMaterialVO(TAdplacementMaterial tAdplacementMaterial) {
        BeanUtils.copyProperties(tAdplacementMaterial, this);
        this.pmediaStr = PMediaEnum.getName(this.pmediaid);
        this.createTimeStr = CommonUtils.localDateToStr(this.createTime);
        this.updateTimeStr = CommonUtils.localDateToStr(this.updateTime);

        this.statusBoolean = Objects.equals(this.status, CommonUtils.ENABLE_STATUS);
        this.delBoolean = Objects.equals(this.del, DelEnum.NO.getCode());
    }
}

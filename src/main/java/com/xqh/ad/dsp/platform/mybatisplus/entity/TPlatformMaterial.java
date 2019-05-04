package com.xqh.ad.dsp.platform.mybatisplus.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 素材表
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-02
 */
public class TPlatformMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 平台广告id
     */
    @TableField("padplacementid")
    private String padplacementid;

    /**
     * 平台媒体id
     */
    @TableField("pmediaid")
    private Integer pmediaid;

    /**
     * 广告位id
     */
    @TableField("adplacementid")
    private String adplacementid;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 广告回复内容
     */
    @TableField("adm")
    private String adm;

    /**
     * 创意id
     */
    @TableField("crid")
    private String crid;

    /**
     * 素材类型
     */
    @TableField("adtype")
    private String adtype;

    /**
     * 扩展字段
     */
    @TableField("ext")
    private String ext;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPadplacementid() {
        return padplacementid;
    }

    public void setPadplacementid(String padplacementid) {
        this.padplacementid = padplacementid;
    }

    public Integer getPmediaid() {
        return pmediaid;
    }

    public void setPmediaid(Integer pmediaid) {
        this.pmediaid = pmediaid;
    }

    public String getAdplacementid() {
        return adplacementid;
    }

    public void setAdplacementid(String adplacementid) {
        this.adplacementid = adplacementid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        this.adm = adm;
    }

    public String getCrid() {
        return crid;
    }

    public void setCrid(String crid) {
        this.crid = crid;
    }

    public String getAdtype() {
        return adtype;
    }

    public void setAdtype(String adtype) {
        this.adtype = adtype;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TPlatformMaterial{" +
        "id=" + id +
        ", padplacementid=" + padplacementid +
        ", pmediaid=" + pmediaid +
        ", adplacementid=" + adplacementid +
        ", price=" + price +
        ", adm=" + adm +
        ", crid=" + crid +
        ", adtype=" + adtype +
        ", ext=" + ext +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

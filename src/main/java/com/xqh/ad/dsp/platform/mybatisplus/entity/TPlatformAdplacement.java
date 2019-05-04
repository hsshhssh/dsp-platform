package com.xqh.ad.dsp.platform.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 平台广告位表
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-02
 */
public class TPlatformAdplacement implements Serializable {

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
     * 媒体id
     */
    @TableField("mediaid")
    private Integer mediaid;

    /**
     * 媒体名称
     */
    @TableField("medianame")
    private String medianame;

    /**
     * 广告位类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 渠道
     */
    @TableField("platform")
    private Integer platform;

    /**
     * 广告位类型
     */
    @TableField("typedeclare")
    private Integer typedeclare;

    /**
     * 广告位尺寸
     */
    @TableField("size")
    private String size;

    /**
     * 广告位低价 分/CPM
     */
    @TableField("bidfloor")
    private Integer bidfloor;

    /**
     * 允许素材类型
     */
    @TableField("allowmaterial")
    private String allowmaterial;

    /**
     * 状态 1初始化 2已上传素材 3已竞价 4已回调
     */
    @TableField("status")
    private Integer status;

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

    public Integer getMediaid() {
        return mediaid;
    }

    public void setMediaid(Integer mediaid) {
        this.mediaid = mediaid;
    }

    public String getMedianame() {
        return medianame;
    }

    public void setMedianame(String medianame) {
        this.medianame = medianame;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getTypedeclare() {
        return typedeclare;
    }

    public void setTypedeclare(Integer typedeclare) {
        this.typedeclare = typedeclare;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getBidfloor() {
        return bidfloor;
    }

    public void setBidfloor(Integer bidfloor) {
        this.bidfloor = bidfloor;
    }

    public String getAllowmaterial() {
        return allowmaterial;
    }

    public void setAllowmaterial(String allowmaterial) {
        this.allowmaterial = allowmaterial;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return "TPlatformAdplacement{" +
        "id=" + id +
        ", padplacementid=" + padplacementid +
        ", pmediaid=" + pmediaid +
        ", adplacementid=" + adplacementid +
        ", mediaid=" + mediaid +
        ", medianame=" + medianame +
        ", type=" + type +
        ", platform=" + platform +
        ", typedeclare=" + typedeclare +
        ", size=" + size +
        ", bidfloor=" + bidfloor +
        ", allowmaterial=" + allowmaterial +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

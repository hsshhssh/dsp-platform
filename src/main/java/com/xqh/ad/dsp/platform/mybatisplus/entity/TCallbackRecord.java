package com.xqh.ad.dsp.platform.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 回调记录
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-12
 */
public class TCallbackRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 平台媒体id
     */
    @TableField("pmediaid")
    private Integer pmediaid;

    /**
     * 曝光id
     */
    @TableField("impid")
    private String impid;

    /**
     * bid impression id from dsp response
     */
    @TableField("bidimpid")
    private String bidimpid;

    /**
     * 价格
     */
    @TableField("callback_price")
    private BigDecimal callbackPrice;

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

    public Integer getPmediaid() {
        return pmediaid;
    }

    public void setPmediaid(Integer pmediaid) {
        this.pmediaid = pmediaid;
    }

    public String getImpid() {
        return impid;
    }

    public void setImpid(String impid) {
        this.impid = impid;
    }

    public String getBidimpid() {
        return bidimpid;
    }

    public void setBidimpid(String bidimpid) {
        this.bidimpid = bidimpid;
    }

    public BigDecimal getCallbackPrice() {
        return callbackPrice;
    }

    public void setCallbackPrice(BigDecimal callbackPrice) {
        this.callbackPrice = callbackPrice;
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
        return "TCallbackRecord{" +
        "id=" + id +
        ", pmediaid=" + pmediaid +
        ", impid=" + impid +
        ", bidimpid=" + bidimpid +
        ", callbackPrice=" + callbackPrice +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

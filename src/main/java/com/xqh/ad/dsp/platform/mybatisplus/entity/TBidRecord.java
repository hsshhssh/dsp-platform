package com.xqh.ad.dsp.platform.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 竞价请求记录表
 * </p>
 *
 * @author K神带你飞
 * @since 2019-07-02
 */
@Data
public class TBidRecord implements Serializable {

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
     * 请求id
     */
    @TableField("requestid")
    private String requestid;

    /**
     * 响应id
     */
    @TableField("bidid")
    private String bidid;

    /**
     * 媒体id
     */
    @TableField("mediaid")
    private String mediaid;

    /**
     * 广告为id
     */
    @TableField("padplacementid")
    private String padplacementid;

    /**
     * 素材id
     */
    @TableField("materialid")
    private String materialid;

    /**
     * 曝光id（逗号分割）
     */
    @TableField("impid")
    private String impid;

    /**
     * 请求参数
     */
    @TableField("bidrequest")
    private String bidrequest;

    /**
     * 响应参数
     */
    @TableField("bidresponse")
    private String bidresponse;

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

    /**
     * 广告位id  BidRequest.Imp.tagid
     */
    @TableField("impTagId")
    private String impTagId;

    /**
     * 用户设备号 BidRequest.Device.dpid
     */
    @TableField("deviceDpId")
    private String deviceDpId;

    /**
     * 运营商 BidRequest.Device.carrier
     */
    @TableField("deviceCarrier")
    private String deviceCarrier;

    /**
     * 设备类型 BidRequest.Device.devicetype
     */
    @TableField("deviceType")
    private String deviceType;

    /**
     * 推广id
     */
    @TableField("amId")
    private String amId;

    /**
     * 区域
     */
    @TableField("cityId")
    private String cityId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

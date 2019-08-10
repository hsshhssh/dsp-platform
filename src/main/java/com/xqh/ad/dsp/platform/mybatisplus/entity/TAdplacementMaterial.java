package com.xqh.ad.dsp.platform.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 投放计划表
 * </p>
 *
 * @author K神带你飞
 * @since 2019-07-27
 */
@Data
public class TAdplacementMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 计划名称
     */
    @TableField("name")
    private String name;

    /**
     * 平台广告id
     */
    @TableField("padplacementid")
    private String padplacementid;

    /**
     * 广告位名称
     */
    @TableField("adplacementname")
    private String adplacementname;

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
     * 素材id
     */
    @TableField("materialid")
    private Long materialid;

    /**
     * 素材名称
     */
    @TableField("materialname")
    private String materialname;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

   /**
     * 状态 1=>启动 非1=>暂停
     */
    @TableField("status")
    private String status;

    /**
     * 投放时间
     */
    @TableField("hours")
    private String hours;

    /**
     * 投放地区
     */
    @TableField("city")
    private String city;

    /**
     * 网络状态
     */
    @TableField("network")
    private String network;

    /**
     * 用户性别(弃用)
     */
    @TableField("sex")
    private String sex;

    /**
     * 用户年龄(弃用)
     */
    @TableField("age")
    private String age;

    /**
     * 自定义人群
     */
    @TableField("tag")
    private String tag;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 曝光监控对象url
     */
    @TableField("pmoUrl")
    private String pmoUrl;

    /**
     * 点击监控对象url
     */
    @TableField("cmoUrl")
    private String cmoUrl;

    /**
     * 删除标识 0否 1是
     */
    @TableField("del")
    private Integer del;

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
}

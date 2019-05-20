package com.xqh.ad.dsp.platform.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 平台广告位表
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-02
 */
@Data
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
     * 广告位名称
     */
    @TableField("adplacementname")
    private String adplacementname;

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
}

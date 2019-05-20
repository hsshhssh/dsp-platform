package com.xqh.ad.dsp.platform.mybatisplus.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 素材表
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-02
 */
@Data
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


}

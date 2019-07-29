package com.xqh.ad.dsp.platform.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author K神带你飞
 * @since 2019-07-27
 */
@Data
public class TTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标签名称
     */
    @TableField("name")
    private String name;

    /**
     * TD密钥
     */
    @TableField("tdKey")
    private String tdKey;

    /**
     * 1白名单 2黑名单
     */
    @TableField("tagType")
    private Integer tagType;


    /**
     * 是否请求TD 1是 2否
     */
    @TableField("isTd")
    private Integer isTd;


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

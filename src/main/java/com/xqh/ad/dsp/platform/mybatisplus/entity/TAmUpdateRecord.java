package com.xqh.ad.dsp.platform.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 计划修改记录表
 * </p>
 *
 * @author K神带你飞
 * @since 2019-08-10
 */
@Data
public class TAmUpdateRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * t_adplacement_material主键
     */
    @TableField("amid")
    private Long amid;

    /**
     * 计划名称
     */
    @TableField("amname")
    private String amname;

    /**
     * 修改类型 1价格 2启用禁用
     */
    @TableField("type")
    private Integer type;

    /**
     * 操作前结果
     */
    @TableField("update_before")
    private String updateBefore;

    /**
     * 操作后结果
     */
    @TableField("update_after")
    private String updateAfter;

    /**
     * 修改时间
     */
    @TableField("record_time")
    private LocalDateTime recordTime;
}

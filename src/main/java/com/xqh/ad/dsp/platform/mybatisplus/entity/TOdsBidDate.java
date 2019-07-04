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
 * 
 * </p>
 *
 * @author K神带你飞
 * @since 2019-06-30
 */
@Data
public class TOdsBidDate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 统计日期
     */
    @TableField("ods_date")
    private LocalDateTime odsDate;

    /**
     * 1=>平台媒体id 2=>媒体id 3=>广告位id 4=>素材id
     */
    @TableField("ods_type")
    private Integer odsType;

    /**
     * 统计维度具体值
     */
    @TableField("ods_value")
    private String odsValue;

    /**
     * 竞价次数
     */
    @TableField("bid")
    private Integer bid;

    /**
     * 竞价成功次数
     */
    @TableField("bidsucc")
    private Integer bidsucc;

    /**
     * 竞价成功总金额
     */
    @TableField("bidprice")
    private BigDecimal bidprice;

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

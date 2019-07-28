package com.xqh.ad.dsp.platform.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * tag-device关联表
 * </p>
 *
 * @author K神带你飞
 * @since 2019-07-27
 */
public class TTagDeviceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备id类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 设备id
     */
    @TableField("deviceId")
    private String deviceId;

    /**
     * tagid
     */
    @TableField("tagid")
    private Long tagid;

    /**
     * 匹配结果 1匹配 2匹配
     */
    @TableField("result")
    private Integer result;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getTagid() {
        return tagid;
    }

    public void setTagid(Long tagid) {
        this.tagid = tagid;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
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
        return "TTagDeviceRecord{" +
        "id=" + id +
        ", type=" + type +
        ", deviceId=" + deviceId +
        ", tagid=" + tagid +
        ", result=" + result +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

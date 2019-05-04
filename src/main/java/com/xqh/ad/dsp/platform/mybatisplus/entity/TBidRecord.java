package com.xqh.ad.dsp.platform.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 竞价请求记录表
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-02
 */
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
     * 请求参数
     */
    @TableField("bidrequest")
    private String bidrequest;

    /**
     * 响应参数
     */
    @TableField("bidresponse")
    private String bidresponse;


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

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getBidid() {
        return bidid;
    }

    public void setBidid(String bidid) {
        this.bidid = bidid;
    }

    public String getBidrequest() {
        return bidrequest;
    }

    public void setBidrequest(String bidrequest) {
        this.bidrequest = bidrequest;
    }

    public String getBidresponse() {
        return bidresponse;
    }

    public void setBidresponse(String bidresponse) {
        this.bidresponse = bidresponse;
    }

    @Override
    public String toString() {
        return "TBidRecord{" +
        "id=" + id +
        ", pmediaid=" + pmediaid +
        ", requestid=" + requestid +
        ", bidid=" + bidid +
        ", bidrequest=" + bidrequest +
        ", bidresponse=" + bidresponse +
        "}";
    }
}

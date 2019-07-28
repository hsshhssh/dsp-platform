package com.xqh.ad.dsp.platform.model;

import lombok.Data;

import java.util.List;

/**
 * Created by samson.huang on 2019/7/28
 */
@Data
public class BidRequest {

    private String id;
    private List<Imp> imp;
    private Object site;
    private Device device;
    private Object app;
    private Object user;
    private Object ver;


    @Data
    public static class Imp {
        private String id;
        private String tagid;
        private Float bidfloor;
        private Object banner;
        private Object video;
        private Integer titlenmax;
        private Integer desclenmax;
        private Integer secure;
        private String supfuncs;
    }

    @Data
    public static class Device {
        private String ua;
        private String ip;
        private String id;
        private String dpid;
        private String carrier;
        private String model;
        private String os;
        private String osv;
        private Integer connectiontype;
        private Integer devicetype;
        private String mac;
        private Object geo;
        private Integer w;
        private Integer h;
        private Integer screenorientation;
    }
}

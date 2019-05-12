package com.xqh.ad.dsp.platform.model;

import lombok.Data;

/**
 * Created by samson.huang on 2019/5/6
 */
@Data
public class BidResponse {
    private String id;
    private String bidid;
    private String seatbid;
    private Integer nbr;

    @Data
    public static class Bid {
        private String id;
        private String impid;
        private float price;
        private String nurl;
        private String adm;
        private String crid;
        private String adtype;
        private String ext;
    }

    @Data
    public static class Adm {
        private String adId;
        private String materialUrl;
        private String dspApiMaterialInnerReqDTO;
    }

    @Data
    public static class DspApiMaterialInnerReqDTO {
        private String url;
        private String landingpage;
        private Integer platform;
        private String materialtype;
        private String pc;
        private String mobile;
        private String video;
        private Integer width;
        private Integer height;
        private String iconurl;
        private String buttontext;
        private Integer installcount;
        private float starcount;
        private String dplurl;
    }

    @Data
    public static class Pc {
        private String title;
        private String desc;
    }

    @Data
    public static class Mobile {
        private String title;
        private String desc;
        private String apkname;
//        private String package;
        private String appstoreid;
        private Integer adtype;
    }

    @Data
    public static class Video {
        private Integer duration;
        private String coverimg;
    }
}

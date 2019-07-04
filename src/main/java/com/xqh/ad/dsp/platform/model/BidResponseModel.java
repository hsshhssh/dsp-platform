package com.xqh.ad.dsp.platform.model;

import lombok.Data;

import java.util.List;

/**
 * Created by samson.huang on 2019/7/3
 */
@Data
public class BidResponseModel {

    private BidResponse bidResponse;

    private List<RequestRecordModel> recordModelList;

    @Data
    public static class RequestRecordModel {

        /**
         * 平台媒体
         */
        private Integer pmediaid;

        /**
         * 广告位id
         */
        private String adplacementid;

        /**
         * 媒体id
         */
        private String materialid;

        /**
         * 曝光id
         */
        private String impid;

    }
}



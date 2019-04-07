package com.xqh.ad.dsp.platform.utils;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by samson.huang on 2019/4/6
 */
public class WinNoticeUtils {


    public static WinNoticeRequest getByRequest(HttpServletRequest request) {
        String impId = request.getParameter("impid");
        String bidImpId = request.getParameter("bidimpid");
        String price = request.getParameter("price");

        WinNoticeRequest winNoticeRequest = new WinNoticeRequest();
        winNoticeRequest.setImpId(impId);
        winNoticeRequest.setBidImpId(bidImpId);
        winNoticeRequest.setPrice(price);
        return winNoticeRequest;
    }


    @Data
    public static class WinNoticeRequest {
        private String impId;
        private String bidImpId;
        private String price;
    }

}

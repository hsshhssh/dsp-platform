package com.xqh.ad.dsp.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.openrtb.OpenRtb;
import com.google.openrtb.json.OpenRtbJsonFactory;
import com.xqh.ad.dsp.platform.model.BidResponse;
import com.xqh.ad.dsp.platform.service.RuanGaoBidService;
import com.xqh.ad.dsp.platform.utils.AsyncUtils;
import com.xqh.ad.dsp.platform.utils.WinNoticeUtils;
import com.xqh.ad.dsp.platform.utils.common.ResponseBean;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 软告接口
 * Created by samson.huang on 2019/5/4
 */
@Slf4j
@RestController
@RequestMapping("ruangao")
public class RuangaoController {

    @Resource
    private RuanGaoBidService ruanGaoBidService;
    @Resource
    private AsyncUtils asyncUtils;

    /**
     * 软告同步广告位接口
     * @return
     */
    @GetMapping("sync/adplacement")
    public ResponseBean<String> asyncAdPlacement() {
        try {
            ruanGaoBidService.asyncAdplacement();
        } catch (Exception e) {
            log.error("软告广告位同步失败", e);
            return new ResponseBean<>("fail");
        }
        return new ResponseBean<>("success");
    }

    /**
     * 竞价发布接口
     */
    @PostMapping("publish")
    public String publish(@RequestBody String requestJson) {
        OpenRtb.BidRequest bidRequest;
        try {
            bidRequest = OpenRtbJsonFactory.create().newReader().readBidRequest(requestJson);
        } catch (IOException e) {
            e.printStackTrace();
            return "解析请求参数异常";
        }

        BidResponse bidResponse = ruanGaoBidService.getBidResponse(bidRequest);
        asyncUtils.handlePublish(bidRequest, requestJson, bidResponse, PMediaEnum.RUAN_GAO);
        return JSONObject.toJSONString(bidRequest);
    }

    /**
     * 竞价回调接口
     */
    @GetMapping("win/notice")
    public String winNotice(HttpServletRequest request) {
        WinNoticeUtils.WinNoticeRequest winNoticeRequest = WinNoticeUtils.getByRequest(request);
        log.info("软告竞价回调-request:{}", JSONObject.toJSONString(winNoticeRequest));
        ruanGaoBidService.callback(winNoticeRequest);
        return "ok";
    }


}

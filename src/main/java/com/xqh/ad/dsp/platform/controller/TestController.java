package com.xqh.ad.dsp.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.openrtb.OpenRtb;
import com.google.openrtb.json.OpenRtbJsonFactory;
import com.googlecode.protobuf.format.JsonFormat;
import com.xqh.ad.dsp.platform.utils.WinNoticeUtils;
import com.xqh.ad.dsp.platform.utils.zkconf.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by samson.huang on 2019/4/6
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestConfig testConfig;

    @PostMapping("publish")
    public String publish(@RequestBody String requestJson) {

        OpenRtb.BidRequest bidRequest;
        try {
            bidRequest = OpenRtbJsonFactory.create().newReader().readBidRequest(requestJson);
        } catch (IOException e) {
            e.printStackTrace();
            return "解析请求参数异常";
        }

        log.info("发布接口-{}", JsonFormat.printToString(bidRequest));
        // OpenRtb.BidResponse bidResponse = OpenRtb.BidResponse.newBuilder()
        //         .setBidid("123")
        //         .setCur("456")
        //         .setId(bidRequest.getId())
        //         .build();
        // return JsonFormat.printToString(bidResponse);
        return testConfig.getBidResponse();
    }

    @GetMapping("win/notice")
    public String winNotice(HttpServletRequest request) {
        WinNoticeUtils.WinNoticeRequest winNoticeRequest = WinNoticeUtils.getByRequest(request);
        log.info("竞价成功回调-request:{}", JSONObject.toJSONString(winNoticeRequest));
        return "ok";
    }

}

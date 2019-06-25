package com.xqh.ad.dsp.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TCallbackRecord;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITCallbackRecordService;
import com.xqh.ad.dsp.platform.utils.WinNoticeUtils;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Created by samson.huang on 2019/6/25
 */
@Slf4j
@RestController("dsp")
public class CallbackController {

    @Resource
    private ITCallbackRecordService callbackRecordService;

    @GetMapping("callback")
    public String winNotice(HttpServletRequest request) {
        WinNoticeUtils.WinNoticeRequest winNoticeRequest = WinNoticeUtils.getByRequest(request);
        log.info("竞价回调-request:{}", JSONObject.toJSONString(winNoticeRequest));


        // 保存回调记录
        try {
            TCallbackRecord callbackRecord = new TCallbackRecord();
            callbackRecord.setPmediaid(PMediaEnum.RUAN_GAO.getCode());
            callbackRecord.setImpid(winNoticeRequest.getImpId());
            callbackRecord.setBidimpid(winNoticeRequest.getBidImpId());
            callbackRecord.setCallbackPrice(BigDecimal.valueOf(Double.valueOf(winNoticeRequest.getPrice())));
            callbackRecordService.save(callbackRecord);
        } catch (NumberFormatException e) {
            log.error("竞价回调-入库过程异常 request:{}", JSONObject.toJSONString(request), e);
        }
        return "ok";
    }
}

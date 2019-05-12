package com.xqh.ad.dsp.platform.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.openrtb.OpenRtb;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xqh.ad.dsp.platform.model.BidResponse;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TBidRecord;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.service.IBidTxService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITBidRecordService;
import com.xqh.ad.dsp.platform.utils.enums.PAdplacementStatusEnum;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by samson.huang on 2019/5/6
 */
@Component
public class AsyncUtils {

    @Resource
    private IBidTxService bidTxService;

    @Async
    public void handlePublish(OpenRtb.BidRequest bidRequest, String requestJson, BidResponse bidResponse, PMediaEnum pMediaEnum) {

        // 修改广告位状态为已竞价
        List<TPlatformAdplacement> adplacementList = Lists.newArrayList();
        // 曝光id
        List<String> impidList = Lists.newArrayList();
        for (OpenRtb.BidRequest.Imp imp : bidRequest.getImpList()) {
            TPlatformAdplacement ad = new TPlatformAdplacement();
            ad.setPmediaid(pMediaEnum.getCode());
            ad.setAdplacementid(imp.getTagid());
            adplacementList.add(ad);

            impidList.add(imp.getId());
        }

        // 保存记录
        TBidRecord tBidRecord = new TBidRecord();
        tBidRecord.setPmediaid(pMediaEnum.getCode());
        tBidRecord.setRequestid(bidResponse.getId());
        tBidRecord.setBidid(bidResponse.getBidid());
        tBidRecord.setImpid(Joiner.on(",").skipNulls().join(impidList));
        tBidRecord.setBidrequest(requestJson);
        tBidRecord.setBidresponse(JSONObject.toJSONString(bidResponse));

        bidTxService.updateAdListAndSaveRecord(adplacementList, tBidRecord);
    }
}

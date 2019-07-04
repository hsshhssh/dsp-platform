package com.xqh.ad.dsp.platform.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xqh.ad.dsp.platform.model.BidResponse;
import com.xqh.ad.dsp.platform.model.BidResponseModel;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TBidRecord;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.service.IBidTxService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by samson.huang on 2019/5/6
 */
@Slf4j
@Component
public class AsyncUtils {

    @Resource
    private IBidTxService bidTxService;
    @Resource
    private ITPlatformAdplacementService adplacementService;

    @Async
    public void handlePublish(String requestJson, BidResponseModel bidResponseModel, PMediaEnum pMediaEnum) {

        BidResponse bidResponse = bidResponseModel.getBidResponse();

        // 修改广告位状态为已竞价
        List<TPlatformAdplacement> adplacementList = Lists.newArrayList();
        // 保存记录
        List<TBidRecord> recordList = Lists.newArrayList();

        // 曝光id
        for (BidResponseModel.RequestRecordModel requestRecordModel : bidResponseModel.getRecordModelList()) {

            TPlatformAdplacement adplacement = adplacementService.selectByAdplacementId(requestRecordModel.getPmediaid(), requestRecordModel.getAdplacementid());
            if (null == adplacement) {
                log.info("异步保存请求记录-广告为不存在 adplacementid:{} pmediaid:{}", requestRecordModel.getAdplacementid(), requestRecordModel.getPmediaid());
                continue;
            }

            // 修改广告状态
            TPlatformAdplacement ad = new TPlatformAdplacement();
            ad.setPmediaid(requestRecordModel.getPmediaid());
            ad.setAdplacementid(requestRecordModel.getAdplacementid());
            adplacementList.add(ad);

            // 保存请求记录
            TBidRecord tBidRecord = new TBidRecord();
            tBidRecord.setPmediaid(pMediaEnum.getCode());
            tBidRecord.setRequestid(bidResponse.getId());
            tBidRecord.setBidid(bidResponse.getBidid());
            tBidRecord.setBidrequest(requestJson);
            tBidRecord.setBidresponse(JSONObject.toJSONString(bidResponse));

            tBidRecord.setImpid(requestRecordModel.getImpid());
            tBidRecord.setPmediaid(requestRecordModel.getPmediaid());
            tBidRecord.setPadplacementid(adplacement.getPadplacementid());
            tBidRecord.setMediaid(String.valueOf(adplacement.getMediaid()));
            tBidRecord.setMaterialid(requestRecordModel.getMaterialid());

            recordList.add(tBidRecord);
        }

        bidTxService.updateAdListAndSaveRecord(adplacementList, recordList);
    }
}

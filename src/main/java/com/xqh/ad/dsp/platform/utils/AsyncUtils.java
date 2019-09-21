package com.xqh.ad.dsp.platform.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xqh.ad.dsp.platform.model.BidResponse;
import com.xqh.ad.dsp.platform.model.BidResponseModel;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TAmUpdateRecord;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TBidRecord;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TTagDeviceRecord;
import com.xqh.ad.dsp.platform.mybatisplus.service.IBidTxService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITAmUpdateRecordService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITTagDeviceRecordService;
import com.xqh.ad.dsp.platform.utils.enums.AmUpdateTypeEnum;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import com.xqh.ad.dsp.platform.utils.enums.TagResultEnum;
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
    @Resource
    private ITTagDeviceRecordService recordService;
    @Resource
    private ITAmUpdateRecordService amUpdateRecordService;

    /**
     * t_am_update_record 最大记录条数
     */
    private static final int UPDATE_RECODE_SIZE = 10;

    /**
     * 同步广告位
     * @param requestJson
     * @param bidResponseModel
     * @param pMediaEnum
     */
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
            tBidRecord.setImpTagId(requestRecordModel.getImpTagId());
            tBidRecord.setDeviceDpId(requestRecordModel.getDeviceDpId());
            tBidRecord.setDeviceCarrier(requestRecordModel.getDeviceCarrier());
            tBidRecord.setDeviceType(requestRecordModel.getDeviceType());
            tBidRecord.setAmId(requestRecordModel.getAmId());
            tBidRecord.setCityId(requestRecordModel.getCityId());

            recordList.add(tBidRecord);
        }

        bidTxService.updateAdListAndSaveRecord(adplacementList, recordList);
    }


    /**
     * 记录计划变更记录
     */
    @Async
    public void handleAmUpdateRecord(Long id, String name, AmUpdateTypeEnum typeEnum, String updateAfter, String updateBefore) {
        TAmUpdateRecord record = new TAmUpdateRecord();
        record.setAmid(id);
        record.setAmname(name);
        record.setType(typeEnum.getCode());
        record.setUpdateBefore(updateBefore);
        record.setUpdateAfter(updateAfter);

        amUpdateRecordService.save(record);
    }


    /**
     * 记录td请求
     * @param tagId
     * @param type
     * @param deviceId
     * @param result
     */
    @Async
    public void handleTdRecord(Long tagId, Integer type, String deviceId, boolean result) {

        TTagDeviceRecord record = new TTagDeviceRecord();
        record.setTagid(tagId);
        record.setType(type);
        record.setDeviceId(deviceId);
        record.setResult(result ? TagResultEnum.YES.getCode() : TagResultEnum.NO.getCode());

        try {
            recordService.save(record);
        } catch (Exception e) {
            log.error("异步插入td记录-失败 record:{}", JSONObject.toJSONString(record));
        }
    }
}

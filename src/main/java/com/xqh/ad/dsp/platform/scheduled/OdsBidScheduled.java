package com.xqh.ad.dsp.platform.scheduled;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TOdsBidDate;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITBidRecordService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITCallbackRecordService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITOdsBidDateService;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.enums.OdsTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by samson.huang on 2019/7/3
 */
@Slf4j
@Component
public class OdsBidScheduled {

    @Resource
    private ITBidRecordService bidRecordService;
    @Resource
    private ITCallbackRecordService callbackRecordService;
    @Resource
    private ITOdsBidDateService odsBidDateService;

    // @Scheduled(cron="0 5 0 * * *")
    public void odsBidScheduled() {

        LocalDateTime startDate = CommonUtils.getZeroHourTime(-1);
        LocalDateTime endDate = CommonUtils.getZeroHourTime(0);

        // 平台媒体
        Map<String, Integer> pmediaidRequestMap = bidRecordService.countGroupBy("pmediaid", startDate, endDate);
        Map<String, Integer> pmediaidCallbackMap = callbackRecordService.countGroupBy("pmediaid", startDate, endDate);
        Map<String, BigDecimal> pmediaidPriceMap = callbackRecordService.sumPriceGroupBy("pmediaid", startDate, endDate);

        // 媒体
        Map<String, Integer> mediaidRequestMap = bidRecordService.countGroupBy("mediaid", startDate, endDate);
        Map<String, Integer> mediaidCallbackMap = callbackRecordService.countGroupBy("mediaid", startDate, endDate);
        Map<String, BigDecimal> mediaidPriceMap = callbackRecordService.sumPriceGroupBy("mediaid", startDate, endDate);

        // 广告位id
        Map<String, Integer> padplacementidRequestMap = bidRecordService.countGroupBy("padplacementid", startDate, endDate);
        Map<String, Integer> padplacementidCallbackMap = callbackRecordService.countGroupBy("padplacementid", startDate, endDate);
        Map<String, BigDecimal> padplacementidPriceMap = callbackRecordService.sumPriceGroupBy("padplacementid", startDate, endDate);

        // 素材id
        Map<String, Integer> materialidRequestMap = bidRecordService.countGroupBy("materialid", startDate, endDate);
        Map<String, Integer> materialidCallbackMap = callbackRecordService.countGroupBy("materialid", startDate, endDate);
        Map<String, BigDecimal> materialidPriceMap = callbackRecordService.sumPriceGroupBy("materialid", startDate, endDate);


        // 取出所有的id
        Set<String> pmediaidSet = Sets.union(pmediaidRequestMap.keySet(), pmediaidRequestMap.keySet());
        Set<String> mediaidSet = Sets.union(mediaidRequestMap.keySet(), mediaidCallbackMap.keySet());
        Set<String> padplacementidSet = Sets.union(padplacementidRequestMap.keySet(), padplacementidCallbackMap.keySet());
        Set<String> materialidSet = Sets.union(materialidRequestMap.keySet(), materialidCallbackMap.keySet());

        // 构造入库记录
        LocalDateTime odsDate = CommonUtils.getZeroHourTime(-1);
        List<TOdsBidDate> pmediaidOdsList = Lists.newArrayList();
        for (String pmediaid : pmediaidSet) {
            TOdsBidDate ods = new TOdsBidDate();
            ods.setOdsDate(odsDate);
            ods.setOdsType(OdsTypeEnum.PMEDIAID.getCode());
            ods.setOdsValue(pmediaid);
            ods.setBid(pmediaidRequestMap.get(pmediaid));
            ods.setBidsucc(pmediaidCallbackMap.get(pmediaid));
            ods.setBidprice(pmediaidPriceMap.get(pmediaid));
            pmediaidOdsList.add(ods);
        }

        List<TOdsBidDate> mediaidOdsList = Lists.newArrayList();
        for (String mediaid : mediaidSet) {
            TOdsBidDate ods = new TOdsBidDate();
            ods.setOdsDate(odsDate);
            ods.setOdsType(OdsTypeEnum.MEDIAID.getCode());
            ods.setOdsValue(mediaid);
            ods.setBid(mediaidRequestMap.get(mediaid));
            ods.setBidsucc(mediaidCallbackMap.get(mediaid));
            ods.setBidprice(mediaidPriceMap.get(mediaid));
            mediaidOdsList.add(ods);
        }

        List<TOdsBidDate> padplacementidOdsList = Lists.newArrayList();
        for (String padplacementid : padplacementidSet) {
            TOdsBidDate ods = new TOdsBidDate();
            ods.setOdsDate(odsDate);
            ods.setOdsType(OdsTypeEnum.PADPLACEMENTID.getCode());
            ods.setOdsValue(padplacementid);
            ods.setBid(padplacementidRequestMap.get(padplacementid));
            ods.setBidsucc(padplacementidCallbackMap.get(padplacementid));
            ods.setBidprice(padplacementidPriceMap.get(padplacementid));
            padplacementidOdsList.add(ods);
        }

        List<TOdsBidDate> materialidOdsList = Lists.newArrayList();
        for (String materialid : materialidSet) {
            TOdsBidDate ods = new TOdsBidDate();
            ods.setOdsDate(odsDate);
            ods.setOdsType(OdsTypeEnum.MATERIALID.getCode());
            ods.setOdsValue(materialid);
            ods.setBid(materialidRequestMap.get(materialid));
            ods.setBidsucc(materialidCallbackMap.get(materialid));
            ods.setBidprice(materialidPriceMap.get(materialid));
            materialidOdsList.add(ods);
        }

        // 入库
        List<TOdsBidDate> insertList = Lists.newArrayList();
        insertList.addAll(pmediaidOdsList);
        insertList.addAll(mediaidOdsList);
        insertList.addAll(padplacementidOdsList);
        insertList.addAll(materialidOdsList);
        odsBidDateService.saveBatch(insertList);

    }

    public static void main(String[] args) {
        Set<String> s1 = Sets.newHashSet("1", "2");
        Set<String> s2 = Sets.newHashSet("2", "3");
        System.out.println(Sets.union(s1, s2));
    }
}

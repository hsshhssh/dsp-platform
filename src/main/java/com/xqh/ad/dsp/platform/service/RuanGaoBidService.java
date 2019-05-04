package com.xqh.ad.dsp.platform.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import com.xqh.ad.dsp.platform.utils.SeqNoUtils;
import com.xqh.ad.dsp.platform.utils.enums.PAdplacementStatusEnum;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import com.xqh.ad.dsp.platform.utils.enums.SeqBizEnum;
import com.xqh.ad.dsp.platform.utils.ruangao.AdplacementModel;
import com.xqh.ad.dsp.platform.utils.ruangao.RuanGaoConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 软告
 * Created by samson.huang on 2019/5/2
 */
@Slf4j
@Service
public class RuanGaoBidService {

    @Autowired
    private RuanGaoConfig ruanGaoConfig;
    @Autowired
    private OkHttpClient okHttpClient;
    @Autowired
    private ITPlatformAdplacementService adplacementService;
    @Autowired
    private SeqNoUtils seqNoUtils;

    /**
     * 同步广告位
     */
    public void asyncAdplacement() {

        // 获取广告位列表
        List<AdplacementModel> adplacementList = getAdplacementList();
        if (CollectionUtils.isEmpty(adplacementList)) {
            log.error("软告-获取广告位列表异常");
            return ;
        }
        Map<String, AdplacementModel> adplacementMap = adplacementList.stream().collect(Collectors.toMap(a -> String.valueOf(a.getAdplacementid()), a -> a));

        // 获取新增广告位
        Set<String> notExistAdplacementIds = adplacementService.selectNotInAdplacement(adplacementMap.keySet(), PMediaEnum.RUAN_GAO);
        if (CollectionUtils.isEmpty(notExistAdplacementIds)) {
            log.info("软告-无新增广告位");
            return;
        }

        // 批量插入
        List<TPlatformAdplacement> list = buildAdplacementList(adplacementMap, notExistAdplacementIds);
        try {
            adplacementService.insertList(list);
        } catch (Exception e) {
            log.error("软告-批量插入异常 ids:{}", notExistAdplacementIds, e);
            return;
        }

        log.info("软告-新增广告位成功 ids:{}", notExistAdplacementIds);
    }

    /**
     * 获取
     * @param modelMap
     * @param notExistAdplacementIds
     * @return
     */
    public List<TPlatformAdplacement> buildAdplacementList(Map<String, AdplacementModel> modelMap, Set<String> notExistAdplacementIds) {
        List<TPlatformAdplacement> list = Lists.newArrayList();
        for (String id : notExistAdplacementIds) {
            AdplacementModel model = modelMap.get(id);

            TPlatformAdplacement adplacement = new TPlatformAdplacement();
            adplacement.setPadplacementid(seqNoUtils.getNextSeqNo(SeqBizEnum.PADPLACEMENT));
            adplacement.setPmediaid(PMediaEnum.RUAN_GAO.getCode());
            adplacement.setAdplacementid(id);
            adplacement.setMediaid(model.getMediaid());
            adplacement.setMedianame(model.getMedianame());
            adplacement.setType(model.getType());
            adplacement.setPlatform(model.getPlatform());
            adplacement.setTypedeclare(model.getTypedeclare());
            adplacement.setSize(model.getSize());
            adplacement.setBidfloor(model.getBidfloor());
            adplacement.setAllowmaterial(model.getAllowmaterial());
            adplacement.setStatus(PAdplacementStatusEnum.INIT.getCode());
            list.add(adplacement);
        }
        return list;
    }


    /**
     * 获取广告位列表
     * @return
     */
    public List<AdplacementModel> getAdplacementList() {

        JSONObject requestJson = new JSONObject();
        requestJson.put("dspid", ruanGaoConfig.getDspid().trim());
        requestJson.put("token", ruanGaoConfig.getToken().trim());

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestJson.toJSONString());
        Request request = new Request.Builder().url(ruanGaoConfig.getAdListUrl().trim()).post(requestBody).build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
            if (Objects.equals(200, response.code())) {
                JSONObject result = JSONObject.parseObject(response.body().string());
                if (Objects.equals(result.getInteger("result"), 0)) {
                    return JSONArray.parseArray(result.getJSONObject("message").getString("records"), AdplacementModel.class);
                }
            }

            log.error("软告-获取广告位http请求失败 response:{}", response.body().toString());
            return null;
        } catch (Exception e) {
            log.error("软告-获取广告位http请求异常", e);
            return null;
        }
    }

}

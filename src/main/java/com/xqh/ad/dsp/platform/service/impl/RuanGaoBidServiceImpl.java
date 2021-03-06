package com.xqh.ad.dsp.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xqh.ad.dsp.platform.model.BidRequest;
import com.xqh.ad.dsp.platform.model.BidResponse;
import com.xqh.ad.dsp.platform.model.BidResponseModel;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TAdplacementMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TCallbackRecord;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITCallbackRecordService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformMaterialService;
import com.xqh.ad.dsp.platform.service.RuanGaoBidService;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.SeqNoUtils;
import com.xqh.ad.dsp.platform.utils.WinNoticeUtils;
import com.xqh.ad.dsp.platform.utils.enums.PAdplacementStatusEnum;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import com.xqh.ad.dsp.platform.utils.enums.SeqBizEnum;
import com.xqh.ad.dsp.platform.utils.ruangao.AdplacementModel;
import com.xqh.ad.dsp.platform.utils.ruangao.PriceDecoder;
import com.xqh.ad.dsp.platform.utils.ruangao.RuanGaoConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by samson.huang on 2019/5/19
 */
@Slf4j
@Service
public class RuanGaoBidServiceImpl implements RuanGaoBidService {

    @Resource
    private RuanGaoConfig ruanGaoConfig;
    @Resource(name = "okHttpClient10")
    private OkHttpClient okHttpClient;
    @Resource
    private ITPlatformAdplacementService adplacementService;
    @Resource
    private SeqNoUtils seqNoUtils;
    @Resource
    private ITPlatformMaterialService materialService;
    @Resource
    private ITCallbackRecordService callbackRecordService;
    @Resource
    private MaterialStrategyService materialStrategyService;
    @Resource
    private GetCityByIpService getCityByIpService;

    /**
     * 同步广告位
     */
    @Override
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
     * 获取响应结果
     * @param request
     * @return
     */
    @Override
    public BidResponseModel getBidResponse(BidRequest request) {
        BidResponseModel bidResponseModel = new BidResponseModel();

        // requestRecordModelList
        List<BidResponseModel.RequestRecordModel> requestRecordModelList = Lists.newArrayList();

        // 竞价接口响应对象
        BidResponse response = new BidResponse();
        // 请求id
        response.setId(request.getId());

        // 获取responseId bidder response id
        String bidderResponseId = seqNoUtils.getNextSeqNo(SeqBizEnum.BIDRESPONSE_ID);
        response.setBidid(bidderResponseId);


        // seatbid
        List<Map<String, List<BidResponse.Bid>>> seatbid = getSeatbid(request, requestRecordModelList);
        if (CollectionUtils.isEmpty(seatbid.get(0).get("bid"))) {
            response.setNbr(9);
        }
        response.setSeatbid(seatbid);

        bidResponseModel.setBidResponse(response);
        bidResponseModel.setRecordModelList(requestRecordModelList);
        return bidResponseModel;
    }

    public List<Map<String, List<BidResponse.Bid>>> getSeatbid(BidRequest request, List<BidResponseModel.RequestRecordModel> requestRecordModelList) {

        // 设置返回值
        List<BidResponse.Bid> bidList = Lists.newArrayList();
        List<Map<String, List<BidResponse.Bid>>> seatbid = Lists.newArrayList();
        Map<String, List<BidResponse.Bid>> bidMap = Maps.newHashMap();
        bidMap.put("bid", bidList);
        seatbid.add(bidMap);

        // 判断竞价广告
        if (CollectionUtils.isEmpty(request.getImp())) {
            return seatbid;
        }

        // 判断device
        BidRequest.Device device = request.getDevice();
        if (null == device) {
            return seatbid;
        }

        // 获取广告位策略
        Map<String, TAdplacementMaterial> strategyMap = materialStrategyService.getStrategy(request, PMediaEnum.RUAN_GAO);
        if (CollectionUtils.isEmpty(strategyMap)) {
            log.error("软告竞价-无可用策略 request:{}", JSONObject.toJSONString(request));
            return seatbid;
        }

        // 获取城市
        String cityCode = getCityByIpService.getCityByIp(request.getDevice().getIp());
        if (StringUtils.isBlank(cityCode)) {
            log.error("软告竞价-获取成功失败 ip:{} cityCode:{}", request.getDevice().getIp());
            return seatbid;
        }

        // 获取当前时间
        int hour = CommonUtils.getHour();

        // 设备联网方式，0=无;1=wifi;2 =2/3/4G
        int network = device.getConnectiontype() == null ? CommonUtils.NETWORK_NONE : device.getConnectiontype();

        for (BidRequest.Imp imp : request.getImp()) {
            // requestRecordModel
            BidResponseModel.RequestRecordModel requestRecordModel = new BidResponseModel.RequestRecordModel();
            requestRecordModel.setPmediaid(PMediaEnum.RUAN_GAO.getCode());

            // 选择策略
            TAdplacementMaterial strategy = strategyMap.get(imp.getTagid());
            if (strategy == null) {
                requestRecordModel.setImpid(imp.getId());
                requestRecordModel.setAdplacementid(imp.getTagid());
                requestRecordModelList.add(requestRecordModel);
                log.error("软告竞价-广告位无可用策略 adplacementid:{}", imp.getTagid());
                continue;
            }

            // 根据策略过滤投放计划
            boolean baseResult = materialStrategyService.filterBase(strategy, cityCode, String.valueOf(network), String.valueOf(hour));
            if (!baseResult) {
                log.info("软告竞价-基础校验不通过 adplacementid:{}", imp.getTagid());
                continue;

            }

            // 过滤标签
            boolean tagReslut = materialStrategyService.filerTDTag(strategy,  request.getDevice().getId(), request.getDevice().getDpid());
            if (!tagReslut) {
                log.info("软告竞价-标签校验不通过 adplacementid:{}", imp.getTagid());
                continue;
            }


            // 获取素材
            TPlatformMaterial material = materialService.getById(strategy.getMaterialid());

            // 构造响应bid对象
            bidList.add(getBid(request, imp, material, strategy));

            // 构造请求请求记录参数
            requestRecordModel.setImpid(imp.getId());
            requestRecordModel.setAdplacementid(imp.getTagid());
            requestRecordModel.setMaterialid(String.valueOf(material.getId()));
            requestRecordModel.setImpTagId(imp.getTagid());
            requestRecordModel.setDeviceDpId(device.getDpid());
            requestRecordModel.setDeviceCarrier(device.getCarrier());
            requestRecordModel.setDeviceType(String.valueOf(device.getDevicetype()));
            requestRecordModel.setAmId(String.valueOf(strategy.getId()));
            requestRecordModel.setCityId(cityCode);
            requestRecordModelList.add(requestRecordModel);
        }

        return seatbid;
    }

    private BidResponse.Bid getBid(BidRequest request, BidRequest.Imp imp,  TPlatformMaterial material, TAdplacementMaterial strategy) {
        BidResponse.Bid bid = new BidResponse.Bid();
        // 请求ID
        bid.setId(request.getId());
        // 曝光id
        bid.setImpid(imp.getId());
        // 价格
        bid.setPrice(strategy.getPrice().floatValue());
        // 竞价成功回调地址
        bid.setNurl(ruanGaoConfig.getNurl().trim());
        bid.setAdm(material.getAdm());
        bid.setCrid(material.getCrid());
        bid.setAdtype(material.getAdtype());

        bid.setExt(getExt(strategy));

        return bid;
    }

    private Object getExt(TAdplacementMaterial strategy) {
        JSONObject ext = new JSONObject();

        JSONArray pmoArr = new JSONArray();
        JSONObject pmo = new JSONObject();
        pmo.put("type_mma", 0);
        pmo.put("url", strategy.getPmoUrl());
        pmoArr.add(pmo);
        ext.put("pmo", pmoArr);

        JSONArray cmoArr = new JSONArray();
        JSONObject cmo = new JSONObject();
        cmo.put("type_mma", 0);
        cmo.put("url", strategy.getCmoUrl());
        cmoArr.add(cmo);
        ext.put("cmo", cmoArr);
        return ext;
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
            adplacement.setAdplacementname(model.getAdplacementname());
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
    @Override
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


    /**
     * 回调
     */
    @Override
    public void callback(WinNoticeUtils.WinNoticeRequest request) {
        // 解密价格
        String price;
        try {
            price = PriceDecoder.decodePrice(request.getPrice(), ruanGaoConfig.getToken());
        } catch (Exception e) {
            log.error("软告竞价回调-解密价格失败 request:{}", JSONObject.toJSONString(request), e);
            return ;
        }

        // 保存回调记录
        try {
            TCallbackRecord callbackRecord = new TCallbackRecord();
            callbackRecord.setPmediaid(PMediaEnum.RUAN_GAO.getCode());
            callbackRecord.setImpid(request.getImpId());
            callbackRecord.setBidimpid(request.getBidImpId());
            callbackRecord.setCallbackPrice(BigDecimal.valueOf(Double.valueOf(price)));
            callbackRecordService.save(callbackRecord);
        } catch (NumberFormatException e) {
            log.error("软告竞价回调-入库过程异常 request:{}", JSONObject.toJSONString(request), e);
        }
    }
}

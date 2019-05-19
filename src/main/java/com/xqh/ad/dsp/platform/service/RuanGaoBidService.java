package com.xqh.ad.dsp.platform.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.openrtb.OpenRtb;
import com.xqh.ad.dsp.platform.model.BidResponse;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TCallbackRecord;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITCallbackRecordService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformMaterialService;
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
 * 软告
 * Created by samson.huang on 2019/5/2
 */
public interface RuanGaoBidService {

    /**
     * 同步广告位
     */
    public void asyncAdplacement();

    /**
     * 获取响应结果
     * @param request
     * @return
     */
    public BidResponse getBidResponse(OpenRtb.BidRequest request);


    /**
     * 获取广告位列表
     * @return
     */
    public List<AdplacementModel> getAdplacementList();


    /**
     * 回调
     */
    public void callback(WinNoticeUtils.WinNoticeRequest request);

}

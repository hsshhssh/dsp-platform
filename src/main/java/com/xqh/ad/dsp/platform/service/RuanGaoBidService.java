package com.xqh.ad.dsp.platform.service;

import com.google.openrtb.OpenRtb;
import com.xqh.ad.dsp.platform.model.BidRequest;
import com.xqh.ad.dsp.platform.model.BidResponseModel;
import com.xqh.ad.dsp.platform.utils.WinNoticeUtils;
import com.xqh.ad.dsp.platform.utils.ruangao.AdplacementModel;

import java.util.List;

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
    public BidResponseModel getBidResponse(BidRequest request);


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

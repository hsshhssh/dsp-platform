package com.xqh.ad.dsp.platform.mybatisplus.service;

import com.xqh.ad.dsp.platform.mybatisplus.entity.TBidRecord;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;

import java.util.List;

/**
 * Created by samson.huang on 2019/5/12
 */
public interface IBidTxService {

    public void updateAdListAndSaveRecord(List<TPlatformAdplacement> adplacementList, List<TBidRecord> recordList);
}

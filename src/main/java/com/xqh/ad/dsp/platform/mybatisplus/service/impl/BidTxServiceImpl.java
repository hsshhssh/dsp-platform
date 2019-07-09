package com.xqh.ad.dsp.platform.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TBidRecord;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.mapper.TBidRecordMapper;
import com.xqh.ad.dsp.platform.mybatisplus.mapper.TPlatformAdplacementMapper;
import com.xqh.ad.dsp.platform.mybatisplus.service.IBidTxService;
import com.xqh.ad.dsp.platform.utils.enums.PAdplacementStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by samson.huang on 2019/5/12
 */
@Service
public class BidTxServiceImpl implements IBidTxService {

    @Resource
    private TPlatformAdplacementMapper adplacementMapper;
    @Resource
    private TBidRecordMapper recordMapper;

    @Override
    @Transactional
    public void updateAdListAndSaveRecord(List<TPlatformAdplacement> adplacementList, List<TBidRecord> recordList) {
        for (TPlatformAdplacement adplacement : adplacementList) {
            UpdateWrapper<TPlatformAdplacement> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("pmediaid", adplacement.getPmediaid());
            updateWrapper.eq("adplacementid", adplacement.getAdplacementid());

            TPlatformAdplacement updateEntity = new TPlatformAdplacement();
            updateEntity.setStatus(PAdplacementStatusEnum.REQUEST.getCode());
            adplacementMapper.update(updateEntity, updateWrapper);
        }

        for (TBidRecord bidRecord : recordList) {
            recordMapper.insert(bidRecord);
        }
    }
}

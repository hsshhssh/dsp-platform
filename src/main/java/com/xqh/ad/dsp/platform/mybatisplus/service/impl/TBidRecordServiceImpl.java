package com.xqh.ad.dsp.platform.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TBidRecord;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.mapper.TBidRecordMapper;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITBidRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 竞价请求记录表 服务实现类
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-02
 */
@Service
public class TBidRecordServiceImpl extends ServiceImpl<TBidRecordMapper, TBidRecord> implements ITBidRecordService {

    @Override
    public TBidRecord getByBididAndImpid(String bidid, String impid) {
        QueryWrapper<TBidRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("bidid", bidid);
        queryWrapper.eq("impid", impid);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Map<String, Integer> countGroupBy(String columnName, String startTime, String endTime) {
        List<Map<String, Object>> list = baseMapper.countGroupBy(columnName, startTime, endTime);
        Map<String, Integer> map = Maps.newHashMap();
        for (Map<String, Object> tempMap : list) {
            map.put(String.valueOf(tempMap.get(columnName)), Integer.valueOf(String.valueOf(tempMap.get("count"))));
        }
        return map;
    }
}

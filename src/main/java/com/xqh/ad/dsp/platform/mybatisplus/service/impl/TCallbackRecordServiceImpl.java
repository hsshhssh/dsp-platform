package com.xqh.ad.dsp.platform.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TCallbackRecord;
import com.xqh.ad.dsp.platform.mybatisplus.mapper.TCallbackRecordMapper;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITCallbackRecordService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 回调记录 服务实现类
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-12
 */
@Service
public class TCallbackRecordServiceImpl extends ServiceImpl<TCallbackRecordMapper, TCallbackRecord> implements ITCallbackRecordService {


    @Override
    public Map<String, Integer> countGroupBy(String columnName, String startTime, String endTime) {
        List<Map<String, Object>> list = baseMapper.countGroupBy(columnName, startTime, endTime);
        Map<String, Integer> map = Maps.newHashMap();
        for (Map<String, Object> tempMap : list) {
            map.put(String.valueOf(tempMap.get(columnName)), Integer.valueOf(String.valueOf(tempMap.get("count"))));
        }
        return map;
    }

    @Override
    public Map<String, BigDecimal> sumPriceGroupBy(String columnName, String startTime, String endTime) {
        List<Map<String, Object>> list = baseMapper.sumPriceGroupBy(columnName, startTime, endTime);
        Map<String, BigDecimal> map = Maps.newHashMap();
        for (Map<String, Object> tempMap : list) {
            map.put(String.valueOf(tempMap.get(columnName)), (BigDecimal) tempMap.get("total"));
        }
        return map;
    }
}

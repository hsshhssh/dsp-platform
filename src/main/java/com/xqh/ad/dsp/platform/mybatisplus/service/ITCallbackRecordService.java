package com.xqh.ad.dsp.platform.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TCallbackRecord;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 回调记录 服务类
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-12
 */
public interface ITCallbackRecordService extends IService<TCallbackRecord> {

    public Map<String, Integer> countGroupBy(@Param("columnName") String columnName, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    public Map<String, BigDecimal> sumPriceGroupBy(@Param("columnName") String columnName, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}

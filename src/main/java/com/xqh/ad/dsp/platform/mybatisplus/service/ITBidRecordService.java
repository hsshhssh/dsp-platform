package com.xqh.ad.dsp.platform.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TBidRecord;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 竞价请求记录表 服务类
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-02
 */
public interface ITBidRecordService extends IService<TBidRecord> {

    TBidRecord getByBididAndImpid(String bidid, String impid);

    Map<String, Integer> countGroupBy(@Param("columnName") String columnName, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

}

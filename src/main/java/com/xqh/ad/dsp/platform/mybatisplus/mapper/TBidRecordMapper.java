package com.xqh.ad.dsp.platform.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TBidRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 竞价请求记录表 Mapper 接口
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-02
 */
@Mapper
public interface TBidRecordMapper extends BaseMapper<TBidRecord> {

    List<Map<String, Object>> countGroupBy(@Param("columnName") String columnName, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

}

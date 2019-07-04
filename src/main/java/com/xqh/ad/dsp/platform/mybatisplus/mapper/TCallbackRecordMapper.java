package com.xqh.ad.dsp.platform.mybatisplus.mapper;

import com.xqh.ad.dsp.platform.mybatisplus.entity.TCallbackRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 回调记录 Mapper 接口
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-12
 */
@Mapper
public interface TCallbackRecordMapper extends BaseMapper<TCallbackRecord> {

    public List<Map<String, Object>> countGroupBy(@Param("columnName") String columnName, @Param("startTime") String startTime, @Param("endTime") String endTime);

    public List<Map<String, Object>> sumPriceGroupBy(@Param("columnName") String columnName, @Param("startTime") String startTime, @Param("endTime") String endTime);

}

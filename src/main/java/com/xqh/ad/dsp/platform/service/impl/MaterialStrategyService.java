package com.xqh.ad.dsp.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.xqh.ad.dsp.platform.model.BidRequest;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TAdplacementMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TTag;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TTagDeviceRecord;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITAdplacementMaterialService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITTagDeviceRecordService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITTagService;
import com.xqh.ad.dsp.platform.utils.AsyncUtils;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.enums.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by samson.huang on 2019/7/27
 */
@Slf4j
@Service
public class MaterialStrategyService {

    @Resource
    private ITAdplacementMaterialService amService;
    @Resource
    private ITTagDeviceRecordService tagDeviceRecordService;
    @Resource
    private ITTagService tagService;
    @Resource
    private TDService tdService;
    @Resource
    private AsyncUtils asyncUtils;

    /**
     * 获取广告位策略
     */
    public Map<String, TAdplacementMaterial> getStrategy(BidRequest request, PMediaEnum pMediaEnum) {

        List<String> adplacementidList = request.getImp().stream().map(BidRequest.Imp::getTagid).collect(Collectors.toList());

        QueryWrapper<TAdplacementMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("adplacementid", adplacementidList);
        queryWrapper.eq("pmediaid", pMediaEnum.getCode());
        queryWrapper.eq("status", CommonUtils.ENABLE_STATUS);

        List<TAdplacementMaterial> list = amService.list(queryWrapper);
        return list.stream().collect(Collectors.toMap(TAdplacementMaterial::getAdplacementid, t -> t));
    }

    /**
     * 判断城市、联网方式、小时
     * @param strategy
     * @param cityCode
     * @param network
     * @param hour
     * @return
     */
    public boolean filterBase(TAdplacementMaterial strategy, String cityCode, String network, String hour) {
        // 判断时间
        List<String> hourList = Splitter.on(",").omitEmptyStrings().splitToList(strategy.getHours());
        if (!hourList.contains(hour)) {
            return false;
        }

        // 判断联网方式
        List<String> networkList = Splitter.on(",").omitEmptyStrings().splitToList(strategy.getNetwork());
        if (!networkList.contains(network)) {
            return false;
        }

        // 判断城市
        List<String> cityList = Splitter.on(",").omitEmptyStrings().splitToList(strategy.getCity());
        if (!cityList.contains(cityCode)) {
            return false;
        }

        return true;
    }

    /**
     * 过滤TD标签
     * @param strategy
     * @param deviceType
     * @param deviceId
     * @return
     */
    public boolean filerTDTag(TAdplacementMaterial strategy, String deviceType, String deviceId) {

        // 没有配置标签 => 条件不满足
        List<String> tagIdList = Splitter.on(",").omitEmptyStrings().splitToList(strategy.getTag());
        if (CollectionUtils.isEmpty(tagIdList)) {
            return false;
        }
        QueryWrapper<TTag> tagQuery = new QueryWrapper<>();
        tagQuery.in("id", tagIdList.stream().map(Long::valueOf).collect(Collectors.toList()));
        List<TTag> tagList = tagService.list();

        // 仅支持安卓
        TDDeviceTypeEnum deviceTypeEnum = TDDeviceTypeEnum.IMEI;
        // TDDeviceTypeEnum deviceTypeEnum = TDDeviceTypeEnum.getByCode(deviceType);
        // if (null == deviceTypeEnum || !Objects.equals(TDDeviceTypeEnum.IMEI.getCode(), deviceType)) {
        //     return false;
        // }

        // 查询本地匹配记录
        List<TTag> noRecordTagList = Lists.newArrayList();
        for (TTag tag : tagList) {
            QueryWrapper<TTagDeviceRecord> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("deviceId", deviceId);
            queryWrapper.eq("type", deviceTypeEnum.getTdCode());
            queryWrapper.eq("tagid", tag.getId());
            TTagDeviceRecord tagRecord = tagDeviceRecordService.getOne(queryWrapper);

            // 黑名单
            if (Objects.equals(TagTypeEnum.BLACK.getCode(), tag.getTagType())
                    && tagRecord != null) {
                log.info("黑名单命中");
                return false;
            }

            // // 白名单
            // if (Objects.equals(TagTypeEnum.WHILE.getCode(), tag.getTagType())
            //         && tagRecord != null) {
            //     return true;
            // }

            // td 记录
            // if (Objects.equals(TagIsTdEnum.YES.getCode(), tag.getIsTd())) {
            //     if (tagRecord != null && Objects.equals(tagRecord.getResult(), TagResultEnum.YES.getCode())) {
            //         log.info("标签过滤-本地记录匹配 tag:{}", tagRecord.getId());
            //         return true;
            //     }
            //     if (tagRecord == null) {
            //         noRecordTagList.add(tag);
            //     }
            // }

        }

        // 请求TD
        // for (TTag tag : noRecordTagList) {
        //     boolean tdResult;
        //     try {
        //         tdResult = tdService.getTDResult(tag, deviceTypeEnum.getTdCode(), deviceId);
        //     } catch (Exception e) {
        //         log.error("标签过滤-请求td异常 tagId:{}", tag.getId(), e);
        //         return false;
        //     }
        //
        //     // 异步记录请求记录
        //     asyncUtils.handleTdRecord(tag.getId(), deviceTypeEnum.getTdCode(), deviceId, tdResult);
        //
        //     // 存在一个成功即可返回
        //     if (tdResult) {
        //         log.info("标签过滤-请求TD成功 tagId:{}", tag.getId());
        //         return true;
        //     }
        //
        // }
        // 所有标签都不满足
        log.info("所有标签都不满足");
        return true;
    }

}

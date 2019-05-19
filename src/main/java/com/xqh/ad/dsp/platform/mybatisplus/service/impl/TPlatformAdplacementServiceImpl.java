package com.xqh.ad.dsp.platform.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import com.xqh.ad.dsp.platform.model.AdplacementListDTO;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.mapper.TPlatformAdplacementMapper;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 平台广告位表 服务实现类
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-02
 */
@Service
public class TPlatformAdplacementServiceImpl extends ServiceImpl<TPlatformAdplacementMapper, TPlatformAdplacement> implements ITPlatformAdplacementService {

    @Override
    public Set<String> selectNotInAdplacement(Set<String> adplacementIdList, PMediaEnum pMediaEnum) {
        QueryWrapper<TPlatformAdplacement> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("adplacementid");
        queryWrapper.in("adplacementid", adplacementIdList);
        queryWrapper.eq("pmediaid", pMediaEnum.getCode());

        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        Set<String> notExistAdplacementIds = Sets.newHashSet();
        List<String> existAdplacementIdList = maps.stream().map(m -> (String) m.get("adplacementid")).distinct().collect(Collectors.toList());
        for (String id : adplacementIdList) {
            if (!existAdplacementIdList.contains(id)) {
                notExistAdplacementIds.add(id);
            }
        }
        return notExistAdplacementIds;
    }

    @Override
    @Transactional
    public void insertList(List<TPlatformAdplacement> list) {
        for (TPlatformAdplacement adplacement : list) {
            baseMapper.insert(adplacement);
        }
    }
}

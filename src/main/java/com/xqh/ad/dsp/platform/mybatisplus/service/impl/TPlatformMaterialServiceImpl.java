package com.xqh.ad.dsp.platform.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.mapper.TPlatformMaterialMapper;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 素材表 服务实现类
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-02
 */
@Service
public class TPlatformMaterialServiceImpl extends ServiceImpl<TPlatformMaterialMapper, TPlatformMaterial> implements ITPlatformMaterialService {

    /**
     * 批量获取广告位素材
     * @param adplacementidList
     * @param mediaEnum
     * @return
     */
    @Override
    public Map<String, TPlatformMaterial> getByAdplacementid(List<String> adplacementidList, PMediaEnum mediaEnum) {

        if (CollectionUtils.isEmpty(adplacementidList)) {
            return Maps.newHashMap();
        }

        QueryWrapper<TPlatformMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("adplacementid", adplacementidList);
        queryWrapper.eq("pmediaid", mediaEnum.getCode());
        List<TPlatformMaterial> platformMaterialList = baseMapper.selectList(queryWrapper);

        return platformMaterialList.stream().collect(Collectors.toMap(TPlatformMaterial::getAdplacementid, t -> t));

    }
}

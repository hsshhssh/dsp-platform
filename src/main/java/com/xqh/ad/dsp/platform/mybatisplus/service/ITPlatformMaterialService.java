package com.xqh.ad.dsp.platform.mybatisplus.service;

import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformMaterial;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 素材表 服务类
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-02
 */
public interface ITPlatformMaterialService extends IService<TPlatformMaterial> {

    public Map<String, TPlatformMaterial> getByAdplacementid(List<String> adplacementidList, PMediaEnum mediaEnum);
}

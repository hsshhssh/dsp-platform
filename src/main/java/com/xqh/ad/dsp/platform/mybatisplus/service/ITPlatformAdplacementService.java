package com.xqh.ad.dsp.platform.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xqh.ad.dsp.platform.model.AdplacementListDTO;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 平台广告位表 服务类
 * </p>
 *
 * @author K神带你飞
 * @since 2019-05-02
 */
public interface ITPlatformAdplacementService extends IService<TPlatformAdplacement> {

    public Set<String> selectNotInAdplacement(Set<String> adplacementIdList, PMediaEnum pMediaEnum);

    public void insertList(List<TPlatformAdplacement> list);
}

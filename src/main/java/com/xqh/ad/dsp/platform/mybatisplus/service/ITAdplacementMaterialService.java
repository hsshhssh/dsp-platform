package com.xqh.ad.dsp.platform.mybatisplus.service;

import com.xqh.ad.dsp.platform.mybatisplus.entity.TAdplacementMaterial;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 投放计划表 服务类
 * </p>
 *
 * @author K神带你飞
 * @since 2019-07-27
 */
public interface ITAdplacementMaterialService extends IService<TAdplacementMaterial> {

    public void toEnableAndToDisable(TAdplacementMaterial toEnable, TAdplacementMaterial toDisable);

    public int saveCost(Long id, Long cost);

    public int saveAllCost(Long id);
}

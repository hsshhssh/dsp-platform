package com.xqh.ad.dsp.platform.mybatisplus.service.impl;

import com.xqh.ad.dsp.platform.mybatisplus.entity.TAdplacementMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.mapper.TAdplacementMaterialMapper;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITAdplacementMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 投放计划表 服务实现类
 * </p>
 *
 * @author K神带你飞
 * @since 2019-07-27
 */
@Service
public class TAdplacementMaterialServiceImpl extends ServiceImpl<TAdplacementMaterialMapper, TAdplacementMaterial> implements ITAdplacementMaterialService {

    @Override
    @Transactional
    public void toEnableAndToDisable(TAdplacementMaterial toEnable, TAdplacementMaterial toDisable) {
        if (toDisable != null) {
            this.baseMapper.updateById(toDisable);
        }

        this.baseMapper.updateById(toEnable);
    }

    @Override
    public int saveCost(Long id, Long cost) {
        TAdplacementMaterial save = new TAdplacementMaterial();
        save.setId(id);
        save.setCost(cost/1000);
        return this.baseMapper.updateById(save);
    }

    @Override
    public int saveAllCost(Long id) {
        return this.baseMapper.saveTotalCost(id);
    }
}

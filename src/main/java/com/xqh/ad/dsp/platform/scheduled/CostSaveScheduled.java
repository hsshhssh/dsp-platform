package com.xqh.ad.dsp.platform.scheduled;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TAdplacementMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITAdplacementMaterialService;
import com.xqh.ad.dsp.platform.utils.CacheUtils;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.enums.DelEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by samson.huang on 2019/9/21
 */
@Slf4j
@Component
public class CostSaveScheduled {

    @Autowired
    private CacheUtils cacheUtils;
    @Autowired
    private ITAdplacementMaterialService adplacementMaterialService;


    @Scheduled(cron="0 0/5 * * * *")
    public void saveCost() {
        int count=0;
        for (Map.Entry<Long, Long> entry : cacheUtils.getCostCache().asMap().entrySet()) {
            count ++;
            if (entry.getKey() == null) {
                continue;
            }
            adplacementMaterialService.saveCost(entry.getKey(), entry.getValue());
        }
        log.info("刷新cost, 刷新条数:{}", count);
    }


    @Scheduled(cron="30 0 0 * * *")
    public void saveTotalCost() {

        // todo 分页
        QueryWrapper<TAdplacementMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", CommonUtils.ENABLE_STATUS);
        queryWrapper.eq("del", DelEnum.NO.getCode());
        queryWrapper.ne("cost", 0L);
        List<TAdplacementMaterial> list = adplacementMaterialService.list(queryWrapper);

        for (TAdplacementMaterial am : list) {
            adplacementMaterialService.saveAllCost(am.getId());
        }

        cacheUtils.getCostCache().invalidateAll();
        log.info("保存total cost, 保存条数:{}", list.size());
    }

}

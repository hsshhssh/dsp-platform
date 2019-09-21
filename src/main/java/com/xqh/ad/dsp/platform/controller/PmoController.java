package com.xqh.ad.dsp.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.xqh.ad.dsp.platform.scheduled.CostSaveScheduled;
import com.xqh.ad.dsp.platform.utils.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by samson.huang on 2019/9/21
 */
@Slf4j
@RestController
public class PmoController {

    @Autowired
    private CacheUtils cacheUtils;
    @Autowired
    private CostSaveScheduled costSaveScheduled;

    @GetMapping("pmo/{id}/{price}")
    public void pmo(@PathVariable("id") Long id, @PathVariable("price") Long price) {
        cacheUtils.addCost(id, price);
    }

    @GetMapping("pmo/cache")
    public Object getCache() {
        return cacheUtils.getCostCache().asMap();
    }

    @GetMapping("pmo/save/cost")
    public void saveCost() {
        costSaveScheduled.saveCost();
    }

    @GetMapping("pmo/save/total")
    public void saveTotal() {
        costSaveScheduled.saveTotalCost();
    }
}

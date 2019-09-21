package com.xqh.ad.dsp.platform.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TAdplacementMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITAdplacementMaterialService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 先本地缓存
 * Created by samson.huang on 2019/8/10
 */
@Slf4j
@Component
public class CacheUtils {


    @Autowired
    private ITAdplacementMaterialService adplacementMaterialService;


    /**
     * 广告位下标缓存（用于轮训）
     */
    private LoadingCache<String, Integer> indexCache;

    /**
     * padplacementId => 当前次数
     * @return
     */
    private CacheLoader<String, Integer> indexCacheLoader() {
        return new CacheLoader<String, Integer>() {
            @Override
            public Integer load(String key) throws Exception {
                return 0;
            }
        };
    }

    /**
     * 计划消耗缓存（用于计算当前消耗）
     */
    @Getter
    private LoadingCache<Long, Long> costCache;

    /**
     * padplacementId => 当前次数
     * @return
     */
    private CacheLoader<Long, Long> costCacheLoader() {
        return new CacheLoader<Long, Long>() {
            @Override
            public Long load(Long key) throws Exception {
                TAdplacementMaterial am = adplacementMaterialService.getById(key);
                if (am == null) {
                    return 0L;
                } else {
                    return am.getCost();
                }
            }
        };
    }


    @PostConstruct
    private void init() {
        indexCache = CacheBuilder.newBuilder()
                .maximumSize(5000)
                .expireAfterAccess(1L, TimeUnit.DAYS)
                .build(indexCacheLoader());

        costCache = CacheBuilder.newBuilder()
                .maximumSize(5000)
                .expireAfterAccess(2L, TimeUnit.DAYS)
                .build(costCacheLoader());
    }

    /**
     *
     * 允许损耗（无须加锁）
     * @param key
     * @return
     */
    public void addCost(Long key, Long cost) {
        try {
            Long oldCost = costCache.get(key);
            costCache.put(key, oldCost + cost);
        } catch (ExecutionException e) {
            log.error("获取当前cost异常 id:{}", key);
        }
    }


    /**
     * 获取计划下标
     * @param padplacementid
     * @param total
     * @return
     */
    public Integer getIndex(String padplacementid, int total) {
        try {
            Integer cur = indexCache.get(padplacementid);
            Integer next = (cur + 1) % total;
            indexCache.put(padplacementid, next);
            log.info("获取计划下标-padplacementid:{} cur:{} nex:{}", padplacementid, cur, next);
            return cur;
        } catch (ExecutionException e) {
            log.error("获取计划下标-异常 padplacementid:{}", padplacementid);
        }
        return 0;
    }

}

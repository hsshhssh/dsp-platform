package com.xqh.ad.dsp.platform.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 先本地缓存
 * Created by samson.huang on 2019/8/10
 */
@Slf4j
@Component
public class CacheUtils {

    private static LoadingCache<String, Integer> cache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .expireAfterAccess(1L, TimeUnit.DAYS)
            .build(createCacheLoader());

    /**
     * padplacementId => 当前次数
     * @return
     */
    private static CacheLoader<String, Integer> createCacheLoader() {
        return new CacheLoader<String, Integer>() {
            @Override
            public Integer load(String key) throws Exception {
                return 0;
            }
        };
    }

    /**
     * 获取计划下标
     * @param padplacementid
     * @param total
     * @return
     */
    public Integer getIndex(String padplacementid, int total) {
        try {
            Integer cur = cache.get(padplacementid);
            Integer next = (cur + 1) % total;
            cache.put(padplacementid, next);
            log.info("获取计划下标-padplacementid:{} cur:{} nex:{}", padplacementid, cur, next);
            return cur;
        } catch (ExecutionException e) {
            log.error("获取计划下标-异常 padplacementid:{}", padplacementid);
        }
        return 0;
    }

}

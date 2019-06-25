package com.xqh.ad.dsp.platform.utils.common;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * Created by samson.huang on 2019/5/19
 */
@Slf4j
public class MybatisPlusHelper {



    public static <T, V> QueryWrapper<V> buildQueryWrapper(T entity, Class<V> cls) {
        try {
            QueryWrapper<V> wrapper = new QueryWrapper<>();
            for (Field field : entity.getClass().getDeclaredFields()) {
                String fileName = field.getName();
                if ("serialVersionUID".equals(fileName)) {
                    continue;
                }
                field.setAccessible(true);
                Object o = field.get(entity);
                if (o != null) {
                    if(fileName.endsWith("_like")) {
                        wrapper.like(fileName.substring(0, fileName.indexOf("_like")), "%" + o + "%");
                    } else {
                        wrapper.eq(fileName, o);
                    }
                }
            }
            return wrapper;
        } catch (IllegalAccessException e) {
            log.error("构造wrapper异常 entity:{}", JSONObject.toJSONString(entity), e);
        }
        throw new RuntimeException();
    }
}

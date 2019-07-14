package com.xqh.ad.dsp.platform.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by samson.huang on 2019/7/14
 */
@Slf4j
@Service
public class GetCityByIpServiceImpl {

    @Resource(name = "okHttpClient1")
    private OkHttpClient okHttpClient;

    public String getCityByIp(String ip) {
        if (StringUtils.isBlank(ip)) {
            return "";
        }
        try {
            Request request = new Request.Builder()
                    .url("http://restapi.amap.com/v3/ip?key=2e5d5fbd6a3e91505526cde0734f6c36&ip=" + ip.trim())
                    .addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
                    .get()
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (Objects.equals(200, response.code())) {
                JSONObject result = JSONObject.parseObject(response.body().string());
                return result.getString("city");

            }
            log.error("通过ip获取城市失败 ip:{} response:{}", response.body().string());
            return "";
        } catch (Exception e) {
            log.error("通过ip获取城市异常 ip:{}", ip ,e);
            return "";
        }
    }

}

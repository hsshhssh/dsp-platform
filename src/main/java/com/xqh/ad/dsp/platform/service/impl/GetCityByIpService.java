package com.xqh.ad.dsp.platform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xqh.ad.dsp.platform.utils.zkconf.DspConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Created by samson.huang on 2019/7/14
 */
@Slf4j
@Service
public class GetCityByIpService {

    @Resource(name = "okHttpClient1")
    private OkHttpClient okHttpClient;
    @Resource
    private DspConfig dspConfig;

    public String getCityByIp(String ip) {
        // return "110000";
        if (StringUtils.isBlank(ip)) {
            return "";
        }
        try {
            Request request = new Request.Builder()
                    .url("http://restapi.amap.com/v3/ip?key=" + dspConfig.getGaoDeKey().trim() + "&ip=" + ip.trim())
                    .addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
                    .get()
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (Objects.equals(200, response.code())) {
                JSONObject result = JSONObject.parseObject(response.body().string());
                if (Objects.equals("10000", result.getString("infocode")) && StringUtils.isNotBlank(result.getString("city"))) {
                    return result.getString("adcode");
                }
            }
            log.error("通过ip获取城市失败 ip:{} response:{}", response.body().string());
            return "";
        } catch (Exception e) {
            log.error("通过ip获取城市异常 ip:{}", ip ,e);
            return "";
        }
    }

}

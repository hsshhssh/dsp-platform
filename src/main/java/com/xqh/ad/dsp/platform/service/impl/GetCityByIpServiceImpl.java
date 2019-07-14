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
                    .url("http://ip.taobao.com/service/getIpInfo.php?ip=119.123.206.66")
                    .addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
                    .get()
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (Objects.equals(200, response.code())) {
                JSONObject result = JSONObject.parseObject(response.body().string());
                if (Objects.equals(result.getInteger("code"), 0)) {
                    return result.getJSONObject("data").getString("city");
                }
            }
            log.error("通过ip获取城市失败 ip:{} response:{}", response.body().string());
            return "";
        } catch (IOException e) {
            log.error("通过ip获取城市异常 ip:{}", ip ,e);
            return "";
        }
    }

}

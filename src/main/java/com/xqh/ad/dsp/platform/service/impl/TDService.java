package com.xqh.ad.dsp.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TTag;
import com.xqh.ad.dsp.platform.utils.zkconf.DspConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by samson.huang on 2019/7/28
 */
@Slf4j
@Service
public class TDService {

    @Resource(name = "okHttpClient1")
    private OkHttpClient okHttpClient;
    @Resource
    private DspConfig dspConfig;

    public boolean getTDResult(TTag tTag, int deviceIdType, String deviceId) {

        JSONObject requestJson = new JSONObject();
        requestJson.put("mediaId", Integer.valueOf(dspConfig.getTdMediaId().trim()));
        requestJson.put("campaignId", Collections.singletonList(tTag.getTdKey()));
        requestJson.put("deviceIdType", deviceIdType);
        requestJson.put("deviceId", deviceId);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestJson.toJSONString());
        Request request = new Request.Builder().url(dspConfig.getTdUrl().trim()).post(requestBody).build();


        try {
            Response response = okHttpClient.newCall(request).execute();
            // http 200响应吗
            if (Objects.equals(200, response.code())) {
                // TD code
                JSONObject result = JSONObject.parseObject(response.body().string());
                if (Objects.equals(2000, result.getIntValue("code"))) {
                    JSONArray r = result.getJSONArray("result");
                    return Objects.equals("true", r.getJSONObject(0).getString("matched"));
                }
            }
        } catch (IOException e) {
            log.error("请求TD接口异常 tTag:{}", JSONObject.toJSONString(tTag), e);
        }
        throw new RuntimeException("请求TD接口异常");
    }

}

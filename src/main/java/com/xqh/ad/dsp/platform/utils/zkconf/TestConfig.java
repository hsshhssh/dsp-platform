package com.xqh.ad.dsp.platform.utils.zkconf;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.hssh.common.zkconf.ValueWithMethod;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by samson.huang on 2019/4/7
 */
@Slf4j
@Component
public class TestConfig {

    @Getter
    private volatile String bidResponse;

    @ValueWithMethod(path = "test.conf")
    public void ininBidResponse(ByteArrayInputStream inputStream) {
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(inputStream, writer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(writer.toString());
        log.info("bidResponse配置值: {}", jsonObject);
        bidResponse = jsonObject.toJSONString();
    }

}

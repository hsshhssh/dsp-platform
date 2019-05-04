package com.xqh.ad.dsp.platform.utils.ruangao;

import lombok.Data;
import org.hssh.common.zkconf.Value;
import org.springframework.stereotype.Component;

/**
 * Created by samson.huang on 2019/5/2
 */
@Data
@Component
public class RuanGaoConfig {

    /**
     * 广告位列表请求url
     */
    @Value(path = "ruangao.conf", key = "adListUrl")
    public String adListUrl;

    /**
     * DSP在ADX系统中的id
     */
    @Value(path = "ruangao.conf", key = "dspid")
    public String dspid;

    /**
     * DSP对应的token值
     */
    @Value(path = "ruangao.conf", key = "token")
    public String token;

}

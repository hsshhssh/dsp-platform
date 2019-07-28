package com.xqh.ad.dsp.platform.utils.zkconf;

import lombok.Data;
import org.hssh.common.zkconf.Value;
import org.springframework.stereotype.Component;

/**
 * Created by samson.huang on 2019/7/28
 */
@Data
@Component
public class DspConfig {

    @Value(path = "dsp.conf", key = "gaoDeKey")
    public String gaoDeKey;

    @Value(path = "dsp.conf", key = "tdUrl")
    public String tdUrl;

    @Value(path = "dsp.conf", key = "tdMediaId")
    public String tdMediaId;
}

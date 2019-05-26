package com.xqh.ad.dsp.platform.utils.zkconf;

import lombok.Data;
import org.hssh.common.zkconf.Value;
import org.springframework.stereotype.Component;

/**
 * Created by samson.huang on 2019/5/26
 */
@Data
@Component
public class UploadConfig {

    @Value(path = "upload.conf", key = "savePath")
    public String savePath;

    @Value(path = "upload.conf", key = "fileUrlPrefix")
    public String fileUrlPrefix;

    @Value(path = "upload.conf", key = "tempUrl")
    public String tempUrl;

}

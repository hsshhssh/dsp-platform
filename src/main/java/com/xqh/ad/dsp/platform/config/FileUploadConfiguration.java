package com.xqh.ad.dsp.platform.config;

import com.xqh.ad.dsp.platform.utils.zkconf.UploadConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * Created by samson.huang on 2019/5/23
 */
@Configuration
public class FileUploadConfiguration {

    @Resource
    private UploadConfig uploadConfig;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement(uploadConfig.getTempUrl(), 10*1024*1024L, 10*1024*1024L, 0);
    }
}

package com.xqh.ad.dsp.platform.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Created by samson.huang on 2019/5/2
 */
@Configuration
public class OkHttpConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)//是否开启缓存
                .connectionPool(new ConnectionPool(20, 5, TimeUnit.MINUTES))//连接池
                .connectTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS)
                .build();
    }
}

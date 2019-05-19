package com.xqh.ad.dsp.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class DspPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(DspPlatformApplication.class, args);
    }

}

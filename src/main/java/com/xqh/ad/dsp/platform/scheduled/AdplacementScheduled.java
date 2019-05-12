package com.xqh.ad.dsp.platform.scheduled;

import com.xqh.ad.dsp.platform.service.RuanGaoBidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by samson.huang on 2019/5/4
 */
@Slf4j
@Component
public class AdplacementScheduled {

    @Autowired
    private RuanGaoBidService ruanGaoBidService;

    @Scheduled(cron="0 0/5 * * * *")
    public void ruanGaoScheduled() {
        ruanGaoBidService.asyncAdplacement();
    }

}

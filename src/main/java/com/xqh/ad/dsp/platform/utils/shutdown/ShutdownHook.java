package com.xqh.ad.dsp.platform.utils.shutdown;

import com.xqh.ad.dsp.platform.scheduled.CostSaveScheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by samson.huang on 2019/9/21
 */
@Component
public class ShutdownHook implements ApplicationRunner {

    @Autowired
    private CostSaveScheduled costSaveScheduled;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> costSaveScheduled.saveCost()));
    }
}

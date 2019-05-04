package com.xqh.ad.dsp.platform;

import com.google.common.collect.Sets;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import com.xqh.ad.dsp.platform.service.RuanGaoBidService;
import com.xqh.ad.dsp.platform.utils.ruangao.AdplacementModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DspPlatformApplicationTests {

    @Autowired
    private RuanGaoBidService ruanGaoBidService;
    @Autowired
    private ITPlatformAdplacementService adplacementService;

    @Test
    public void getAdListTest() {
        List<AdplacementModel> adplacementList = ruanGaoBidService.getAdplacementList();
        System.out.println(adplacementList);
    }

    @Test
    public void notInAdplcementIdTest() {
        Set<String> strings = adplacementService.selectNotInAdplacement(Sets.newHashSet("1", "2"));
        System.out.println(strings);
    }

    @Test
    public void contextLoads() {
    }

}

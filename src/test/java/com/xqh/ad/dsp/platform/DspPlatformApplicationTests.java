package com.xqh.ad.dsp.platform;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Sets;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.mapper.TBidRecordMapper;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import com.xqh.ad.dsp.platform.scheduled.OdsBidScheduled;
import com.xqh.ad.dsp.platform.service.RuanGaoBidService;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.common.MybatisPlusHelper;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import com.xqh.ad.dsp.platform.utils.ruangao.AdplacementModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DspPlatformApplicationTests {

    @Autowired
    private RuanGaoBidService ruanGaoBidService;
    @Autowired
    private ITPlatformAdplacementService adplacementService;
    @Autowired
    private TBidRecordMapper tBidRecordMapper;
    @Autowired
    private OdsBidScheduled odsBidScheduled;

    @Test
    public void odsBidScheduledTest() {
        odsBidScheduled.odsBidScheduled();
    }

    @Test
    public void bidRecordGroupByTest() {
        String startDate = CommonUtils.getZeroHourTimeDate(-1);
        String endDate = CommonUtils.getZeroHourTimeDate(0);

        List<Map<String, Object>> pmediaid = tBidRecordMapper.countGroupBy("pmediaid", startDate, endDate);
        //List<Map<String, Object>> pmediaid = tBidRecordMapper.countGroupBy("pmediaid");
        System.out.println(pmediaid);
    }

    @Test
    public void getAdListTest() {
        List<AdplacementModel> adplacementList = ruanGaoBidService.getAdplacementList();
        System.out.println(adplacementList);
    }

    @Test
    public void notInAdplcementIdTest() {
        Set<String> strings = adplacementService.selectNotInAdplacement(Sets.newHashSet("1", "2"), PMediaEnum.RUAN_GAO);
        System.out.println(strings);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void updateTest() {
        TPlatformAdplacement adplacement = new TPlatformAdplacement();
        adplacement.setStatus(2);
        UpdateWrapper<TPlatformAdplacement> wrapper = new UpdateWrapper<>();
        wrapper.eq("pmediaid", 1);
        wrapper.eq("adplacementid", "2");
        adplacementService.update(adplacement, wrapper);
    }

    @Test
    public void selectPage() {

        TPlatformAdplacement adplacement = new TPlatformAdplacement();
        adplacement.setAdplacementid("2");

        Page<TPlatformAdplacement> queryPage = new Page<>(1, 2);
        IPage<TPlatformAdplacement> page = adplacementService.page(queryPage, MybatisPlusHelper.buildQueryWrapper(adplacement, TPlatformAdplacement.class));
        System.out.println(page.getTotal());
        System.out.println(page.getRecords());
    }

}

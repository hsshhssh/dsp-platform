package com.xqh.ad.dsp.platform.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xqh.ad.dsp.platform.model.MediaidVO;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * Created by samson.huang on 2019/8/17
 */
@Slf4j
@Service
public class MediaidService {

    // 缓存的媒体数据
    private List<MediaidVO> mediaidVOList;
    // 分页条数
    private static long page = 2000;
    @Autowired
    private ITPlatformAdplacementService adplacementService;


    @PostConstruct
    private void init() {
        mediaidVOList = Lists.newCopyOnWriteArrayList();
    }

    /**
     * 获取media数据
     * @return
     */
    public List<MediaidVO> getList() {
        if (CollectionUtils.isEmpty(mediaidVOList)) {
            mediaidVOList.addAll(getListFromDB());
        }

        return mediaidVOList;
    }

    /**
     * 清空media数据
     */
    public void clearList() {
        this.mediaidVOList.clear();
    }

    /**
     * db中获取media数据
     * @return
     */
    private List<MediaidVO> getListFromDB() {
        List<MediaidVO> list = Lists.newArrayList();

        // mediaid => madianame
        Map<Integer, String> mediaidMap = Maps.newHashMap();
        int count = adplacementService.count();
        for (long i=0; i<=((count-1)/page); i++) {
            Page<TPlatformAdplacement> pageQuery = new Page<>(i, page);
            IPage<TPlatformAdplacement> page = adplacementService.page(pageQuery);
            for (TPlatformAdplacement ad : page.getRecords()) {
                if (mediaidMap.containsKey(ad.getMediaid())) {
                    // mediaid重复 凭借medianame
                    mediaidMap.put(ad.getMediaid(), ad.getMedianame());
                } else {
                    // media没有重复
                    mediaidMap.put(ad.getMediaid(), ad.getMedianame());
                }
            }
        }

        // map => list
        for (Map.Entry<Integer, String> entry : mediaidMap.entrySet()) {
            list.add(new MediaidVO(entry.getKey(), entry.getValue()));
        }

        return list;
    }

    /**
     * 拼接medianame
     * @param curMedianame
     * @param appendMedianame
     * @return
     */
    private String contractMedianame(String curMedianame, String appendMedianame) {
        return curMedianame + "/" + appendMedianame;
    }
}

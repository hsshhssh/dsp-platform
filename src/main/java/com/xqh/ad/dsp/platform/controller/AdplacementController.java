package com.xqh.ad.dsp.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xqh.ad.dsp.platform.model.AdplacementListDTO;
import com.xqh.ad.dsp.platform.model.AdplacementVO;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import com.xqh.ad.dsp.platform.utils.common.MybatisPlusHelper;
import com.xqh.ad.dsp.platform.utils.common.PageResult;
import com.xqh.ad.dsp.platform.utils.common.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * Created by samson.huang on 2019/5/19
 */
@Slf4j
@RestController
@RequestMapping("/admin/adplacement")
public class AdplacementController {

    @Autowired
    private ITPlatformAdplacementService adplacementService;

    /**
     * 广告位列表
     * @param listDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseBean<PageResult<AdplacementVO>> list(@RequestBody AdplacementListDTO listDTO) {
        log.info("广告位列表-请求参数:{}", JSONObject.toJSONString(listDTO));
        Page<TPlatformAdplacement> pageQuery = new Page<>(listDTO.getPage(), listDTO.getLimit());
        QueryWrapper<TPlatformAdplacement> queryWrapper = MybatisPlusHelper.buildQueryWrapper(listDTO, TPlatformAdplacement.class);
        queryWrapper.orderByDesc("id");

        IPage<TPlatformAdplacement> page = adplacementService.page(pageQuery, queryWrapper);


        PageResult<AdplacementVO> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getRecords().stream().map(AdplacementVO::new).collect(Collectors.toList()));
        return new ResponseBean<>(pageResult);
    }


}

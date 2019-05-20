package com.xqh.ad.dsp.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xqh.ad.dsp.platform.model.MaterialListDTO;
import com.xqh.ad.dsp.platform.model.MaterialVO;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformMaterialService;
import com.xqh.ad.dsp.platform.utils.common.MybatisPlusHelper;
import com.xqh.ad.dsp.platform.utils.common.PageResult;
import com.xqh.ad.dsp.platform.utils.common.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * Created by samson.huang on 2019/5/19
 */
@Slf4j
@RestController
@RequestMapping("admin/material")
public class MaterialController {

    @Resource
    private ITPlatformMaterialService materialService;

    @PostMapping("/list")
    public ResponseBean<PageResult<MaterialVO>> list(@RequestBody MaterialListDTO listDTO) {
        Page<TPlatformMaterial> pageQuery = new Page<>(listDTO.getPage(), listDTO.getSize());
        QueryWrapper<TPlatformMaterial> queryWrapper = MybatisPlusHelper.buildQueryWrapper(listDTO, TPlatformMaterial.class);
        queryWrapper.orderByDesc("id");

        IPage<TPlatformMaterial> page = materialService.page(pageQuery, queryWrapper);
        PageResult<MaterialVO> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getRecords().stream().map(MaterialVO::new).collect(Collectors.toList()));
        return new ResponseBean<>(pageResult);
    }
}

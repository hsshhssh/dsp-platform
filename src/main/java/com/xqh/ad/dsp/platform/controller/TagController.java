package com.xqh.ad.dsp.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xqh.ad.dsp.platform.model.TagListDTO;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TTag;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITTagService;
import com.xqh.ad.dsp.platform.utils.common.MybatisPlusHelper;
import com.xqh.ad.dsp.platform.utils.common.PageResult;
import com.xqh.ad.dsp.platform.utils.common.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by samson.huang on 2019/7/28
 */
@Slf4j
@RestController
@RequestMapping("admin/tag")
public class TagController {

    @Resource
    private ITTagService tagService;

    @RequestMapping("list")
    public ResponseBean<PageResult<TTag>> list(@RequestBody TagListDTO listDTO) {

        Page<TTag> pageQuery = new Page<>(listDTO.getPage(), listDTO.getLimit());
        QueryWrapper<TTag> queryWrapper = MybatisPlusHelper.buildQueryWrapper(listDTO, TTag.class);
        queryWrapper.orderByDesc("id");

        IPage<TTag> page = tagService.page(pageQuery, queryWrapper);
        PageResult<TTag> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getRecords());

        return new ResponseBean<>(pageResult);
    }

    @RequestMapping("get")
    public ResponseBean<TTag> get(String id) {
        return new ResponseBean<>(tagService.getById(Long.valueOf(id)));
    }

    @RequestMapping("save")
    public ResponseBean<String> save(@RequestBody TTag tTag) {
        if (tTag.getId() == null) {
            tagService.save(tTag);
        } else {
            tagService.updateById(tTag);
        }
        return new ResponseBean<>("操作成功");
    }
}

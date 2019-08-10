package com.xqh.ad.dsp.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xqh.ad.dsp.platform.model.AmUpdateRecordListDTO;
import com.xqh.ad.dsp.platform.model.AmUpdateRecordVO;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TAmUpdateRecord;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TOdsBidDate;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITAmUpdateRecordService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITOdsBidDateService;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.common.MybatisPlusHelper;
import com.xqh.ad.dsp.platform.utils.common.PageResult;
import com.xqh.ad.dsp.platform.utils.common.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * Created by samson.huang on 2019/8/10
 */
@Slf4j
@RestController
@RequestMapping("/admin/strategy/record")
public class AmUpdateRecordController {

    @Resource
    private ITAmUpdateRecordService amUpdateRecordService;


    @RequestMapping("list")
    public ResponseBean<PageResult<AmUpdateRecordVO>> list(@RequestBody AmUpdateRecordListDTO listDTO) {

        if (listDTO.getTimestamp_gte_ignore() != null) {
            listDTO.setRecord_time_gte(CommonUtils.getDateTimeOfTimestamp(listDTO.getTimestamp_gte_ignore()));
        }
        if (listDTO.getTimestamp_lte_ignore() != null) {
            listDTO.setRecord_time_lte(CommonUtils.getDateTimeOfTimestamp(listDTO.getTimestamp_lte_ignore()));
        }

        Page<TAmUpdateRecord> pageQuery = new Page<>(listDTO.getPage(), listDTO.getLimit());
        QueryWrapper<TAmUpdateRecord> queryWrapper = MybatisPlusHelper.buildQueryWrapper(listDTO, TAmUpdateRecord.class);
        queryWrapper.orderByDesc("id");

        IPage<TAmUpdateRecord> page = amUpdateRecordService.page(pageQuery, queryWrapper);
        PageResult<AmUpdateRecordVO> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getRecords().stream().map(AmUpdateRecordVO::new).collect(Collectors.toList()));

        return new ResponseBean<>(pageResult);
    }
}

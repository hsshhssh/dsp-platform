package com.xqh.ad.dsp.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xqh.ad.dsp.platform.model.OdsBidDateListDTO;
import com.xqh.ad.dsp.platform.model.OdsBidDateVO;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TOdsBidDate;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITOdsBidDateService;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.common.MybatisPlusHelper;
import com.xqh.ad.dsp.platform.utils.common.PageResult;
import com.xqh.ad.dsp.platform.utils.common.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * Created by samson.huang on 2019/7/4
 */
@Slf4j
@RestController
@RequestMapping("/admin/ods/bid")
public class OdsBidDateController {

    @Autowired
    private ITOdsBidDateService odsBidDateService;

    @PostMapping("list")
    public ResponseBean<PageResult<OdsBidDateVO>> list(@RequestBody OdsBidDateListDTO listDTO) {
        log.info("统计列表-请求参数:{}", JSONObject.toJSONString(listDTO));
        if (listDTO.getOdsTimestamp_gte_ignore() != null) {
            listDTO.setOds_date_gte(CommonUtils.getDateTimeOfTimestamp(listDTO.getOdsTimestamp_gte_ignore()));
        }
        if (listDTO.getOdsTimestamp_lte_ignore() != null) {
            listDTO.setOds_date_lte(CommonUtils.getDateTimeOfTimestamp(listDTO.getOdsTimestamp_lte_ignore()));
        }

        Page<TOdsBidDate> pageQuery = new Page<>(listDTO.getPage(), listDTO.getLimit());
        QueryWrapper<TOdsBidDate> queryWrapper = MybatisPlusHelper.buildQueryWrapper(listDTO, TOdsBidDate.class);

        IPage<TOdsBidDate> page = odsBidDateService.page(pageQuery, queryWrapper);
        PageResult<OdsBidDateVO> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getRecords().stream().map(OdsBidDateVO::new).collect(Collectors.toList()));
        return new ResponseBean<>(pageResult);
    }

}

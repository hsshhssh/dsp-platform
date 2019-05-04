package com.xqh.ad.dsp.platform.controller;

import com.xqh.ad.dsp.platform.service.RuanGaoBidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by samson.huang on 2019/5/4
 */
@Slf4j
@RestController
@RequestMapping("ruangao")
public class RuangaoController {

    @Autowired
    private RuanGaoBidService ruanGaoBidService;

    @GetMapping("async/adplacement")
    public String asyncAdPlacement() {
        ruanGaoBidService.asyncAdplacement();
        return "success";
    }
}

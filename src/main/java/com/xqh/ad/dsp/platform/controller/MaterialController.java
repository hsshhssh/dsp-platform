package com.xqh.ad.dsp.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xqh.ad.dsp.platform.model.MaterialListDTO;
import com.xqh.ad.dsp.platform.model.MaterialVO;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformMaterialService;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.SeqNoUtils;
import com.xqh.ad.dsp.platform.utils.common.MybatisPlusHelper;
import com.xqh.ad.dsp.platform.utils.common.PageResult;
import com.xqh.ad.dsp.platform.utils.common.ResponseBean;
import com.xqh.ad.dsp.platform.utils.common.ResponseEnum;
import com.xqh.ad.dsp.platform.utils.enums.PMediaEnum;
import com.xqh.ad.dsp.platform.utils.enums.SeqBizEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Resource
    private ITPlatformAdplacementService adplacementService;
    @Resource
    private SeqNoUtils seqNoUtils;

    @PostMapping("/list")
    public ResponseBean<PageResult<MaterialVO>> list(@RequestBody MaterialListDTO listDTO) {
        Page<TPlatformMaterial> pageQuery = new Page<>(listDTO.getPage(), listDTO.getLimit());
        QueryWrapper<TPlatformMaterial> queryWrapper = MybatisPlusHelper.buildQueryWrapper(listDTO, TPlatformMaterial.class);
        queryWrapper.orderByDesc("id");

        IPage<TPlatformMaterial> page = materialService.page(pageQuery, queryWrapper);
        PageResult<MaterialVO> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getRecords().stream().map(MaterialVO::new).collect(Collectors.toList()));
        return new ResponseBean<>(pageResult);
    }

    @PostMapping("get")
    public ResponseBean<JSONObject> get(String id) {
        // QueryWrapper<TPlatformAdplacement> adQuery = new QueryWrapper<>();
        // adQuery.eq("padplacementid", padplacementid);
        // TPlatformAdplacement adplacement = adplacementService.getOne(adQuery);
        // if (null == adplacement) {
        //     return new ResponseBean<>(ResponseEnum.AD_NOT_EXIT);
        // }
        //
        // respJson.put("padplacementid", padplacementid);
        // respJson.put("pmediaStr", PMediaEnum.getName(adplacement.getPmediaid()));
        // respJson.put("pmediaid", adplacement.getPmediaid());
        // respJson.put("adplacementid", adplacement.getAdplacementid());
        // respJson.put("adplacementname", adplacement.getAdplacementname());

        JSONObject respJson = new JSONObject();
        TPlatformMaterial material = materialService.getById(Long.valueOf(id));
        if (null != material) {
            // 未保存
            respJson.put("id", Long.valueOf(id));
            respJson.put("price", material.getPrice());
            respJson.put("crid", material.getCrid());
            respJson.put("adtype", material.getAdtype());
            respJson.put("ext", CommonUtils.strToObj(material.getExt()));
            respJson.put("name", material.getName());
            respJson.put("adm", JSONObject.parseObject(material.getAdm()));
        } else {
            JSONObject adm = new JSONObject();
            adm.put("adId", seqNoUtils.getNextSeqNo(SeqBizEnum.ADM_AD_ID));
            JSONObject inner = new JSONObject();
            JSONObject pc = new JSONObject();
            JSONObject mobile = new JSONObject();
            JSONObject video = new JSONObject();
            inner.put("pc", pc);
            inner.put("video", video);
            inner.put("mobile", mobile);
            adm.put("dspApiMaterialInnerReqDTO", inner);
            respJson.put("adm", adm);

            // 创意id
            respJson.put("crid", seqNoUtils.getNextSeqNo(SeqBizEnum.CRID));
        }

        return new ResponseBean<>(respJson);
    }

    @PostMapping("save")
    public ResponseBean<String> save(@RequestBody JSONObject reqJson) {
        // String padplacementid = reqJson.getString("padplacementid");
        // if (StringUtils.isBlank(padplacementid)) {
        //     return new ResponseBean<>(ResponseEnum.AD_NOT_EXIT);
        // }
        //
        // QueryWrapper<TPlatformMaterial> queryWrapper = new QueryWrapper<>();
        // queryWrapper.eq("padplacementid", padplacementid);
        // TPlatformMaterial queryMaterial = materialService.getOne(queryWrapper);
        TPlatformMaterial material = JSONObject.parseObject(reqJson.toJSONString(), TPlatformMaterial.class);
        if (material.getId() == null) {
            materialService.save(material);
        } else {
            materialService.updateById(material);
        }
        return new ResponseBean<>("保存成功");
    }

}

package com.xqh.ad.dsp.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xqh.ad.dsp.platform.model.AdpMaterialListDTO;
import com.xqh.ad.dsp.platform.model.AdplacementMaterialSaveDTO;
import com.xqh.ad.dsp.platform.model.AdpMaterialVO;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TAdplacementMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITAdplacementMaterialService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformMaterialService;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.common.MybatisPlusHelper;
import com.xqh.ad.dsp.platform.utils.common.PageResult;
import com.xqh.ad.dsp.platform.utils.common.ResponseBean;
import com.xqh.ad.dsp.platform.utils.common.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by samson.huang on 2019/7/28
 */
@Slf4j
@RestController
@RequestMapping("/admin/strategy")
public class AdplacementMaterialController {

    @Resource
    private ITAdplacementMaterialService adplacementMaterialService;
    @Resource
    private ITPlatformAdplacementService adplacementService;
    @Resource
    private ITPlatformMaterialService materialService;


    @RequestMapping("list")
    public ResponseBean<PageResult<AdpMaterialVO>> list(@RequestBody AdpMaterialListDTO listDTO) {
        Page<TAdplacementMaterial> pageQuery = new Page<>(listDTO.getPage(), listDTO.getLimit());
        QueryWrapper<TAdplacementMaterial> queryWrapper = MybatisPlusHelper.buildQueryWrapper(listDTO, TAdplacementMaterial.class);
        queryWrapper.orderByDesc("id");

        IPage<TAdplacementMaterial> page = adplacementMaterialService.page(pageQuery, queryWrapper);
        PageResult<AdpMaterialVO> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getRecords().stream().map(AdpMaterialVO::new).collect(Collectors.toList()));

        return new ResponseBean<>(pageResult);
    }

    @RequestMapping("get")
    public ResponseBean<AdpMaterialVO> get(String id) {
        TAdplacementMaterial adplacementMaterial = adplacementMaterialService.getById(Long.valueOf(id));
        if (null == adplacementMaterial) {
            return new ResponseBean<>(ResponseEnum.ADPLACEMENT_MATERIAL_NOT_EXIT);
        }

        AdpMaterialVO vo = new AdpMaterialVO(adplacementMaterial);

        // TODO 广告位信息 素材信息
        return new ResponseBean<>(vo);
    }

    @RequestMapping("save")
    public ResponseBean<String> save(@RequestBody AdplacementMaterialSaveDTO saveDTO) {
        TPlatformAdplacement adplacement = adplacementService.selectByAdplacementId(saveDTO.getPmediaid(), saveDTO.getAdplacementid());
        if (null == adplacement) {
            return new ResponseBean<>(ResponseEnum.AD_NOT_EXIT);
        }

        TPlatformMaterial material = materialService.getById(saveDTO.getMaterialid());
        if (null == material) {
            return new ResponseBean<>(ResponseEnum.MATERIAL_NOT_EXIT);
        }

        TAdplacementMaterial strategy = new TAdplacementMaterial();
        strategy.setName(saveDTO.getName());
        strategy.setPadplacementid(adplacement.getPadplacementid());
        strategy.setPmediaid(adplacement.getPmediaid());
        strategy.setAdplacementid(adplacement.getAdplacementid());
        strategy.setAdplacementname(adplacement.getAdplacementname());
        strategy.setMediaid(adplacement.getMediaid());
        strategy.setMaterialid(material.getId());
        strategy.setHours(saveDTO.getHours());
        strategy.setCity(saveDTO.getCity());
        strategy.setNetwork(saveDTO.getNetwork());
        strategy.setSex(saveDTO.getSex());
        strategy.setAge(saveDTO.getAge());
        strategy.setTag(saveDTO.getTag());

        if (saveDTO.getId() != null) {
            // 更新
            strategy.setId(saveDTO.getId());
            // 状态为禁用
            strategy.setStatus(UUID.randomUUID().toString());
            adplacementMaterialService.updateById(strategy);
        } else {
            // 修改
            adplacementMaterialService.save(strategy);
        }
        return new ResponseBean<>("操作成功");
    }

    /**
     * 启用
     * @param id
     * @return
     */
    @RequestMapping("enable")
    public ResponseBean<String> enable(String id) {
        TAdplacementMaterial strategy = adplacementMaterialService.getById(Long.valueOf(id));
        if (null == strategy) {
            return new ResponseBean<>(ResponseEnum.ADPLACEMENT_MATERIAL_NOT_EXIT);
        }

        if (Objects.equals(CommonUtils.ENABLE_STATUS, strategy.getStatus())) {
            return new ResponseBean<>("操作成功");
        }

        QueryWrapper<TAdplacementMaterial> query = new QueryWrapper<>();
        query.eq("padplacementid", strategy.getPadplacementid());
        query.eq("status", CommonUtils.ENABLE_STATUS);
        TAdplacementMaterial enableStrategy = adplacementMaterialService.getOne(query);

        TAdplacementMaterial toDisable = null;
        if (null != enableStrategy) {
             toDisable = new TAdplacementMaterial();
             toDisable.setId(enableStrategy.getId());
             toDisable.setStatus(UUID.randomUUID().toString());
        }
        TAdplacementMaterial toEnable = new TAdplacementMaterial();
        toEnable.setId(strategy.getId());
        toEnable.setStatus(CommonUtils.ENABLE_STATUS);


        try {
            adplacementMaterialService.toEnableAndToDisable(toEnable, toDisable);
        } catch (Exception e) {
            return new ResponseBean<>(ResponseEnum.UNKNOWN);
        }
        return new ResponseBean<>("操作成功");
    }

    /**
     * 禁用
     * @param id
     * @return
     */
    @RequestMapping("disable")
    public ResponseBean<String> disable(String id) {
        TAdplacementMaterial strategy = adplacementMaterialService.getById(Long.valueOf(id));

        if (null == strategy) {
            return new ResponseBean<>(ResponseEnum.ADPLACEMENT_MATERIAL_NOT_EXIT);
        }

        if (!Objects.equals(CommonUtils.ENABLE_STATUS, strategy.getStatus())) {
            return new ResponseBean<>("操作成功");
        }

        // 更新状态
        TAdplacementMaterial updateStrategy = new TAdplacementMaterial();
        updateStrategy.setId(strategy.getId());
        updateStrategy.setStatus(UUID.randomUUID().toString());
        adplacementMaterialService.updateById(updateStrategy);

        return new ResponseBean<>("操作成功");
    }
}

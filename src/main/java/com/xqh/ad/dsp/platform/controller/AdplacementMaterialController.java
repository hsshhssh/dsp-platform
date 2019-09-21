package com.xqh.ad.dsp.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.xqh.ad.dsp.platform.model.AdpMaterialListDTO;
import com.xqh.ad.dsp.platform.model.AdplacementMaterialSaveDTO;
import com.xqh.ad.dsp.platform.model.AdpMaterialVO;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TAdplacementMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformAdplacement;
import com.xqh.ad.dsp.platform.mybatisplus.entity.TPlatformMaterial;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITAdplacementMaterialService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformAdplacementService;
import com.xqh.ad.dsp.platform.mybatisplus.service.ITPlatformMaterialService;
import com.xqh.ad.dsp.platform.utils.AsyncUtils;
import com.xqh.ad.dsp.platform.utils.CommonUtils;
import com.xqh.ad.dsp.platform.utils.common.MybatisPlusHelper;
import com.xqh.ad.dsp.platform.utils.common.PageResult;
import com.xqh.ad.dsp.platform.utils.common.ResponseBean;
import com.xqh.ad.dsp.platform.utils.common.ResponseEnum;
import com.xqh.ad.dsp.platform.utils.enums.AmUpdateTypeEnum;
import com.xqh.ad.dsp.platform.utils.enums.CityEnum;
import com.xqh.ad.dsp.platform.utils.enums.DelEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
    @Resource
    private AsyncUtils asyncUtils;


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

        vo.setHoursList(Splitter.on(",").omitEmptyStrings().splitToList(vo.getHours()));
        vo.setNetworkList(Splitter.on(",").omitEmptyStrings().splitToList(vo.getNetwork()));
        vo.setTagList(Splitter.on(",").omitEmptyStrings().splitToList(vo.getTag()));

        // 城市
        List<List<String>> cityList = Lists.newArrayList();
        for (String cityCode : Splitter.on(",").omitEmptyStrings().splitToList(vo.getCity())) {
            CityEnum cityEnum = CityEnum.get(cityCode);
            if (cityEnum != null) {
                List<String> l = Lists.newArrayList();
                if(StringUtils.isNotBlank(cityEnum.getProvince())) {
                    l.add(cityEnum.getProvince());
                }
                l.add(cityEnum.getCode());

                cityList.add(l);
            }
        }
        vo.setCityList(cityList);


        // TODO 广告位信息 素材信息
        return new ResponseBean<>(vo);
    }

    @RequestMapping("copy")
    public ResponseBean<String> copy(String id) {
        TAdplacementMaterial adplacementMaterial = adplacementMaterialService.getById(Long.valueOf(id));
        if (null == adplacementMaterial) {
            return new ResponseBean<>(ResponseEnum.ADPLACEMENT_MATERIAL_NOT_EXIT);
        }

        TAdplacementMaterial copy = new TAdplacementMaterial();
        BeanUtils.copyProperties(adplacementMaterial, copy);
        copy.setId(null);
        copy.setName("");
        copy.setStatus(UUID.randomUUID().toString());
        copy.setCreateTime(LocalDateTime.now());
        copy.setUpdateTime(LocalDateTime.now());
        adplacementMaterialService.save(copy);
        return new ResponseBean<>("操作成功");
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
        strategy.setMedianame(adplacement.getMedianame());
        strategy.setMaterialid(material.getId());
        strategy.setMaterialname(material.getName());
        strategy.setHours(Joiner.on(",").skipNulls().join(saveDTO.getHoursList()));
        if (!CollectionUtils.isEmpty(saveDTO.getCityList())) {
            List<String> cL = Lists.newArrayList();
            for (List<String> l : saveDTO.getCityList()) {
                if (l.size() > 1) {
                    cL.add(l.get(1));
                } else {
                    cL.add(l.get(0));
                }
            }
            strategy.setCity(Joiner.on(",").skipNulls().join(cL));
        }
        strategy.setNetwork(Joiner.on(",").skipNulls().join(saveDTO.getNetworkList()));
        strategy.setSex(saveDTO.getSex());
        strategy.setAge(saveDTO.getAge());
        strategy.setTag(Joiner.on(",").skipNulls().join(saveDTO.getTagList()));
        strategy.setRemark(saveDTO.getRemark());
        strategy.setPmoUrl(saveDTO.getPmoUrl());
        strategy.setCmoUrl(saveDTO.getCmoUrl());
        strategy.setPrice(saveDTO.getPrice());
        strategy.setBudget(saveDTO.getBudget());

        // 更新前记录
        TAdplacementMaterial oldStragy = adplacementMaterialService.getById(saveDTO.getId());

        if (oldStragy != null) {
            // 更新
            strategy.setId(saveDTO.getId());
            adplacementMaterialService.updateById(strategy);

            // 记录变更历史
            if (!Objects.equals(oldStragy.getPrice(), strategy.getPrice())) {
                asyncUtils.handleAmUpdateRecord(saveDTO.getId(), strategy.getName(), AmUpdateTypeEnum.PRICE, strategy.getPrice().toString(), oldStragy.getPrice().toString());
            }

        } else {
            // 新增 状态为禁用
            strategy.setStatus(UUID.randomUUID().toString());
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

        TAdplacementMaterial toEnable = new TAdplacementMaterial();
        toEnable.setId(strategy.getId());
        toEnable.setStatus(CommonUtils.ENABLE_STATUS);
        adplacementMaterialService.updateById(toEnable);

        // 记录变更历史
        asyncUtils.handleAmUpdateRecord(Long.valueOf(id), strategy.getName(), AmUpdateTypeEnum.STATUS, CommonUtils.ENABLE_NAME, CommonUtils.DISABLE_NAME);


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
        updateStrategy.setStatus(CommonUtils.DISABLE_STATUS);
        adplacementMaterialService.updateById(updateStrategy);

        asyncUtils.handleAmUpdateRecord(Long.valueOf(id), strategy.getName(), AmUpdateTypeEnum.STATUS, CommonUtils.DISABLE_NAME, CommonUtils.ENABLE_NAME);

        return new ResponseBean<>("操作成功");
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public ResponseBean<String> delete(String id) {
        TAdplacementMaterial strategy = adplacementMaterialService.getById(Long.valueOf(id));
        if (null == strategy) {
            return new ResponseBean<>(ResponseEnum.ADPLACEMENT_MATERIAL_NOT_EXIT);
        }

        if (Objects.equals(DelEnum.YES.getCode(), strategy.getDel())) {
            return new ResponseBean<>("操作成功");
        }

        // 更新状态
        TAdplacementMaterial updateStrategy = new TAdplacementMaterial();
        updateStrategy.setId(strategy.getId());
        updateStrategy.setDel(DelEnum.YES.getCode());
        adplacementMaterialService.updateById(updateStrategy);

        asyncUtils.handleAmUpdateRecord(Long.valueOf(id), strategy.getName(), AmUpdateTypeEnum.STATUS, DelEnum.YES.getName(), DelEnum.NO.getName());

        return new ResponseBean<>("操作成功");
    }

    /**
     * 恢复
     * @param id
     * @return
     */
    @RequestMapping("recovery")
    public ResponseBean<String> recovery(String id) {
        TAdplacementMaterial strategy = adplacementMaterialService.getById(Long.valueOf(id));
        if (null == strategy) {
            return new ResponseBean<>(ResponseEnum.ADPLACEMENT_MATERIAL_NOT_EXIT);
        }

        if (Objects.equals(DelEnum.NO.getCode(), strategy.getDel())) {
            return new ResponseBean<>("操作成功");
        }

        // 更新状态
        TAdplacementMaterial updateStrategy = new TAdplacementMaterial();
        updateStrategy.setId(strategy.getId());
        updateStrategy.setDel(DelEnum.NO.getCode());
        adplacementMaterialService.updateById(updateStrategy);

        asyncUtils.handleAmUpdateRecord(Long.valueOf(id), strategy.getName(), AmUpdateTypeEnum.STATUS, DelEnum.NO.getName(), DelEnum.YES.getName());

        return new ResponseBean<>("操作成功");
    }


    @RequestMapping("save/price")
    public ResponseBean<String> savePrice(String id, BigDecimal price) {
        TAdplacementMaterial strategy = adplacementMaterialService.getById(Long.valueOf(id));

        if (null == strategy) {
            return new ResponseBean<>(ResponseEnum.ADPLACEMENT_MATERIAL_NOT_EXIT);
        }

        TAdplacementMaterial updateStrategy = new TAdplacementMaterial();
        updateStrategy.setId(strategy.getId());
        updateStrategy.setPrice(price);
        adplacementMaterialService.updateById(updateStrategy);

        // 记录变更历史
        if (!Objects.equals(strategy.getPrice(), price)) {
            asyncUtils.handleAmUpdateRecord(Long.valueOf(id), strategy.getName(), AmUpdateTypeEnum.PRICE, price.toString(), strategy.getPrice().toString());
        }

        return new ResponseBean<>("操作成功");
    }

    @RequestMapping("save/budget")
    public ResponseBean<String> saveBudget(String id, Long budget) {
        TAdplacementMaterial strategy = adplacementMaterialService.getById(Long.valueOf(id));

        if (null == strategy) {
            return new ResponseBean<>(ResponseEnum.ADPLACEMENT_MATERIAL_NOT_EXIT);
        }

        TAdplacementMaterial updateStrategy = new TAdplacementMaterial();
        updateStrategy.setId(strategy.getId());
        updateStrategy.setBudget(budget);
        adplacementMaterialService.updateById(updateStrategy);

        // 记录变更历史
        if (!Objects.equals(strategy.getBudget(), budget)) {
            asyncUtils.handleAmUpdateRecord(Long.valueOf(id), strategy.getName(), AmUpdateTypeEnum.BUDGET, budget.toString(), strategy.getBudget().toString());
        }

        return new ResponseBean<>("操作成功");
    }

}

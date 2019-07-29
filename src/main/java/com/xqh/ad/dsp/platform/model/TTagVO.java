package com.xqh.ad.dsp.platform.model;

import com.xqh.ad.dsp.platform.mybatisplus.entity.TTag;
import com.xqh.ad.dsp.platform.utils.enums.TagIsTdEnum;
import com.xqh.ad.dsp.platform.utils.enums.TagTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Created by samson.huang on 2019/7/29
 */
@Data
@NoArgsConstructor
public class TTagVO {

    private Long id;
    private String name;
    private String tdKey;
    private Integer tagType;
    private Integer isTd;

    public String tagTypeName;
    public String isTdName;

    public String tagTypeStr;
    public String isTdStr;


    public TTagVO(TTag tTag) {
        BeanUtils.copyProperties(tTag, this);

        TagTypeEnum tagTypeEnum = TagTypeEnum.get(this.tagType);
        this.tagTypeName = tagTypeEnum != null ? tagTypeEnum.getName() : "";
        this.tagTypeStr = String.valueOf(this.tagType);

        TagIsTdEnum tagIsTdEnum = TagIsTdEnum.get(this.isTd);
        this.isTdName = tagIsTdEnum != null ? tagIsTdEnum.getName() : "";
        this.isTdStr = String.valueOf(this.isTd);

    }
}

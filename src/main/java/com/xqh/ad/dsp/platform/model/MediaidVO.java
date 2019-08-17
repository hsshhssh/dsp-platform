package com.xqh.ad.dsp.platform.model;

import lombok.Data;

/**
 * Created by samson.huang on 2019/8/17
 */
@Data
public class MediaidVO {

    /**
     * 媒体id
     */
    private Integer mediaid;
    /**
     * 媒体名称
     */
    private String medianame;

    public MediaidVO() {
    }

    public MediaidVO(Integer mediaid, String medianame) {
        this.mediaid = mediaid;
        this.medianame = medianame;
    }
}

package com.xqh.ad.dsp.platform.utils.common;

import lombok.Data;

@Data
public class ResponseBean<T> {

    private String code;
    private String message;

    private T data;

    public ResponseBean(T data) {
        this.code = ResponseEnum.SUCCESS.getCode();
        this.message = ResponseEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public ResponseBean(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public ResponseBean(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseBean(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
    }

}

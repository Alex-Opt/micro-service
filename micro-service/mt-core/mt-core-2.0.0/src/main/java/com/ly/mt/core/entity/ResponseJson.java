package com.ly.mt.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 响应实体
 * @Author taoye
 */
@ApiModel
public class ResponseJson {
    @ApiModelProperty(value = "状态码", required = true)
    private String code;
    @ApiModelProperty(value = "描述信息", required = true)
    private String msg;
    @ApiModelProperty("返回数据")
    private String result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{code=\"" + code + "\", msg=\"" + msg + "\", result=" + result + "}";
    }
}
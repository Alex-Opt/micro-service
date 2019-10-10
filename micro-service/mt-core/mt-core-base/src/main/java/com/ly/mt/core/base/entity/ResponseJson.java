package com.ly.mt.core.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 响应实体
 *
 * @author taoye
 */
@ApiModel
public class ResponseJson<T> {
    @ApiModelProperty(value = "状态码", required = true)
    private String code;
    @ApiModelProperty(value = "描述信息", required = true)
    private String msg;
    @JSONField(name = "result")
    @ApiModelProperty("返回对象")
    private T result;

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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{code=\"" + code + "\", msg=\"" + msg + "\", result=" + result + "}";
    }
}
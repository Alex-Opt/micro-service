package com.ly.mt.cabinet.c.rujin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RujinNotifyVo {

    @ApiModelProperty("0=成功， 大于零失败")
    private String error;
    @ApiModelProperty("失败的相关信息")
    private String emsg;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEmsg() {
        return emsg;
    }

    public void setEmsg(String emsg) {
        this.emsg = emsg;
    }
}
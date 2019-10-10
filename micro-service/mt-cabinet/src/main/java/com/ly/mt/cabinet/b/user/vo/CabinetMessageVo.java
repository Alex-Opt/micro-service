package com.ly.mt.cabinet.b.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 格子柜用户消息
 * @Description user
 * @Author whl
 */
@ApiModel(value="格子柜用户消息")
public class CabinetMessageVo {
    @ApiModelProperty(value = "是否有未读格子柜补货消息 0:未读 1:已读", required = true)
    private String gzgReadStatus;

    @ApiModelProperty(value = "是否有未读展柜补货消息 0:未读 1:已读", required = true)
    private String zgReadStatus;

    public String getGzgReadStatus() {
        return gzgReadStatus;
    }

    public void setGzgReadStatus(String gzgReadStatus) {
        this.gzgReadStatus = gzgReadStatus;
    }

    public String getZgReadStatus() {
        return zgReadStatus;
    }

    public void setZgReadStatus(String zgReadStatus) {
        this.zgReadStatus = zgReadStatus;
    }
}
package com.ly.mt.blue.tooth.subsidary.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 固件升级VO
 * @Description FirmwareVo
 * @Author whl
 */
@ApiModel(value="固件信息")
public class FirmwareVo {
    @ApiModelProperty(value = "版本号 version:1.0.1", required = true)
    private String version;
    @ApiModelProperty(value = "下载url", required = true)
    private String url;
    @ApiModelProperty("终端类型 1:ios 2:安卓")
    private String terminalType;
    @ApiModelProperty(value = "本次更新内容", required = true)
    private String remark;
    @ApiModelProperty(value ="是否强制更新0:否 1:是",required = true)
    private String forcedUpdating;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getForcedUpdating() {
        return forcedUpdating;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public void setForcedUpdating(String forcedUpdating) {
        this.forcedUpdating = forcedUpdating;
    }
}
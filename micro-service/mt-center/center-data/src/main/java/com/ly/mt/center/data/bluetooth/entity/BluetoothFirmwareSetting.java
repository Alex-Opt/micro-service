package com.ly.mt.center.data.bluetooth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BluetoothFirmwareSetting {
    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty("类型1:APP升级 2:固件升级")
    private String type;
    @ApiModelProperty("终端类型 1:ios 2:安卓")
    private String terminal_type;
    @ApiModelProperty("版本编号")
    private String version;
    @ApiModelProperty("固件地址")
    private String url;
    @ApiModelProperty("更新内容备注")
    private String remark;
    @ApiModelProperty("是否强制更新0:否 1:是")
    private String forced_updating;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("修改时间")
    private String modify_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTerminal_type() {
        return terminal_type;
    }

    public void setTerminal_type(String terminal_type) {
        this.terminal_type = terminal_type;
    }

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

    public String getForced_updating() {
        return forced_updating;
    }

    public void setForced_updating(String forced_updating) {
        this.forced_updating = forced_updating;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

}
package com.ly.mt.blue.tooth.taste.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 首页烟弹信息VO
 */
@ApiModel(value="烟弹信息")
public class BluetoothTasteInfoVo {
    @ApiModelProperty(value = "烟弹id", required = true)
    private String id;
    @ApiModelProperty(value = "烟弹名称", required = true)
    private String taste;//烟弹名称
    @ApiModelProperty(value = "烟弹口味key", required = true)
    private String tasteKey;//口味key
    @ApiModelProperty(value = "烟弹消耗数量", required = true)
    private String num;//消耗数量
    @ApiModelProperty(value = "烟弹图标URL", required = true)
    private String icon_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTasteKey() {
        return tasteKey;
    }

    public void setTasteKey(String tasteKey) {
        this.tasteKey = tasteKey;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }
}
package com.ly.mt.blue.tooth.taste.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户添加烟弹对象
 */
@ApiModel(value = "烟弹信息")
public class BluetoothUserTasteVo {
    @ApiModelProperty(value = "烟弹id", required = true)
    private String id;
    @ApiModelProperty(value = "用户id", required = true)
    private String userId;//用户id
    @ApiModelProperty(value = "图标url", required = true)
    private String iconUrl;//图标url
    @ApiModelProperty(value = "烟弹key", required = true)
    private String tasteKey;//烟弹KEY
    @ApiModelProperty(value = "添加时间", required = true)
    private String addTime;//添加时间
    @ApiModelProperty(value = "烟弹名称", required = true)
    private String taste;//烟弹名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTasteKey() {
        return tasteKey;
    }

    public void setTasteKey(String tasteKey) {
        this.tasteKey = tasteKey;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }
}
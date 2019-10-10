package com.ly.mt.center.data.bluetooth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户添加烟弹
 */
@ApiModel
public class BluetoothUserTasteInfo {
    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty("口味")
    private String taste;
    @ApiModelProperty("用户id")
    private String user_id;
    @ApiModelProperty("用户添加数量")
    private String taste_key;
    @ApiModelProperty("口味key")
    private String num;
    @ApiModelProperty("烟弹图标URL")
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTaste_key() {
        return taste_key;
    }

    public void setTaste_key(String taste_key) {
        this.taste_key = taste_key;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }
}
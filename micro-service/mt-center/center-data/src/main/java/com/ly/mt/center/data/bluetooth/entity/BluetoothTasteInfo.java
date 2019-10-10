package com.ly.mt.center.data.bluetooth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BluetoothTasteInfo {
    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty("口味")
    private String taste;
    @ApiModelProperty("口味英文")
    private String taste_key;
    @ApiModelProperty("口味状态（0：正常，1：取消状态）")
    private String taste_status;
    @ApiModelProperty("")
    private String create_time;


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

    public String getTaste_key() {
        return taste_key;
    }

    public void setTaste_key(String taste_key) {
        this.taste_key = taste_key;
    }

    public String getTaste_status() {
        return taste_status;
    }

    public void setTaste_status(String taste_status) {
        this.taste_status = taste_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}
package com.ly.mt.center.data.bluetooth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BluetoothIndexLog {
    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty("")
    private String user_id;
    @ApiModelProperty("mac地址")
    private String mac_address;
    @ApiModelProperty("指标项中文")
    private String index_name;
    @ApiModelProperty("指标项英文")
    private String index_key;
    @ApiModelProperty("指标值")
    private String index_value;
    @ApiModelProperty("指标类型 :1.口感设置 2.灯光设置3.频次设置")
    private String index_type;
    @ApiModelProperty("指标模式  与指标类型一一对应 ")
    private String index_model;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getIndex_name() {
        return index_name;
    }

    public void setIndex_name(String index_name) {
        this.index_name = index_name;
    }

    public String getIndex_key() {
        return index_key;
    }

    public void setIndex_key(String index_key) {
        this.index_key = index_key;
    }

    public String getIndex_value() {
        return index_value;
    }

    public void setIndex_value(String index_value) {
        this.index_value = index_value;
    }

    public String getIndex_type() {
        return index_type;
    }

    public void setIndex_type(String index_type) {
        this.index_type = index_type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIndex_model() {
        return index_model;
    }

    public void setIndex_model(String index_model) {
        this.index_model = index_model;
    }
}
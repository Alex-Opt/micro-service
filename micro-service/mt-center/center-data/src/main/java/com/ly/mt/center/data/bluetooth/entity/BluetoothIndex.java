package com.ly.mt.center.data.bluetooth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BluetoothIndex {
    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty("")
    private String user_id;
    @ApiModelProperty("烟杆mac地址")
    private String mac_address;
    @ApiModelProperty("指标中文")
    private String index_name;
    @ApiModelProperty("指标KEY")
    private String index_key;
    @ApiModelProperty("指标值")
    private String index_value;
    @ApiModelProperty("指标类型 :1.口感设置 2.灯光设置3.频次设置")
    private String index_type;
    @ApiModelProperty("")
    private String create_time;
    @ApiModelProperty("")
    private String modify_time;


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

    public String getIndex_type() {
        return index_type;
    }

    public void setIndex_type(String index_type) {
        this.index_type = index_type;
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
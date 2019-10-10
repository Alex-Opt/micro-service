package com.ly.mt.center.data.bluetooth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BluetoothTasteBest {
    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty("口味key")
    private String taste_key;
    @ApiModelProperty("指标类型 :1.口感设置 2.灯光设置3.频次设置")
    private String index_type;
    @ApiModelProperty("指标名称")
    private String index_name;
    @ApiModelProperty("指标key")
    private String index_key;
    @ApiModelProperty("指标值")
    private String index_value;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String modify_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaste_key() {
        return taste_key;
    }

    public void setTaste_key(String taste_key) {
        this.taste_key = taste_key;
    }

    public String getIndex_type() {
        return index_type;
    }

    public void setIndex_type(String index_type) {
        this.index_type = index_type;
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
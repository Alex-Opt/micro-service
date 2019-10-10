package com.ly.mt.center.data.bluetooth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BluetoothUserBind {
    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("烟杆名称")
    private String name;
    @ApiModelProperty("烟杆mac地址")
    private String mac_address;
    @ApiModelProperty("儿童锁 0：关闭，1：开启")
    private String child_lock;
    @ApiModelProperty("绑定状态 0：绑定，1：解绑")
    private String bind_status;
    @ApiModelProperty("剩余电量")
    private int electric_quantity;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getBind_status() {
        return bind_status;
    }

    public void setBind_status(String bind_status) {
        this.bind_status = bind_status;
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

    public String getChild_lock() {
        return child_lock;
    }

    public void setChild_lock(String child_lock) {
        this.child_lock = child_lock;
    }

    public int getElectric_quantity() {
        return electric_quantity;
    }

    public void setElectric_quantity(int electric_quantity) {
        this.electric_quantity = electric_quantity;
    }
}
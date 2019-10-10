package com.ly.mt.center.data.bluetooth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BluetoothLog {
    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty("用户id")
    private String user_id;
    @ApiModelProperty("mac地址")
    private String mac_address;
    @ApiModelProperty("时间戳")
    private String time_stamp;
    @ApiModelProperty("功率")
    private String power;
    @ApiModelProperty("持续时间(s)")
    private String time;
    @ApiModelProperty("剩余电量/mh")
    private String remaining_battery;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("查询开始时间")
    private String query_start_time;
    @ApiModelProperty("查询结束开始时间")
    private String query_end_time;

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

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemaining_battery() {
        return remaining_battery;
    }

    public void setRemaining_battery(String remaining_battery) {
        this.remaining_battery = remaining_battery;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getQuery_start_time() {
        return query_start_time;
    }

    public void setQuery_start_time(String query_start_time) {
        this.query_start_time = query_start_time;
    }

    public String getQuery_end_time() {
        return query_end_time;
    }

    public void setQuery_end_time(String query_end_time) {
        this.query_end_time = query_end_time;
    }
}
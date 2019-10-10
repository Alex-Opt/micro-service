package com.ly.mt.blue.tooth.tobarod.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户绑定烟杆")
public class BluetoothUserBindVo {
    @ApiModelProperty(value = "烟杆id", required = true)
    private String id;
    @ApiModelProperty(value = "用户Id", required = true)
    private String userId;
    @ApiModelProperty(value = "烟杆名称", required = true)
    private String name;//烟杆名称
    @ApiModelProperty(value = "烟杆mac", required = true)
    private String macAddress;//烟杆mac
    @ApiModelProperty(value = "儿童锁状态", required = true)
    private String childLock;//儿童锁状态
    @ApiModelProperty(value = "电量", required = true)
    private int electricQuantity;//电量
    @ApiModelProperty(value = "烟杆添加时间", required = true)
    private String createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChildLock() {
        return childLock;
    }

    public void setChildLock(String childLock) {
        this.childLock = childLock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getElectricQuantity() {
        return electricQuantity;
    }

    public void setElectricQuantity(int electricQuantity) {
        this.electricQuantity = electricQuantity;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
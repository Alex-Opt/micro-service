package com.ly.mt.blue.tooth.mq.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 蓝牙烟杆传输数据
 */
@ApiModel(value="蓝牙烟杆抽烟数据")
public class BluetoothLogMqVo<BluetoothLogVo> {
    @ApiModelProperty(value = "用户id", required = true)
    private String userId;
    @ApiModelProperty(value = "mac地址", required = true)
    private String macAddress;
    @ApiModelProperty(value = "烟杆剩余电量", required = true)
    private int electricQuantity;
    @ApiModelProperty(value = "抽烟数据集合", required = true)
    private List<BluetoothLogVo> bluetoothLogVoList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public List<BluetoothLogVo> getBluetoothLogVoList() {
        return bluetoothLogVoList;
    }

    public void setBluetoothLogVoList(List<BluetoothLogVo> bluetoothLogVoList) {
        this.bluetoothLogVoList = bluetoothLogVoList;
    }

    public int getElectricQuantity() {
        return electricQuantity;
    }

    public void setElectricQuantity(int electricQuantity) {
        this.electricQuantity = electricQuantity;
    }
}
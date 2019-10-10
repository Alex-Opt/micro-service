package com.ly.mt.blue.tooth.setting.vo;

import com.ly.mt.blue.tooth.taste.vo.BluetoothIndexVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 用户指标设置请求VO
 */
@ApiModel(value="指标设置信息")
public class BluetoothIndexSettingVo {
    @ApiModelProperty(value = "指标类型 1:口感-自定义 2：灯光 3:频次", required = true)
    private String indexType;
    @ApiModelProperty(value = "烟杆Mac", required = true)
    private String macAddress ;
    @ApiModelProperty(value = "指标集合", required = true)
    private List<BluetoothIndexVo> bluetoothIndexVoList;

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public List<BluetoothIndexVo> getBluetoothIndexVoList() {
        return bluetoothIndexVoList;
    }

    public void setBluetoothIndexVoList(List<BluetoothIndexVo> bluetoothIndexVoList) {
        this.bluetoothIndexVoList = bluetoothIndexVoList;
    }
}
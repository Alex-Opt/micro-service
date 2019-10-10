package com.ly.mt.center.data.bluetooth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BluetoothUserSubsidiary {
    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty("用户id")
    private String user_id;
    @ApiModelProperty("戒烟目标(每天不超过)")
    private String smoking_target;
    @ApiModelProperty("达标天数(健康抽烟)")
    private String compliance_days_target;
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

    public String getSmoking_target() {
        return smoking_target;
    }

    public void setSmoking_target(String smoking_target) {
        this.smoking_target = smoking_target;
    }

    public String getCompliance_days_target() {
        return compliance_days_target;
    }

    public void setCompliance_days_target(String compliance_days_target) {
        this.compliance_days_target = compliance_days_target;
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
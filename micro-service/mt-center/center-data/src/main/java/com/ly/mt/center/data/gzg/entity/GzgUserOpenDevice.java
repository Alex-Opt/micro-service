package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("可以直接打开柜门的维护人员")
public class GzgUserOpenDevice {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("状态,（0：正常：1：不正常）")
    private String status;

    @ApiModelProperty("创建时间")
    private String create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}

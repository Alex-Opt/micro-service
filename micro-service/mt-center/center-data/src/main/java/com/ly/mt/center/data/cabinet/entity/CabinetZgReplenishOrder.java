package com.ly.mt.center.data.cabinet.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 格子柜B端补货：展柜补货单订单表
 *
 * @author wanghongliang
 * @date 2019-09-18
 */
public class CabinetZgReplenishOrder {

    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty(value = "推送手机号", required = true)
    private String phone;

    @ApiModelProperty(value = "用户id", required = true)
    private String user_id;

    @ApiModelProperty(value = "展柜编号", required = true)
    private String cabinet_code;

    @ApiModelProperty(value = "店铺id", required = true)
    private String cabinet_store_id;

    @ApiModelProperty(value = "代补数量", required = true)
    private String num;

    @ApiModelProperty(value = "状态 0：待补货  1：已完成", required = true)
    private String status;

    @ApiModelProperty(value = "创建时间", required = true)
    private String create_time;

    @ApiModelProperty(value = "修改时间", required = true)
    private String update_time;

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

    public String getCabinet_code() {
        return cabinet_code;
    }

    public void setCabinet_code(String cabinet_code) {
        this.cabinet_code = cabinet_code;
    }

    public String getCabinet_store_id() {
        return cabinet_store_id;
    }

    public void setCabinet_store_id(String cabinet_store_id) {
        this.cabinet_store_id = cabinet_store_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

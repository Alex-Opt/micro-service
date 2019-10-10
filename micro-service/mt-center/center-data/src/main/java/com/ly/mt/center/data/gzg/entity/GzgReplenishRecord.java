package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgReplenishRecord {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("补货编号")
    private String replenish_code;
    @ApiModelProperty("补货订单ID")
    private String replenish_order_id;
    @ApiModelProperty("补货员ID")
    private String replenish_user_id;
    @ApiModelProperty("补货状态0抢单1商品校验2货柜校验3提交审核4审核通过 5领取奖励 6补货失效")
    private String state;
    @ApiModelProperty("失效类型0商品校验失败1货柜校验失败2审核失败3补货超时4放弃补货")
    private String invalid_type;
    @ApiModelProperty("抢单时间")
    private String grad_time;
    @ApiModelProperty("提交审核时间")
    private String submit_audit_time;
    @ApiModelProperty("到账时间")
    private String arrival_account_time;
    @ApiModelProperty("失效时间")
    private String invalid_time;
    @ApiModelProperty("奖励金额")
    private String amount;
    @ApiModelProperty("审核照片")
    private String audit_picture;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReplenish_code() {
        return replenish_code;
    }

    public void setReplenish_code(String replenish_code) {
        this.replenish_code = replenish_code;
    }

    public String getReplenish_order_id() {
        return replenish_order_id;
    }

    public void setReplenish_order_id(String replenish_order_id) {
        this.replenish_order_id = replenish_order_id;
    }

    public String getReplenish_user_id() {
        return replenish_user_id;
    }

    public void setReplenish_user_id(String replenish_user_id) {
        this.replenish_user_id = replenish_user_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInvalid_type() {
        return invalid_type;
    }

    public void setInvalid_type(String invalid_type) {
        this.invalid_type = invalid_type;
    }

    public String getGrad_time() {
        return grad_time;
    }

    public void setGrad_time(String grad_time) {
        this.grad_time = grad_time;
    }

    public String getSubmit_audit_time() {
        return submit_audit_time;
    }

    public void setSubmit_audit_time(String submit_audit_time) {
        this.submit_audit_time = submit_audit_time;
    }

    public String getArrival_account_time() {
        return arrival_account_time;
    }

    public void setArrival_account_time(String arrival_account_time) {
        this.arrival_account_time = arrival_account_time;
    }

    public String getInvalid_time() {
        return invalid_time;
    }

    public void setInvalid_time(String invalid_time) {
        this.invalid_time = invalid_time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAudit_picture() {
        return audit_picture;
    }

    public void setAudit_picture(String audit_picture) {
        this.audit_picture = audit_picture;
    }

}
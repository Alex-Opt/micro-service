package com.ly.mt.core.data.cabinet.entity;

import java.io.Serializable;

public class CabinetCashRecord implements Serializable {
    private String id;

    private String userId;

    private String openId;

    private String withdrawalAmount;

    private String status;

    private String type;

    private String createTime;
    private String updateTime;
    private String detailId;
    private String remark;
    private String paymentTime;
    private String reason;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(String withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "CabinetInfo{" +
                "id='" + id + '\'' +
                ", user_id='" + userId + '\'' +
                ", withdrawal_amount='" + withdrawalAmount + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", create_time='" + createTime + '\'' +
                ", update_time='" + updateTime + '\'' +
                ", detail_id='" + detailId + '\'' +
                ", remark='" + remark + '\'' +
                ", payment_time='" + paymentTime + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
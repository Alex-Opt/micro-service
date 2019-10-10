package com.ly.mt.center.data.cabinet.entity;

import java.io.Serializable;

public class CabinetCashRecord implements Serializable {
    private String id;

    private String user_id;

    private String open_id;

    private String withdrawal_amount;

    private String status;

    private String type;

    private String create_time;
    private String update_time;
    private String detail_id;
    private String remark;
    private String payment_time;
    private String reason;

    private static final long serialVersionUID = 1L;

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

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getWithdrawal_amount() {
        return withdrawal_amount;
    }

    public void setWithdrawal_amount(String withdrawal_amount) {
        this.withdrawal_amount = withdrawal_amount;
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

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(String payment_time) {
        this.payment_time = payment_time;
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
                ", user_id='" + user_id + '\'' +
                ", withdrawal_amount='" + withdrawal_amount + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", detail_id='" + detail_id + '\'' +
                ", remark='" + remark + '\'' +
                ", payment_time='" + payment_time + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
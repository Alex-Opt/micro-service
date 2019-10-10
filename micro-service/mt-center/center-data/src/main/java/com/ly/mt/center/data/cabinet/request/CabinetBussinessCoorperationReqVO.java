package com.ly.mt.center.data.cabinet.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CabinetBussinessCoorperationReqVO {
    @ApiModelProperty(name = "是否押金")
    private int is_deposit;
    @ApiModelProperty(name = "押金金额")
    private long deposit_amount;
    @ApiModelProperty(name = "店主电话")
    private String owner_phone;
    @ApiModelProperty(name = "店主姓名")
    private String owner_name;
    @ApiModelProperty(name = "bd姓名")
    private String bd_name;
    @ApiModelProperty(name = "bd电话")
    private String bd_phone;
    @ApiModelProperty("合作照片")
    private String coorperation_pic;

    public String getCoorperation_pic() {
        return coorperation_pic;
    }

    public void setCoorperation_pic(String coorperation_pic) {
        this.coorperation_pic = coorperation_pic;
    }

    public int getIs_deposit() {
        return is_deposit;
    }

    public void setIs_deposit(int is_deposit) {
        this.is_deposit = is_deposit;
    }

    public long getDeposit_amount() {
        return deposit_amount;
    }

    public void setDeposit_amount(long deposit_amount) {
        this.deposit_amount = deposit_amount;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getBd_name() {
        return bd_name;
    }

    public void setBd_name(String bd_name) {
        this.bd_name = bd_name;
    }

    public String getBd_phone() {
        return bd_phone;
    }

    public void setBd_phone(String bd_phone) {
        this.bd_phone = bd_phone;
    }
}

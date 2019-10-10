package com.ly.mt.cabinet.b.common.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("展柜合作信息展示类")
public class ShowcaseCoopDefaultRespVo implements Serializable {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("展柜编号")
    private String imei;

    @ApiModelProperty("创建状态")
    private String create_status;

    @ApiModelProperty("合作信息状态")
    private String status;

    @ApiModelProperty("店铺名称")
    private String name;

    @ApiModelProperty("店铺噢地址")
    private String address;

    @ApiModelProperty("详细地址")
    private String detail_address;

    @ApiModelProperty("商圈")
    private String belong_circle;

    @ApiModelProperty("店铺类型")
    private String store_type;

    @ApiModelProperty("预计和流量")
    private String forcast_flow;

    @ApiModelProperty("装修情况")
    private String decorate_type;

    @ApiModelProperty("是否连锁")
    private String is_chain;

    @ApiModelProperty("人均消费")
    private String per_capita_expense;

    @ApiModelProperty("使用面积")
    private String use_area;

    @ApiModelProperty("店主名称")
    private String owner_name;

    @ApiModelProperty("店主手机号")
    private String owner_phone;

    @ApiModelProperty("bd名称")
    private String bd_name;

    @ApiModelProperty("db手机号")
    private String bd_phone;

    @ApiModelProperty("是否押金")
    private String is_deposit;

    @ApiModelProperty("押金金额")
    private double deposit_amount;

    @ApiModelProperty("分成比例")
    private String divide_scale;

    @ApiModelProperty("是否合同")
    private String is_sign_contract;

    @ApiModelProperty("合同生效期")
    private String take_effect_datetime;

    @ApiModelProperty("合同失效期")
    private String lose_efficacy_datetime;


    @ApiModelProperty("合同照片")
    private String contract_pic;

    @ApiModelProperty("合作照片")
    private String coorperation_pic;

    @ApiModelProperty("拜访描述")
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCreate_status() {
        return create_status;
    }

    public void setCreate_status(String create_status) {
        this.create_status = create_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail_address() {
        return detail_address;
    }

    public void setDetail_address(String detail_address) {
        this.detail_address = detail_address;
    }

    public String getBelong_circle() {
        return belong_circle;
    }

    public void setBelong_circle(String belong_circle) {
        this.belong_circle = belong_circle;
    }

    public String getStore_type() {
        return store_type;
    }

    public void setStore_type(String store_type) {
        this.store_type = store_type;
    }

    public String getForcast_flow() {
        return forcast_flow;
    }

    public void setForcast_flow(String forcast_flow) {
        this.forcast_flow = forcast_flow;
    }

    public String getDecorate_type() {
        return decorate_type;
    }

    public void setDecorate_type(String decorate_type) {
        this.decorate_type = decorate_type;
    }

    public String getIs_chain() {
        return is_chain;
    }

    public void setIs_chain(String is_chain) {
        this.is_chain = is_chain;
    }

    public String getPer_capita_expense() {
        return per_capita_expense;
    }

    public void setPer_capita_expense(String per_capita_expense) {
        this.per_capita_expense = per_capita_expense;
    }

    public String getUse_area() {
        return use_area;
    }

    public void setUse_area(String use_area) {
        this.use_area = use_area;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
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

    public String getIs_deposit() {
        return is_deposit;
    }

    public void setIs_deposit(String is_deposit) {
        this.is_deposit = is_deposit;
    }

    public double getDeposit_amount() {
        return deposit_amount;
    }

    public void setDeposit_amount(double deposit_amount) {
        this.deposit_amount = deposit_amount;
    }

    public String getDivide_scale() {
        return divide_scale;
    }

    public void setDivide_scale(String divide_scale) {
        this.divide_scale = divide_scale;
    }

    public String getIs_sign_contract() {
        return is_sign_contract;
    }

    public void setIs_sign_contract(String is_sign_contract) {
        this.is_sign_contract = is_sign_contract;
    }

    public String getTake_effect_datetime() {
        return take_effect_datetime;
    }

    public void setTake_effect_datetime(String take_effect_datetime) {
        this.take_effect_datetime = take_effect_datetime;
    }

    public String getLose_efficacy_datetime() {
        return lose_efficacy_datetime;
    }

    public void setLose_efficacy_datetime(String lose_efficacy_datetime) {
        this.lose_efficacy_datetime = lose_efficacy_datetime;
    }

    public String getContract_pic() {
        return contract_pic;
    }

    public void setContract_pic(String contract_pic) {
        this.contract_pic = contract_pic;
    }

    public String getCoorperation_pic() {
        return coorperation_pic;
    }

    public void setCoorperation_pic(String coorperation_pic) {
        this.coorperation_pic = coorperation_pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

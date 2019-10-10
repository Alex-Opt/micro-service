package com.ly.mt.cabinet.b.common.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ApiModel
public class CoorpReqVo {
    /**
     * 店铺id
     */
    @ApiModelProperty("店铺id")
    private String store_id;
    /**
     * 店铺名称
     */
    @ApiModelProperty("店铺名称")
    private String store_name;
    /**
     * 店铺地址
     */
    @ApiModelProperty("店铺区域地址")
    private String store_address;
    /**
     * 店铺详细地址
     */
    @ApiModelProperty("店铺详细地址")
    private String detail_address;
    /**
     * 所属商圈
     */
    @ApiModelProperty("所属商圈")
    private String belong_circle;
    /**
     * 预计客流量
     */
    @ApiModelProperty("预计客流量")
    private int forecast_flow;
    /**
     * 店铺类型
     */
    @ApiModelProperty("店铺类型")
    private int store_type;
    /**
     * 装修情况
     */
    @ApiModelProperty("装修情况")
    private int descate_type;
    /**
     * 是否连锁
     */
    @ApiModelProperty("是否连锁")
    private int is_chain;
    /**
     * 人均消费
     */
    @ApiModelProperty("人均消费")
    private int per_capita_expense;
    /**
     * 店铺面积
     */
    @ApiModelProperty("店铺面积")
    private int use_area;
    /**
     * 店主姓名
     */
    @ApiModelProperty("店主姓名")
    private String owner_name;
    /**
     * 店主手机号码
     */
    @ApiModelProperty("店主电话")
    @NotNull
    private String owner_phone;
    /**
     * bd姓名
     */
    @ApiModelProperty("bd姓名")
    private String bd_name;
    /**
     * bd手机号码
     */
    @ApiModelProperty("bd电话")
    @NotNull
    private String bd_phone;
    /**
     * 是否押金
     */
    @ApiModelProperty("是否押金")
    private int is_desposit;
    /**
     * 押金金额
     */
    @ApiModelProperty("押金金额")
    private String desposit_amount;
    /**
     * 分成比例
     */
    @ApiModelProperty("分成比例")
    private double divide_scale;
    /**
     * 是否签合同
     */
    @ApiModelProperty("是否签合同")
    private int is_sign_contract;
    /**
     * 合同有效期
     */
    @ApiModelProperty("合同生效时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String take_effect_datetime;
    /**
     * 合同失效期
     */
    @ApiModelProperty("合同失效时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String lose_effect_datetime;
    /**
     * 合同id
     */
    @ApiModelProperty("合同id")
    private String contract_id;
    /**
     * 合同照片
     */
    @ApiModelProperty("合同照片")
    private List<String> contract_pic;
    /**
     * 合作照片
     */
    @ApiModelProperty("合作照片")
    private List<String> coorperation_pic;
    /**
     * 拜访描述
     */
    @ApiModelProperty("描述")
    private String description;
    /**
     * 区域id
     */
    @ApiModelProperty("区域id")
    private long m_id;
    /**
     * 展柜imei
     */
    @ApiModelProperty("展柜imei")
    private String showcase_imei;

    public String getShowcase_imei() {
        return showcase_imei;
    }

    public void setShowcase_imei(String showcase_imei) {
        this.showcase_imei = showcase_imei;
    }

    public List<String> getContract_pic() {
        return contract_pic;
    }

    public void setContract_pic(List<String> contract_pic) {
        this.contract_pic = contract_pic;
    }

    public List<String> getCoorperation_pic() {
        return coorperation_pic;
    }

    public void setCoorperation_pic(List<String> coorperation_pic) {
        this.coorperation_pic = coorperation_pic;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public long getM_id() {
        return m_id;
    }

    public void setM_id(long m_id) {
        this.m_id = m_id;
    }
    public int getStore_type() {
        return store_type;
    }

    public void setStore_type(int store_type) {
        this.store_type = store_type;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
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

    public int getForecast_flow() {
        return forecast_flow;
    }

    public void setForecast_flow(int forecast_flow) {
        this.forecast_flow = forecast_flow;
    }

    public int getDescate_type() {
        return descate_type;
    }

    public void setDescate_type(int descate_type) {
        this.descate_type = descate_type;
    }

    public int getIs_chain() {
        return is_chain;
    }

    public void setIs_chain(int is_chain) {
        this.is_chain = is_chain;
    }

    public int getPer_capita_expense() {
        return per_capita_expense;
    }

    public void setPer_capita_expense(int per_capita_expense) {
        this.per_capita_expense = per_capita_expense;
    }

    public int getUse_area() {
        return use_area;
    }

    public void setUse_area(int use_area) {
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

    public int getIs_desposit() {
        return is_desposit;
    }

    public void setIs_desposit(int is_desposit) {
        this.is_desposit = is_desposit;
    }

    public String getDesposit_amount() {
        return desposit_amount;
    }

    public void setDesposit_amount(String desposit_amount) {
        this.desposit_amount = desposit_amount;
    }

    public double getDivide_scale() {
        return divide_scale;
    }

    public void setDivide_scale(double divide_scale) {
        this.divide_scale = divide_scale;
    }

    public int getIs_sign_contract() {
        return is_sign_contract;
    }

    public void setIs_sign_contract(int is_sign_contract) {
        this.is_sign_contract = is_sign_contract;
    }

    public String getTake_effect_datetime() {
        return take_effect_datetime;
    }

    public void setTake_effect_datetime(String take_effect_datetime) {
        this.take_effect_datetime = take_effect_datetime;
    }

    public String getLose_effect_datetime() {
        return lose_effect_datetime;
    }

    public void setLose_effect_datetime(String lose_effect_datetime) {
        this.lose_effect_datetime = lose_effect_datetime;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.ly.mt.center.data.cabinet.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class CabinetContract implements Serializable {
    private String id;
    /**
     * 合同生效时间
     */
    private String take_effect_datetime;
    /**
     * 合同失效时间
     */
    private String lose_efficacy_datetime;

    /**
     * 合同照片
     */
    private String contract_pic;
    /**
     * 状态
     */
    private String status;
    /**
     * 创建时间
     */
    private String gmt_create;
    /**
     * 修改时间
     */
    private String gmt_modify;
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getGmt_modify() {
        return gmt_modify;
    }

    public void setGmt_modify(String gmt_modify) {
        this.gmt_modify = gmt_modify;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
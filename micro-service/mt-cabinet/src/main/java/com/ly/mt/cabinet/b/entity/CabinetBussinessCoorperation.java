package com.ly.mt.cabinet.b.entity;

import java.io.Serializable;

public class CabinetBussinessCoorperation implements Serializable {
    private String id;

    private String is_deposit;

    private String deposit_amount;

    private String owner_user_id;

    private String owner_phone;

    private String owner_name;

    private String bd_user_id;

    private String bd_phone;

    private String bd_name;

    private String contract_id;

    private String store_id;

    private String coorperation_pic;

    private String status;

    private String divide_scale;

    private String is_sign_contract;

    private String description;

    private String gmt_create;

    private String gmt_modify;

    private static final long serialVersionUID = 1L;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIs_deposit() {
        return is_deposit;
    }

    public void setIs_deposit(String is_deposit) {
        this.is_deposit = is_deposit;
    }

    public String getDeposit_amount() {
        return deposit_amount;
    }

    public void setDeposit_amount(String deposit_amount) {
        this.deposit_amount = deposit_amount;
    }

    public String getOwner_user_id() {
        return owner_user_id;
    }

    public void setOwner_user_id(String owner_user_id) {
        this.owner_user_id = owner_user_id;
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

    public String getBd_user_id() {
        return bd_user_id;
    }

    public void setBd_user_id(String bd_user_id) {
        this.bd_user_id = bd_user_id;
    }

    public String getBd_phone() {
        return bd_phone;
    }

    public void setBd_phone(String bd_phone) {
        this.bd_phone = bd_phone;
    }

    public String getBd_name() {
        return bd_name;
    }

    public void setBd_name(String bd_name) {
        this.bd_name = bd_name;
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

    public String getCoorperation_pic() {
        return coorperation_pic;
    }

    public void setCoorperation_pic(String coorperation_pic) {
        this.coorperation_pic = coorperation_pic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
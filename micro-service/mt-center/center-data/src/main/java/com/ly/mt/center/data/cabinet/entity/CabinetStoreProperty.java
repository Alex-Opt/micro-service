package com.ly.mt.center.data.cabinet.entity;

import java.io.Serializable;
import java.util.Date;

public class CabinetStoreProperty implements Serializable {
    private String id;

    private String store_type;

    private String forcast_flow;

    private String decorate_type;

    private String is_chain;

    private String per_capita_expense;

    private String use_area;

    private String cabinet_store_id;

    private String gmt_create;

    private String gmt_modify;

    private static final long serialVersionUID = 1L;

    public String getUse_area() {
        return use_area;
    }

    public void setUse_area(String use_area) {
        this.use_area = use_area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCabinet_store_id() {
        return cabinet_store_id;
    }

    public void setCabinet_store_id(String cabinet_store_id) {
        this.cabinet_store_id = cabinet_store_id;
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

    @Override
    public String toString() {
        return "CabinetStoreProperty{" +
                "id='" + id + '\'' +
                ", store_type='" + store_type + '\'' +
                ", forcast_flow='" + forcast_flow + '\'' +
                ", decorate_type='" + decorate_type + '\'' +
                ", is_chain='" + is_chain + '\'' +
                ", per_capita_expense='" + per_capita_expense + '\'' +
                ", cabinet_store_id='" + cabinet_store_id + '\'' +
                ", gmt_create='" + gmt_create + '\'' +
                ", gmt_modify='" + gmt_modify + '\'' +
                '}';
    }
}
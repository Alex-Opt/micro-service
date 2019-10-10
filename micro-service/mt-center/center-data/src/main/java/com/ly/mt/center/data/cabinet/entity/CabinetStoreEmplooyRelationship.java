package com.ly.mt.center.data.cabinet.entity;

import java.io.Serializable;
import java.util.Date;

public class CabinetStoreEmplooyRelationship implements Serializable {
    private String id;

    private String emplooy_id;

    private String store_id;

    private String status;

    private String gmt_create;

    private String gmt_modify;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmplooy_id() {
        return emplooy_id;
    }

    public void setEmplooy_id(String emplooy_id) {
        this.emplooy_id = emplooy_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
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

    @Override
    public String toString() {
        return "CabinetStoreEmplooyRelationship{" +
                "id='" + id + '\'' +
                ", emplooy_id='" + emplooy_id + '\'' +
                ", store_id='" + store_id + '\'' +
                ", status='" + status + '\'' +
                ", gmt_create='" + gmt_create + '\'' +
                ", gmt_modify='" + gmt_modify + '\'' +
                '}';
    }
}
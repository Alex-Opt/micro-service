package com.ly.mt.cabinet.b.entity;

import java.io.Serializable;

public class CabinetCreateRecord implements Serializable {
    private String id;

    private String cabinet_info_id;

    private String createor_phone;

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

    public String getCabinet_info_id() {
        return cabinet_info_id;
    }

    public void setCabinet_info_id(String cabinet_info_id) {
        this.cabinet_info_id = cabinet_info_id;
    }

    public String getCreateor_phone() {
        return createor_phone;
    }

    public void setCreateor_phone(String createor_phone) {
        this.createor_phone = createor_phone;
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
        return "CabinetCreateRecord{" +
                "id='" + id + '\'' +
                ", cabinet_info_id='" + cabinet_info_id + '\'' +
                ", createor_phone='" + createor_phone + '\'' +
                ", status='" + status + '\'' +
                ", gmt_create='" + gmt_create + '\'' +
                ", gmt_modify='" + gmt_modify + '\'' +
                '}';
    }
}
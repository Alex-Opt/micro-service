package com.ly.mt.cabinet.b.common.request;

import com.ly.mt.cabinet.b.common.dict.CabinetStoreStatusEnum;

public class CabStoreReqVo {
    private String id;

    private String name;

    private String address;

    private String detail_address;

    private String belong_circle;

    private String create_status;
    private long m_id;
    private String create_type;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreate_status() {
        return create_status;
    }

    public void setCreate_status(String create_status) {
        this.create_status = create_status;
    }

    public long getM_id() {
        return m_id;
    }

    public void setM_id(long m_id) {
        this.m_id = m_id;
    }

    public String getCreate_type() {
        return create_type;
    }

    public void setCreate_type(String create_type) {
        this.create_type = create_type;
    }
}

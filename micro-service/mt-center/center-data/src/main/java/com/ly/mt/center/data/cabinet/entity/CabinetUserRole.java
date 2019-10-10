package com.ly.mt.center.data.cabinet.entity;

import java.io.Serializable;
import java.util.Date;

public class CabinetUserRole implements Serializable {
    private String id;

    private String user_id;

    private String role_id;

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
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
        return "CabinetUserRole{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", role_id='" + role_id + '\'' +
                ", status='" + status + '\'' +
                ", gmt_create='" + gmt_create + '\'' +
                ", gmt_modify='" + gmt_modify + '\'' +
                '}';
    }
}
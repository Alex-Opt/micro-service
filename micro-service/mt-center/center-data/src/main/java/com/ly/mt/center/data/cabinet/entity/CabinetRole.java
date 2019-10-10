package com.ly.mt.center.data.cabinet.entity;

import java.io.Serializable;
import java.util.Date;

public class CabinetRole implements Serializable {
    private String id;

    private String role_name;

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

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
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

    @Override
    public String toString() {
        return "CabinetRole{" +
                "id='" + id + '\'' +
                ", role_name='" + role_name + '\'' +
                ", description='" + description + '\'' +
                ", gmt_create='" + gmt_create + '\'' +
                ", gmt_modify='" + gmt_modify + '\'' +
                '}';
    }
}
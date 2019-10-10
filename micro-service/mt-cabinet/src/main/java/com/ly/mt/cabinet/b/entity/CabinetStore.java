package com.ly.mt.cabinet.b.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class CabinetStore {

    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty("店铺名称")
    private String name;

    @ApiModelProperty("店铺地址")
    private String address;

    @ApiModelProperty("区域id")
    private long m_id;

    @ApiModelProperty("详细地址")
    private String detail_address;

    @ApiModelProperty("所属商圈")
    private String belong_circle;

    @ApiModelProperty("0:待创建  1:已创建  2:已使用")
    private String create_status;

    @ApiModelProperty("0:正常  1:失效")
    private String status;

    @ApiModelProperty("创建时间")
    private String gmt_create;

    @ApiModelProperty("修改时间")
    private String gmt_modify;

    public long getM_id() {
        return m_id;
    }

    public void setM_id(long m_id) {
        this.m_id = m_id;
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

    public CabinetStore() {
    }

    public CabinetStore(String id, String name, String address, String detail_address, String belong_circle, String create_status, String status, String gmt_create, String gmt_modify) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.detail_address = detail_address;
        this.belong_circle = belong_circle;
        this.create_status = create_status;
        this.status = status;
        this.gmt_create = gmt_create;
        this.gmt_modify = gmt_modify;
    }

    @Override
    public String toString() {
        return "CabinetStore{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", detail_address='" + detail_address + '\'' +
                ", belong_circle='" + belong_circle + '\'' +
                ", create_status='" + create_status + '\'' +
                ", status='" + status + '\'' +
                ", gmt_create='" + gmt_create + '\'' +
                ", gmt_modify='" + gmt_modify + '\'' +
                '}';
    }
}






package com.ly.mt.cabinet.b.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel
public class CabinetInfo implements Serializable {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty("店铺名称")
    private String imei;

    @ApiModelProperty("店铺id")
    private String store_id;

    @ApiModelProperty("方案名称")
    private String programme_name;

    @ApiModelProperty("创建状态 0:待使用  1:已保存  2:已上线")
    private String create_status;

    @ApiModelProperty("0:上架  1:下架")
    private String status;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("创建人")
    private String creator;

    @ApiModelProperty("创建人手机号")
    private String creator_phone;

    @ApiModelProperty("创建时间")
    private String gmt_create;

    @ApiModelProperty("修改时间")
    private String gmt_modify;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator_phone() {
        return creator_phone;
    }

    public void setCreator_phone(String creator_phone) {
        this.creator_phone = creator_phone;
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

    public String getProgramme_name() {
        return programme_name;
    }

    public void setProgramme_name(String programme_name) {
        this.programme_name = programme_name;
    }


}
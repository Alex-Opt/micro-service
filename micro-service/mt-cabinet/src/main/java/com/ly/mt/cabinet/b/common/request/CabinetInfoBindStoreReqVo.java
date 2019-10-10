package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CabinetInfoBindStoreReqVo {

    @ApiModelProperty(value = "格子柜唯一标识符",required = true,example = "2")
    private String imei;

    @ApiModelProperty(value = "店铺的Id",required = true,example = "865800042529062")
    private String store_id;

    @ApiModelProperty(value = "配货方案名称",required = true,example = "RJ_A")
    private String programme;

    @ApiModelProperty(value = "仓库姓名",required = true,example = "南霸天")
    private String creator;

    @ApiModelProperty(value = "手机号码",required = true,example = "13333333333")
    private String phone;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "CabinetInfoBindStoreVo{" +
                "imei='" + imei + '\'' +
                ", store_id='" + store_id + '\'' +
                ", programme='" + programme + '\'' +
                ", creator='" + creator + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}






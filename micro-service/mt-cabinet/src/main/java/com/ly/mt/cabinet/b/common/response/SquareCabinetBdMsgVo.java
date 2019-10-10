package com.ly.mt.cabinet.b.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 格子柜展示带bd等信息的vo展示类
 */
@ApiModel("格子柜管理查询对象")
public class SquareCabinetBdMsgVo {

    /**
     * 主键id
     */
    @ApiModelProperty("格子柜主键id")
    private String id;

    /**
     * 格子柜imei编码
     */
    @ApiModelProperty("格子柜id imei码")
    private String imei;

    /**
     * 创建状态 0: 上线  1:下架  2:保存
     */
    @ApiModelProperty("创建状态  0: 上线  1:下架  2:保存")
    private int create_status;

    /**
     * 0:上架  1:下架
     */
    @ApiModelProperty("格子柜状态 0上架 1下架")
    private int status;


    /**
     * 类型，展柜或者格子柜
     */
    @ApiModelProperty("格子柜还是展柜")
    private int type;

    /**
     * 店主名称
     */
    @ApiModelProperty("店主名称")
    private String owner_name;

    /**
     * 详细地址 店铺地址
     */
    @ApiModelProperty("详细地址")
    private String store_address;

    /**
     * 店铺名称
     */
    @ApiModelProperty("店铺名称")
    private String store_name;

    /**
     * 最后操作时间，不管上线还是下线
     */
    @ApiModelProperty("上线时间")
    private String last_time;

    @ApiModelProperty("店主手机")
    private String owner_phone;

    @ApiModelProperty("店铺id")
    private String store_id;

    public String getLast_time() {
        return last_time;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }

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

    public int getCreate_status() {
        return create_status;
    }

    public void setCreate_status(int create_status) {
        this.create_status = create_status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

}

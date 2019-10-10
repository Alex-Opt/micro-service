package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgReplenishOrder {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("格子柜订单ID")
    private String gzg_order_id;
    @ApiModelProperty("格子柜子单id")
    private String gzg_order_itme_id;
    @ApiModelProperty("酒店ID")
    private String hotel_id;
    @ApiModelProperty("格子柜ID")
    private String gzg_id;
    @ApiModelProperty("商品所在格子的号码")
    private String cabinet_no;
    @ApiModelProperty("补货订单状态（0待补货1锁定2完成）")
    private String state;
    @ApiModelProperty("补货订单创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGzg_order_id() {
        return gzg_order_id;
    }

    public void setGzg_order_id(String gzg_order_id) {
        this.gzg_order_id = gzg_order_id;
    }

    public String getGzg_order_itme_id() {
        return gzg_order_itme_id;
    }

    public void setGzg_order_itme_id(String gzg_order_itme_id) {
        this.gzg_order_itme_id = gzg_order_itme_id;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getGzg_id() {
        return gzg_id;
    }

    public void setGzg_id(String gzg_id) {
        this.gzg_id = gzg_id;
    }

    public String getCabinet_no() {
        return cabinet_no;
    }

    public void setCabinet_no(String cabinet_no) {
        this.cabinet_no = cabinet_no;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}
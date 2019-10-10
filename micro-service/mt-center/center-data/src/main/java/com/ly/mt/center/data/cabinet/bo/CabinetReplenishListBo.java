package com.ly.mt.center.data.cabinet.bo;

import java.io.Serializable;

/**
 * 商品名称信息
 */
public class CabinetReplenishListBo implements Serializable {
    /**
     * 补货订单id
     */
    private String id;
    /**
     * skuId
     */
    private String sku_id;
    /**
     * 订单id
     */
    private String order_id;
    /**
     * 订单明细id
     */
    private String order_item_id;
    /**
     * 订单完成时间
     */
    private String order_finish_time;

    //格子柜图片
    private String big_pic_url;

    //补货订单状态
    private String status;

    //格子柜编号
    private String cabinet_code;

    //格子柜舱门
    private String cabinet_no;

    //店铺名称
    private String shop_name;

    //店铺地址
    private String address;

    //店铺详细地址
    private String detail_address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(String order_item_id) {
        this.order_item_id = order_item_id;
    }

    public String getOrder_finish_time() {
        return order_finish_time;
    }

    public void setOrder_finish_time(String order_finish_time) {
        this.order_finish_time = order_finish_time;
    }

    public String getBig_pic_url() {
        return big_pic_url;
    }

    public void setBig_pic_url(String big_pic_url) {
        this.big_pic_url = big_pic_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCabinet_code() {
        return cabinet_code;
    }

    public void setCabinet_code(String cabinet_code) {
        this.cabinet_code = cabinet_code;
    }

    public String getCabinet_no() {
        return cabinet_no;
    }

    public void setCabinet_no(String cabinet_no) {
        this.cabinet_no = cabinet_no;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
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
}
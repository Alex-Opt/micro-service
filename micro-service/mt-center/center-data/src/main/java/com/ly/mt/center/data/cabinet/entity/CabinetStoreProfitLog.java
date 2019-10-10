package com.ly.mt.center.data.cabinet.entity;

import java.io.Serializable;
import java.util.Date;

public class CabinetStoreProfitLog implements Serializable {
    private String id;

    private String store_id;

    /**
     * 订单总金额
     */
    private String order_amount;

    /**
     * 分成的金额
     */
    private String profit_amount;

    private String imei;

    private String gzg_order_id;

    private String create_time;

    private String update_time;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id == null ? null : store_id.trim();
    }

    public String getGzg_order_id() {
        return gzg_order_id;
    }

    public void setGzg_order_id(String gzg_order_id) {
        this.gzg_order_id = gzg_order_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getProfit_amount() {
        return profit_amount;
    }

    public void setProfit_amount(String profit_amount) {
        this.profit_amount = profit_amount;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
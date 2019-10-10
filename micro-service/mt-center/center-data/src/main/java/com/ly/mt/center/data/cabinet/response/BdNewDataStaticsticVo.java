package com.ly.mt.center.data.cabinet.response;

public class BdNewDataStaticsticVo {

    private String store_id;

    private String store_name;

    private String order_num;

    private String order_amount;

    private String profit_amount;

    /**
     * 库存/补货
     */
    private String stock_num_replenish;

    public BdNewDataStaticsticVo() {
    }

    public BdNewDataStaticsticVo(BdDataStaticsticVo vo) {
        if (vo == null){
            return;
        }
        this.store_id = vo.getStore_id();
        this.store_name = vo.getStore_name();
        this.order_num = vo.getOrder_num();
        this.order_amount = vo.getOrder_amount();
        this.profit_amount = vo.getProfit_amount();
        this.stock_num_replenish = vo.getStock_num()+"/"+vo.getReplenish();
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
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

    public String getStock_num_replenish() {
        return stock_num_replenish;
    }

    public void setStock_num_replenish(String stock_num_replenish) {
        this.stock_num_replenish = stock_num_replenish;
    }
}

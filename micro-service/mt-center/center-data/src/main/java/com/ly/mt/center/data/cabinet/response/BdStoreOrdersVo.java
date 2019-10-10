package com.ly.mt.center.data.cabinet.response;

public class BdStoreOrdersVo<T> {

    private PageInfoResponseVo<T> orders;

    private BdNewDataStaticsticVo statictisc;

    public PageInfoResponseVo<T> getOrders() {
        return orders;
    }

    public void setOrders(PageInfoResponseVo<T> orders) {
        this.orders = orders;
    }

    public BdNewDataStaticsticVo getStatictisc() {
        return statictisc;
    }

    public void setStatictisc(BdNewDataStaticsticVo statictisc) {
        this.statictisc = statictisc;
    }

    public BdStoreOrdersVo() {
    }

    public BdStoreOrdersVo(PageInfoResponseVo<T> orders, BdNewDataStaticsticVo statictisc) {
        this.orders = orders;
        this.statictisc = statictisc;
    }
}

package com.ly.mt.cabinet.b.common.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("数据统计-店铺详情最后")
public class BdStoreOrdersRespVo<T> {

    @ApiModelProperty("订单集合")
    private PageInfoResponseVo<T> orders;

    @ApiModelProperty("当前店铺详情")
    private BdNewDataStaticsticRespVo statictisc;

    public PageInfoResponseVo<T> getOrders() {
        return orders;
    }

    public void setOrders(PageInfoResponseVo<T> orders) {
        this.orders = orders;
    }

    public BdNewDataStaticsticRespVo getStatictisc() {
        return statictisc;
    }

    public void setStatictisc(BdNewDataStaticsticRespVo statictisc) {
        this.statictisc = statictisc;
    }
}

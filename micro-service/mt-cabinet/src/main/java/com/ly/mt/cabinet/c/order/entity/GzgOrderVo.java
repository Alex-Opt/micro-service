package com.ly.mt.cabinet.c.order.entity;


import com.ly.mt.cabinet.c.entity.GzgOrderItemVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author gongj
 * @String 2019/5/21
 */
@ApiModel
public class GzgOrderVo {
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("买家id")
    private String buyer_id;
    @ApiModelProperty("商品原始价格")
    private String order_old_money;
    @ApiModelProperty("商品优惠价格")
    private String order_discount_money;
    @ApiModelProperty("商品优惠后价格")
    private String order_money;
    @ApiModelProperty("订单创建时间")
    private String create_time;
    @ApiModelProperty("订单状态（0:待支付,1:已完成,2:超时,3:退款中,4:已退款）")
    private String order_status;

    List<GzgOrderItemVo> gzgOrderItemVoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getOrder_old_money() {
        return order_old_money;
    }

    public void setOrder_old_money(String order_old_money) {
        this.order_old_money = order_old_money;
    }

    public String getOrder_discount_money() {
        return order_discount_money;
    }

    public void setOrder_discount_money(String order_discount_money) {
        this.order_discount_money = order_discount_money;
    }

    public String getOrder_money() {
        return order_money;
    }

    public void setOrder_money(String order_money) {
        this.order_money = order_money;
    }

    public List<GzgOrderItemVo> getGzgOrderItemVoList() {
        return gzgOrderItemVoList;
    }

    public void setGzgOrderItemVoList(List<GzgOrderItemVo> gzgOrderItemVoList) {
        this.gzgOrderItemVoList = gzgOrderItemVoList;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }


    @Override
    public String toString() {
        return "GzgOrderVo{" +
                "id='" + id + '\'' +
                ", buyer_id='" + buyer_id + '\'' +
                ", order_old_money='" + order_old_money + '\'' +
                ", order_discount_money='" + order_discount_money + '\'' +
                ", order_money='" + order_money + '\'' +
                ", create_time='" + create_time + '\'' +
                ", order_status='" + order_status + '\'' +
                ", gzgOrderItemVoList=" + gzgOrderItemVoList +
                '}';
    }
}
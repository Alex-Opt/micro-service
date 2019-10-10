package com.ly.mt.center.data.trade.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TradeOrders {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("订单号")
    private String order_no;
    @ApiModelProperty("买家Id")
    private String buyer_id;
    @ApiModelProperty("买家留言")
    private String buyer_memo;
    @ApiModelProperty("店铺Id")
    private String shop_id;
    @ApiModelProperty("管易仓库code")
    private String gy_warehouse_code;
    @ApiModelProperty("管易店铺code")
    private String gy_shop_code;
    @ApiModelProperty("卖家Id")
    private String seller_id;
    @ApiModelProperty("订单原始金额")
    private String order_old_money;
    @ApiModelProperty("订单实付金额")
    private String order_money;
    @ApiModelProperty("订单优惠金额")
    private String order_discount_money;
    @ApiModelProperty("配送方式Id")
    private String distribution_id;
    @ApiModelProperty("")
    private String logistics_code;
    @ApiModelProperty("暂存快递单号")
    private String gy_logistics_code;
    @ApiModelProperty("运费")
    private String freight;
    @ApiModelProperty("订单状态（10:待付款 20:待配送 25:部分发货 30:待收货 99:完成）")
    private String order_status;
    @ApiModelProperty("订单来源 1：H5，2：小程序，3：APP，4：专属小店 (当来源是4时，shop_id 不能为空)，1000以上：活动页下单来源")
    private String order_source;
    @ApiModelProperty("订单取消时间")
    private String cancle_time;
    @ApiModelProperty("取消订单(1:否，2:是)")
    private String order_yn;
    @ApiModelProperty("是否退货（1：否，2：退货审核，3：退货完成）")
    private String is_refund;
    @ApiModelProperty("申请退货时间")
    private String refund_time;
    @ApiModelProperty("支付方式（1 网银，2 微信，3 支付宝，4 其他，5 信用，6 其他，7 线下方式）")
    private String payment_type;
    @ApiModelProperty("订单推送的状态 1-未推送 2-已推送成功 3-推送失败")
    private String push_status;
    @ApiModelProperty("订单类型 1-普通，2-专属 ，3-活动")
    private String order_type;
    @ApiModelProperty("用户地址Id")
    private String address_id;
    @ApiModelProperty("自动收货时间")
    private String auto_receive_time;
    @ApiModelProperty("自动取消时间")
    private String auto_cancel_time;
    @ApiModelProperty("订单快照文件在OSS上保存的Key")
    private String order_snapshoot_key;
    @ApiModelProperty("订单锁定(1:无,2:是)")
    private String locked;
    @ApiModelProperty("锁定时间")
    private String lock_time;
    @ApiModelProperty("订单预计发货时间")
    private String delivery_time;
    @ApiModelProperty("下单时间")
    private String create_time;
    @ApiModelProperty("订单支付时间")
    private String pay_time;
    @ApiModelProperty("订单完成时间")
    private String order_finish_time;
    @ApiModelProperty("备注")
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBuyer_memo() {
        return buyer_memo;
    }

    public void setBuyer_memo(String buyer_memo) {
        this.buyer_memo = buyer_memo;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getGy_warehouse_code() {
        return gy_warehouse_code;
    }

    public void setGy_warehouse_code(String gy_warehouse_code) {
        this.gy_warehouse_code = gy_warehouse_code;
    }

    public String getGy_shop_code() {
        return gy_shop_code;
    }

    public void setGy_shop_code(String gy_shop_code) {
        this.gy_shop_code = gy_shop_code;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getOrder_old_money() {
        return order_old_money;
    }

    public void setOrder_old_money(String order_old_money) {
        this.order_old_money = order_old_money;
    }

    public String getOrder_money() {
        return order_money;
    }

    public void setOrder_money(String order_money) {
        this.order_money = order_money;
    }

    public String getOrder_discount_money() {
        return order_discount_money;
    }

    public void setOrder_discount_money(String order_discount_money) {
        this.order_discount_money = order_discount_money;
    }

    public String getDistribution_id() {
        return distribution_id;
    }

    public void setDistribution_id(String distribution_id) {
        this.distribution_id = distribution_id;
    }

    public String getLogistics_code() {
        return logistics_code;
    }

    public void setLogistics_code(String logistics_code) {
        this.logistics_code = logistics_code;
    }

    public String getGy_logistics_code() {
        return gy_logistics_code;
    }

    public void setGy_logistics_code(String gy_logistics_code) {
        this.gy_logistics_code = gy_logistics_code;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_source() {
        return order_source;
    }

    public void setOrder_source(String order_source) {
        this.order_source = order_source;
    }

    public String getCancle_time() {
        return cancle_time;
    }

    public void setCancle_time(String cancle_time) {
        this.cancle_time = cancle_time;
    }

    public String getOrder_yn() {
        return order_yn;
    }

    public void setOrder_yn(String order_yn) {
        this.order_yn = order_yn;
    }

    public String getIs_refund() {
        return is_refund;
    }

    public void setIs_refund(String is_refund) {
        this.is_refund = is_refund;
    }

    public String getRefund_time() {
        return refund_time;
    }

    public void setRefund_time(String refund_time) {
        this.refund_time = refund_time;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPush_status() {
        return push_status;
    }

    public void setPush_status(String push_status) {
        this.push_status = push_status;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getAuto_receive_time() {
        return auto_receive_time;
    }

    public void setAuto_receive_time(String auto_receive_time) {
        this.auto_receive_time = auto_receive_time;
    }

    public String getAuto_cancel_time() {
        return auto_cancel_time;
    }

    public void setAuto_cancel_time(String auto_cancel_time) {
        this.auto_cancel_time = auto_cancel_time;
    }

    public String getOrder_snapshoot_key() {
        return order_snapshoot_key;
    }

    public void setOrder_snapshoot_key(String order_snapshoot_key) {
        this.order_snapshoot_key = order_snapshoot_key;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getLock_time() {
        return lock_time;
    }

    public void setLock_time(String lock_time) {
        this.lock_time = lock_time;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getOrder_finish_time() {
        return order_finish_time;
    }

    public void setOrder_finish_time(String order_finish_time) {
        this.order_finish_time = order_finish_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
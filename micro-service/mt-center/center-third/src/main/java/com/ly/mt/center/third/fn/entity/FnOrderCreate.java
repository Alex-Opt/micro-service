package com.ly.mt.center.third.fn.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("蜂鸟订单推送实体")
public class FnOrderCreate {
    @ApiModelProperty(value = "订单id", required = true)
    private String order_id;
    @ApiModelProperty(value = "商户备注信息", hidden = true)
    private String partner_remark;
    @ApiModelProperty(value = "订单号", hidden = true, required = true)
    private String partner_order_code;
    @ApiModelProperty(value = "回调地址", hidden = true, required = true)
    private String notify_url;
    @ApiModelProperty(value = "订单类型", hidden = true, required = true)
    private String order_type;
    @ApiModelProperty(value = "门店编号", hidden = true, required = true)
    private String chain_store_code;
    @ApiModelProperty(value = "下单时间(毫秒", hidden = true)
    private String order_add_time;
    @ApiModelProperty(value = "订单总金额", hidden = true, required = true)
    private String order_total_amount;
    @ApiModelProperty(value = "客户需要支付的金额", hidden = true, required = true)
    private String order_actual_amount;
    @ApiModelProperty(value = "订单总重量", hidden = true)
    private String order_weight;
    @ApiModelProperty(value = "用户备注", hidden = true)
    private String order_remark;
    @ApiModelProperty(value = "是否需要发票", hidden = true, required = true)
    private String is_invoiced;
    @ApiModelProperty(value = "发票抬头,如果需要发票此项必填", hidden = true)
    private String invoice;
    @ApiModelProperty(value = "订单支付状态", hidden = true, required = true)
    private String order_payment_status;
    @ApiModelProperty(value = "订单支付方式", hidden = true, required = true)
    private String order_payment_method;
    @ApiModelProperty(value = "是否需要ele代收", hidden = true, required = true)
    private String is_agent_payment;
    @ApiModelProperty(value = "需要代收时客户应付金额,如果需要ele代收此项必填", hidden = true)
    private String require_payment_pay;
    @ApiModelProperty(value = "订单货物件数", hidden = true, required = true)
    private String goods_count;
    @ApiModelProperty(value = "需要送达时间", hidden = true)
    private String require_receive_time;
    @ApiModelProperty(value = "商家订单流水号", hidden = true)
    private String serial_number;
    @ApiModelProperty(value = "商户订单来源", hidden = true)
    private String order_source;
    @ApiModelProperty(value = "商户渠道订单号", hidden = true)
    private String channel_order_code;
    @ApiModelProperty(value = "出餐时间（毫秒)", hidden = true)
    private String cooking_time;
    @ApiModelProperty(value = "订单创建时间（毫秒)", hidden = true)
    private String platform_created_time;
    @ApiModelProperty(value = "订单支付时间（毫秒)", hidden = true)
    private String platform_paid_time;
    @ApiModelProperty(value = "收货人信息", hidden = true, required = true)
    private FnOrderCreateReceiverInfo receiver_info;
    @ApiModelProperty(value = "发货人信息", hidden = true, required = true)
    private FnOrderCreateTransportInfo transport_info;
    @ApiModelProperty(value = "货品信息", hidden = true, required = true)
    private List<FnOrderCreateItem> items_json;


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPartner_remark() {
        return partner_remark;
    }

    public void setPartner_remark(String partner_remark) {
        this.partner_remark = partner_remark;
    }

    public String getPartner_order_code() {
        return partner_order_code;
    }

    public void setPartner_order_code(String partner_order_code) {
        this.partner_order_code = partner_order_code;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getChain_store_code() {
        return chain_store_code;
    }

    public void setChain_store_code(String chain_store_code) {
        this.chain_store_code = chain_store_code;
    }

    public String getOrder_add_time() {
        return order_add_time;
    }

    public void setOrder_add_time(String order_add_time) {
        this.order_add_time = order_add_time;
    }

    public String getOrder_total_amount() {
        return order_total_amount;
    }

    public void setOrder_total_amount(String order_total_amount) {
        this.order_total_amount = order_total_amount;
    }

    public String getOrder_actual_amount() {
        return order_actual_amount;
    }

    public void setOrder_actual_amount(String order_actual_amount) {
        this.order_actual_amount = order_actual_amount;
    }

    public String getOrder_weight() {
        return order_weight;
    }

    public void setOrder_weight(String order_weight) {
        this.order_weight = order_weight;
    }

    public String getOrder_remark() {
        return order_remark;
    }

    public void setOrder_remark(String order_remark) {
        this.order_remark = order_remark;
    }

    public String getIs_invoiced() {
        return is_invoiced;
    }

    public void setIs_invoiced(String is_invoiced) {
        this.is_invoiced = is_invoiced;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getOrder_payment_status() {
        return order_payment_status;
    }

    public void setOrder_payment_status(String order_payment_status) {
        this.order_payment_status = order_payment_status;
    }

    public String getOrder_payment_method() {
        return order_payment_method;
    }

    public void setOrder_payment_method(String order_payment_method) {
        this.order_payment_method = order_payment_method;
    }

    public String getIs_agent_payment() {
        return is_agent_payment;
    }

    public void setIs_agent_payment(String is_agent_payment) {
        this.is_agent_payment = is_agent_payment;
    }

    public String getRequire_payment_pay() {
        return require_payment_pay;
    }

    public void setRequire_payment_pay(String require_payment_pay) {
        this.require_payment_pay = require_payment_pay;
    }

    public String getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(String goods_count) {
        this.goods_count = goods_count;
    }

    public String getRequire_receive_time() {
        return require_receive_time;
    }

    public void setRequire_receive_time(String require_receive_time) {
        this.require_receive_time = require_receive_time;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getOrder_source() {
        return order_source;
    }

    public void setOrder_source(String order_source) {
        this.order_source = order_source;
    }

    public String getChannel_order_code() {
        return channel_order_code;
    }

    public void setChannel_order_code(String channel_order_code) {
        this.channel_order_code = channel_order_code;
    }

    public String getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(String cooking_time) {
        this.cooking_time = cooking_time;
    }

    public String getPlatform_created_time() {
        return platform_created_time;
    }

    public void setPlatform_created_time(String platform_created_time) {
        this.platform_created_time = platform_created_time;
    }

    public String getPlatform_paid_time() {
        return platform_paid_time;
    }

    public void setPlatform_paid_time(String platform_paid_time) {
        this.platform_paid_time = platform_paid_time;
    }

    public FnOrderCreateReceiverInfo getReceiver_info() {
        return receiver_info;
    }

    public void setReceiver_info(FnOrderCreateReceiverInfo receiver_info) {
        this.receiver_info = receiver_info;
    }

    public FnOrderCreateTransportInfo getTransport_info() {
        return transport_info;
    }

    public void setTransport_info(FnOrderCreateTransportInfo transport_info) {
        this.transport_info = transport_info;
    }

    public List<FnOrderCreateItem> getItems_json() {
        return items_json;
    }

    public void setItems_json(List<FnOrderCreateItem> items_json) {
        this.items_json = items_json;
    }
}
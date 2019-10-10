package com.ly.mt.center.third.fn.entity;

/**
 * @Description 蜂鸟订单推送实体-货品信息
 * @Author taoye
 */
public class FnOrderCreateItem {
    // 商品编号-非必填
    private String item_id;
    // 商品名称(不超过128个字符)
    private String item_name;
    // 商品数量
    private String item_quantity;
    // 商品原价
    private String item_price;
    // 商品实际支付金额
    private String item_actual_price;
    // 商品尺寸-非必填
    private String item_size;
    // 商品备注(不超过255个字符)-非必填
    private String item_remark;
    // 是否需要ele打包 0:否 1:是
    private String is_need_package;
    // 是否代购 0:否
    private String is_agent_purchase;
    // 代购进价, 如果需要代购 此项必填
    private String agent_purchase_price;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity = item_quantity;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_actual_price() {
        return item_actual_price;
    }

    public void setItem_actual_price(String item_actual_price) {
        this.item_actual_price = item_actual_price;
    }

    public String getItem_size() {
        return item_size;
    }

    public void setItem_size(String item_size) {
        this.item_size = item_size;
    }

    public String getItem_remark() {
        return item_remark;
    }

    public void setItem_remark(String item_remark) {
        this.item_remark = item_remark;
    }

    public String getIs_need_package() {
        return is_need_package;
    }

    public void setIs_need_package(String is_need_package) {
        this.is_need_package = is_need_package;
    }

    public String getIs_agent_purchase() {
        return is_agent_purchase;
    }

    public void setIs_agent_purchase(String is_agent_purchase) {
        this.is_agent_purchase = is_agent_purchase;
    }

    public String getAgent_purchase_price() {
        return agent_purchase_price;
    }

    public void setAgent_purchase_price(String agent_purchase_price) {
        this.agent_purchase_price = agent_purchase_price;
    }
}
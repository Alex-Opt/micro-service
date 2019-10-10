package com.ly.mt.center.data.battle.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrdersBattleInfo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("订单id")
    private String order_id;
    @ApiModelProperty("店铺Id")
    private String shop_id;
    @ApiModelProperty("抢单用户编号")
    private String user_id;
    @ApiModelProperty("买家编号")
    private String buyer_id;
    @ApiModelProperty("骑手名称")
    private String deliveryman_name;
    @ApiModelProperty("骑手手机号码")
    private String deliveryman_phone;
    @ApiModelProperty("订单类型 1-普通，2-专属 ，3-活动")
    private String type;
    @ApiModelProperty("1-未抢单 3-已被抢单 5-商家已商品校验 7-订单已推送到蜂鸟系统 9-系统拒单/配送异常 11-蜂鸟系统已接单 13-已分配骑手 15-骑手已到店 17-配送中 19-已送达 21-商品签收完成 23-抢单已取消 25-商品被拒签 27-商品申请退货中 29-商品退货退款完成")
    private String status;
    @ApiModelProperty("抢单次数")
    private String grabed_num;
    @ApiModelProperty("抢单时间")
    private String grabed_at;
    @ApiModelProperty("校验截止时间")
    private String checked_at;
    @ApiModelProperty("接单时间")
    private String taked_at;
    @ApiModelProperty("预计取货时间")
    private String estimate_sended_at;
    @ApiModelProperty("发货方式")
    private String send_model;
    @ApiModelProperty("发货公司")
    private String send_com;
    @ApiModelProperty("发货号")
    private String send_no;
    @ApiModelProperty("发货时间")
    private String sended_at;
    @ApiModelProperty("预计完成时间")
    private String estimated_successed_at;
    @ApiModelProperty("完成时间")
    private String successed_at;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String modify_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getDeliveryman_name() {
        return deliveryman_name;
    }

    public void setDeliveryman_name(String deliveryman_name) {
        this.deliveryman_name = deliveryman_name;
    }

    public String getDeliveryman_phone() {
        return deliveryman_phone;
    }

    public void setDeliveryman_phone(String deliveryman_phone) {
        this.deliveryman_phone = deliveryman_phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrabed_num() {
        return grabed_num;
    }

    public void setGrabed_num(String grabed_num) {
        this.grabed_num = grabed_num;
    }

    public String getGrabed_at() {
        return grabed_at;
    }

    public void setGrabed_at(String grabed_at) {
        this.grabed_at = grabed_at;
    }

    public String getChecked_at() {
        return checked_at;
    }

    public void setChecked_at(String checked_at) {
        this.checked_at = checked_at;
    }

    public String getTaked_at() {
        return taked_at;
    }

    public void setTaked_at(String taked_at) {
        this.taked_at = taked_at;
    }

    public String getEstimate_sended_at() {
        return estimate_sended_at;
    }

    public void setEstimate_sended_at(String estimate_sended_at) {
        this.estimate_sended_at = estimate_sended_at;
    }

    public String getSend_model() {
        return send_model;
    }

    public void setSend_model(String send_model) {
        this.send_model = send_model;
    }

    public String getSend_com() {
        return send_com;
    }

    public void setSend_com(String send_com) {
        this.send_com = send_com;
    }

    public String getSend_no() {
        return send_no;
    }

    public void setSend_no(String send_no) {
        this.send_no = send_no;
    }

    public String getSended_at() {
        return sended_at;
    }

    public void setSended_at(String sended_at) {
        this.sended_at = sended_at;
    }

    public String getEstimated_successed_at() {
        return estimated_successed_at;
    }

    public void setEstimated_successed_at(String estimated_successed_at) {
        this.estimated_successed_at = estimated_successed_at;
    }

    public String getSuccessed_at() {
        return successed_at;
    }

    public void setSuccessed_at(String successed_at) {
        this.successed_at = successed_at;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

}
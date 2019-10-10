package com.ly.mt.center.data.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopInfo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("状态  0：正常，1：冻结")
    private String status;
    @ApiModelProperty("会员级别Id")
    private String shop_point_grade_id;
    @ApiModelProperty("店铺类型")
    private String shop_type;
    @ApiModelProperty("店铺名称")
    private String shop_name;
    @ApiModelProperty("电商店主名称")
    private String e_shop_name;
    @ApiModelProperty("电商URL")
    private String e_shop_url;
    @ApiModelProperty("店铺地址经度")
    private String shop_lon;
    @ApiModelProperty("店铺地址纬度")
    private String shop_lat;
    @ApiModelProperty("店铺地址")
    private String shop_address;
    @ApiModelProperty("店铺地址国家")
    private String shop_country;
    @ApiModelProperty("店铺地址省")
    private String shop_province_code;
    @ApiModelProperty("店铺地址城市")
    private String shop_city_code;
    @ApiModelProperty("身份证正面")
    private String idcard_front;
    @ApiModelProperty("身份证背面")
    private String idcard_back;
    @ApiModelProperty("真实姓名")
    private String real_name;
    @ApiModelProperty("身份证号码")
    private String idcard;
    @ApiModelProperty("证件期限")
    private String vaild_type;
    @ApiModelProperty("有效期开始日期")
    private String vaild_start_at;
    @ApiModelProperty("有效期结束日期")
    private String vaild_end_at;
    @ApiModelProperty("邀请码")
    private String invite_code;
    @ApiModelProperty("进货数")
    private String stock_num;
    @ApiModelProperty("标语")
    private String slogan;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("修改时间")
    private String modify_time;
    @ApiModelProperty("代理商主键id")
    private String operator_id;
    @ApiModelProperty("管易店铺code")
    private String gy_warehouse_code;
    @ApiModelProperty("管易店铺code")
    private String gy_shop_code;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShop_point_grade_id() {
        return shop_point_grade_id;
    }

    public void setShop_point_grade_id(String shop_point_grade_id) {
        this.shop_point_grade_id = shop_point_grade_id;
    }

    public String getShop_type() {
        return shop_type;
    }

    public void setShop_type(String shop_type) {
        this.shop_type = shop_type;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getE_shop_name() {
        return e_shop_name;
    }

    public void setE_shop_name(String e_shop_name) {
        this.e_shop_name = e_shop_name;
    }

    public String getE_shop_url() {
        return e_shop_url;
    }

    public void setE_shop_url(String e_shop_url) {
        this.e_shop_url = e_shop_url;
    }

    public String getShop_lon() {
        return shop_lon;
    }

    public void setShop_lon(String shop_lon) {
        this.shop_lon = shop_lon;
    }

    public String getShop_lat() {
        return shop_lat;
    }

    public void setShop_lat(String shop_lat) {
        this.shop_lat = shop_lat;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getShop_country() {
        return shop_country;
    }

    public void setShop_country(String shop_country) {
        this.shop_country = shop_country;
    }

    public String getShop_province_code() {
        return shop_province_code;
    }

    public void setShop_province_code(String shop_province_code) {
        this.shop_province_code = shop_province_code;
    }

    public String getShop_city_code() {
        return shop_city_code;
    }

    public void setShop_city_code(String shop_city_code) {
        this.shop_city_code = shop_city_code;
    }

    public String getIdcard_front() {
        return idcard_front;
    }

    public void setIdcard_front(String idcard_front) {
        this.idcard_front = idcard_front;
    }

    public String getIdcard_back() {
        return idcard_back;
    }

    public void setIdcard_back(String idcard_back) {
        this.idcard_back = idcard_back;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getVaild_type() {
        return vaild_type;
    }

    public void setVaild_type(String vaild_type) {
        this.vaild_type = vaild_type;
    }

    public String getVaild_start_at() {
        return vaild_start_at;
    }

    public void setVaild_start_at(String vaild_start_at) {
        this.vaild_start_at = vaild_start_at;
    }

    public String getVaild_end_at() {
        return vaild_end_at;
    }

    public void setVaild_end_at(String vaild_end_at) {
        this.vaild_end_at = vaild_end_at;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getStock_num() {
        return stock_num;
    }

    public void setStock_num(String stock_num) {
        this.stock_num = stock_num;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
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

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    @Override
    public String toString() {
        return "ShopInfo{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status='" + status + '\'' +
                ", shop_point_grade_id='" + shop_point_grade_id + '\'' +
                ", shop_type='" + shop_type + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", e_shop_name='" + e_shop_name + '\'' +
                ", e_shop_url='" + e_shop_url + '\'' +
                ", shop_lon='" + shop_lon + '\'' +
                ", shop_lat='" + shop_lat + '\'' +
                ", shop_address='" + shop_address + '\'' +
                ", shop_country='" + shop_country + '\'' +
                ", shop_province_code='" + shop_province_code + '\'' +
                ", shop_city_code='" + shop_city_code + '\'' +
                ", idcard_front='" + idcard_front + '\'' +
                ", idcard_back='" + idcard_back + '\'' +
                ", real_name='" + real_name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", vaild_type='" + vaild_type + '\'' +
                ", vaild_start_at='" + vaild_start_at + '\'' +
                ", vaild_end_at='" + vaild_end_at + '\'' +
                ", invite_code='" + invite_code + '\'' +
                ", stock_num='" + stock_num + '\'' +
                ", slogan='" + slogan + '\'' +
                ", create_time='" + create_time + '\'' +
                ", modify_time='" + modify_time + '\'' +
                ", operator_id='" + operator_id + '\'' +
                ", gy_warehouse_code='" + gy_warehouse_code + '\'' +
                ", gy_shop_code='" + gy_shop_code + '\'' +
                '}';
    }
}
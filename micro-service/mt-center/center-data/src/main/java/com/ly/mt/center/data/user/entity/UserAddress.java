package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserAddress {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("用户Id")
    private String user_id;
    @ApiModelProperty("用户名")
    private String user_name;
    @ApiModelProperty("收货人姓名")
    private String receive_name;
    @ApiModelProperty("收货人电话")
    private String receive_phone;
    @ApiModelProperty("省（直辖市）代码")
    private String province_code;
    @ApiModelProperty("省（直辖市）名称")
    private String province_name;
    @ApiModelProperty("省辖市代码")
    private String city_code;
    @ApiModelProperty("省辖市名称")
    private String city_name;
    @ApiModelProperty("县区代码")
    private String district_code;
    @ApiModelProperty("前端定位显示的定位值")
    private String picker_value;
    @ApiModelProperty("县区名称")
    private String district_name;
    @ApiModelProperty("地址维度")
    private String lat;
    @ApiModelProperty("地址经度")
    private String lon;
    @ApiModelProperty("详细地址")
    private String user_address;
    @ApiModelProperty("是否默认地址 0-非默认地址  1-默认地址")
    private String is_default;
    @ApiModelProperty("1：有效，2：无效")
    private String valid_status;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getReceive_name() {
        return receive_name;
    }

    public void setReceive_name(String receive_name) {
        this.receive_name = receive_name;
    }

    public String getReceive_phone() {
        return receive_phone;
    }

    public void setReceive_phone(String receive_phone) {
        this.receive_phone = receive_phone;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getPicker_value() {
        return picker_value;
    }

    public void setPicker_value(String picker_value) {
        this.picker_value = picker_value;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getValid_status() {
        return valid_status;
    }

    public void setValid_status(String valid_status) {
        this.valid_status = valid_status;
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
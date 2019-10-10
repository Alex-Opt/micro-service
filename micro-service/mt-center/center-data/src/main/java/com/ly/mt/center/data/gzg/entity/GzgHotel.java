package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgHotel {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("酒店名称")
    private String name;
    @ApiModelProperty("酒店地址")
    private String address;
    @ApiModelProperty("省CODE")
    private String province_code;
    @ApiModelProperty("市CODE")
    private String city_code;
    @ApiModelProperty("区CODE")
    private String area;
    @ApiModelProperty("酒店管理员id")
    private String hotel_admin_id;
    @ApiModelProperty("BD用户id")
    private String bd_id;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHotel_admin_id() {
        return hotel_admin_id;
    }

    public void setHotel_admin_id(String hotel_admin_id) {
        this.hotel_admin_id = hotel_admin_id;
    }

    public String getBd_id() {
        return bd_id;
    }

    public void setBd_id(String bd_id) {
        this.bd_id = bd_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}
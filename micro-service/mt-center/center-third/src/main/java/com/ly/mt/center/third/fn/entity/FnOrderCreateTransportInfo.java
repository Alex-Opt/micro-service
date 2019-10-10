package com.ly.mt.center.third.fn.entity;

/**
 * @Description 蜂鸟订单推送实体-
 * @Author taoye
 */
public class FnOrderCreateTransportInfo {
    // 站点id，定点送专用属性-非必填
    private String  transport_id;
    // 门店名称（支持汉字、符号、字母的组合），后期此参数将预留另用
    private String transport_name;
    // 取货点地址，后期此参数将预留另用
    private String transport_address;
    // 取货点经度，取值范围0～180，后期此参数将预留另用
    private String transport_longitude;
    // 取货点纬度，取值范围0～90，后期此参数将预留另用
    private String transport_latitude;
    // 取货点经纬度来源（1:腾讯地图, 2:百度地图, 3:高德地图），蜂鸟建议使用高德地图
    private String position_source;
    // 取货点联系方式, 只支持手机号,400开头电话以及座机号码
    private String transport_tel;
    // 取货点备注-非必填
    private String transport_remark;

    public String getTransport_id() {
        return transport_id;
    }

    public void setTransport_id(String transport_id) {
        this.transport_id = transport_id;
    }

    public String getTransport_name() {
        return transport_name;
    }

    public void setTransport_name(String transport_name) {
        this.transport_name = transport_name;
    }

    public String getTransport_address() {
        return transport_address;
    }

    public void setTransport_address(String transport_address) {
        this.transport_address = transport_address;
    }

    public String getTransport_longitude() {
        return transport_longitude;
    }

    public void setTransport_longitude(String transport_longitude) {
        this.transport_longitude = transport_longitude;
    }

    public String getTransport_latitude() {
        return transport_latitude;
    }

    public void setTransport_latitude(String transport_latitude) {
        this.transport_latitude = transport_latitude;
    }

    public String getPosition_source() {
        return position_source;
    }

    public void setPosition_source(String position_source) {
        this.position_source = position_source;
    }

    public String getTransport_tel() {
        return transport_tel;
    }

    public void setTransport_tel(String transport_tel) {
        this.transport_tel = transport_tel;
    }

    public String getTransport_remark() {
        return transport_remark;
    }

    public void setTransport_remark(String transport_remark) {
        this.transport_remark = transport_remark;
    }
}
package com.ly.mt.activity.advertisement.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 活动广告页面下单实体vo
 */
@ApiModel
public class PageOrderVo {
    @ApiModelProperty("烟杆sku")
    private String tobaccoSku;
    @ApiModelProperty("烟杆skuNum")
    private String tobaccoSkuNum;
    //烟弹sku
    @ApiModelProperty("烟弹sku")
    private String cartridgesSku;
    //烟弹skuNum
    @ApiModelProperty("烟弹skuNum")
    private String cartridgesSkuNum;

    @ApiModelProperty("赠品实体类")
    private List<Gift>  giftList;

    //详细地址
    @ApiModelProperty("详细地址")
    private String address;
    //电话
    @ApiModelProperty("电话")
    private String mobile;
    //姓名
    @ApiModelProperty("姓名")
    private String userName;
    //广告来源
    @ApiModelProperty("广告来源")
    private String source;
    //媒体类型
    @ApiModelProperty("媒体类型")
    private String type;
    //省编码
    @ApiModelProperty("省编码")
    private String provinceCode;
    //省名
    @ApiModelProperty("省名")
    private String provinceName;
    //市编码
    @ApiModelProperty("市编码")
    private String cityCode;
    //城市名
    @ApiModelProperty("城市名")
    private String cityName;
    //区县编码
    @ApiModelProperty("区县编码")
    private String districtCode;
    //区县名称
    @ApiModelProperty("区县名称")
    private String districtName;
    //渠道
    @ApiModelProperty("渠道")
    private String channel;
    //素材
    @ApiModelProperty("素材")
    private String material;
    //支付方式  6其他 货到付款   2 微信  3 支付宝
    @ApiModelProperty("@ApiModelProperty(\"素材\")")
    private String paymentType;
    @ApiModelProperty("留言")
    private String remark;
    @ApiModelProperty("订单金额")
    private String orderMoney;
    @ApiModelProperty("烟杆价格")
    private String tobaccoSkuPrice;
    @ApiModelProperty("烟弹sku价格")
    private String cartridgesSkuPrice;
    public PageOrderVo() {}

    public PageOrderVo(String tobaccoSku, String tobaccoSkuNum, String cartridgesSku, String cartridgesSkuNum, String address, String mobile,
                       String userName, String source, String type, String provinceCode, String provinceName, String cityCode, String cityName,
                       String districtCode, String districtName, String channel, String material, String paymentType,String remark,String orderMoney,
                       String tobaccoSkuPrice,String cartridgesSkuPrice) {
        this.tobaccoSku = tobaccoSku;
        this.tobaccoSkuNum = tobaccoSkuNum;
        this.cartridgesSku = cartridgesSku;
        this.cartridgesSkuNum = cartridgesSkuNum;
        this.address = address;
        this.mobile = mobile;
        this.userName = userName;
        this.source = source;
        this.type = type;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.districtCode = districtCode;
        this.districtName = districtName;
        this.channel = channel;
        this.material = material;
        this.paymentType = paymentType;
        this.remark = remark;
        this.orderMoney = orderMoney;
        this.cartridgesSkuPrice = cartridgesSkuPrice;
        this.tobaccoSkuPrice = tobaccoSkuPrice;
    }


    public List<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<Gift> giftList) {
        this.giftList = giftList;
    }

    public String getTobaccoSkuPrice() {
        return tobaccoSkuPrice;
    }

    public void setTobaccoSkuPrice(String tobaccoSkuPrice) {
        this.tobaccoSkuPrice = tobaccoSkuPrice;
    }

    public String getCartridgesSkuPrice() {
        return cartridgesSkuPrice;
    }

    public void setCartridgesSkuPrice(String cartridgesSkuPrice) {
        this.cartridgesSkuPrice = cartridgesSkuPrice;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTobaccoSku() {
        return tobaccoSku;
    }

    public void setTobaccoSku(String tobaccoSku) {
        this.tobaccoSku = tobaccoSku;
    }

    public String getTobaccoSkuNum() {
        return tobaccoSkuNum;
    }

    public void setTobaccoSkuNum(String tobaccoSkuNum) {
        this.tobaccoSkuNum = tobaccoSkuNum;
    }

    public String getCartridgesSku() {
        return cartridgesSku;
    }

    public void setCartridgesSku(String cartridgesSku) {
        this.cartridgesSku = cartridgesSku;
    }

    public String getCartridgesSkuNum() {
        return cartridgesSkuNum;
    }

    public void setCartridgesSkuNum(String cartridgesSkuNum) {
        this.cartridgesSkuNum = cartridgesSkuNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }


    @Override
    public String toString() {
        return "PageOrderVo{" +
                "tobaccoSku='" + tobaccoSku + '\'' +
                ", tobaccoSkuNum='" + tobaccoSkuNum + '\'' +
                ", cartridgesSku='" + cartridgesSku + '\'' +
                ", cartridgesSkuNum='" + cartridgesSkuNum + '\'' +
                ", giftList=" + giftList +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userName='" + userName + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", districtCode='" + districtCode + '\'' +
                ", districtName='" + districtName + '\'' +
                ", channel='" + channel + '\'' +
                ", material='" + material + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", remark='" + remark + '\'' +
                ", orderMoney='" + orderMoney + '\'' +
                ", tobaccoSkuPrice='" + tobaccoSkuPrice + '\'' +
                ", cartridgesSkuPrice='" + cartridgesSkuPrice + '\'' +
                '}';
    }
}

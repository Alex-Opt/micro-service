package com.ly.mt.home.tob.purchases.vo;

import com.ly.mt.home.tob.discount.vo.ShopPurchasesDiscountVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 进货单
 *
 * @author: linan
 * @date: 2019/9/10
 **/
@ApiModel
public class ShopPurchasesVo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("买家Id")
    private String userId;
    @ApiModelProperty("卖家id")
    private String sellerId;
    @ApiModelProperty("店铺Id")
    private String shopId;
    @ApiModelProperty("收货人")
    private String consignee;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("省编码")
    private String provinceCode;
    @ApiModelProperty("城市编码")
    private String cityCode;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("详细地址")
    private String fullAddress;
    @ApiModelProperty("优惠券编号")
    private String couponId;
    @ApiModelProperty("配送方式")
    private String deliveryType;
    @ApiModelProperty("配送费")
    private String deliveryFee;
    @ApiModelProperty("订单金额")
    private String amount;
    @ApiModelProperty("优惠金额")
    private String discount;
    @ApiModelProperty("实际金额")
    private String actualAmount;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("状态10:待付款 15:已取消 20:待配送 25:部分发货 30:待收货 99:完成")
    private String status;
    @ApiModelProperty("支付方式 1 网银，2 微信，3 支付宝，4 其他，5 信用，6 其他，7 线下方式")
    private String payedModel;
    @ApiModelProperty("支付订单号")
    private String transactionId;
    @ApiModelProperty("支付时间")
    private String payedTime;
    @ApiModelProperty("发货时间")
    private String sendedTime;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("修改时间")
    private String modifyTime;
    @ApiModelProperty("快递公司代码")
    private String logisticsCode;
    @ApiModelProperty("快递单号")
    private String gyLogisticsCode;
    @ApiModelProperty("区县code")
    private String districtCode;
    @ApiModelProperty("收货地址id")
    private String addressId;
    @ApiModelProperty("管易订单推送状态")
    private String pushStatus;

    private String provinceName;

    private String cityName;

    private String districtName;

    private Integer totalSkuNum;

    private Integer totalRefundNum;

    private String preAmount;

    private String discountType;

    private String discountRate;

    @ApiModelProperty("是否是购物车购买 1、是，0、否")
    private Integer buyType;

    private ShopPurchasesDiscountVo discountVo;

    private List<ShopPurchasesDiscountVo> discountList;

    private List<ShopPurchasesItemsVo> itemList;

    public ShopPurchasesVo() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayedModel() {
        return payedModel;
    }

    public void setPayedModel(String payedModel) {
        this.payedModel = payedModel;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPayedTime() {
        return payedTime;
    }

    public void setPayedTime(String payedTime) {
        this.payedTime = payedTime;
    }

    public String getSendedTime() {
        return sendedTime;
    }

    public void setSendedTime(String sendedTime) {
        this.sendedTime = sendedTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<ShopPurchasesItemsVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<ShopPurchasesItemsVo> itemList) {
        this.itemList = itemList;
    }

    public Integer getTotalSkuNum() {
        return totalSkuNum;
    }

    public void setTotalSkuNum(Integer totalSkuNum) {
        this.totalSkuNum = totalSkuNum;
    }

    public Integer getTotalRefundNum() {
        return totalRefundNum;
    }

    public void setTotalRefundNum(Integer totalRefundNum) {
        this.totalRefundNum = totalRefundNum;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getGyLogisticsCode() {
        return gyLogisticsCode;
    }

    public void setGyLogisticsCode(String gyLogisticsCode) {
        this.gyLogisticsCode = gyLogisticsCode;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(String preAmount) {
        this.preAmount = preAmount;
    }

    public ShopPurchasesDiscountVo getDiscountVo() {
        return discountVo;
    }

    public void setDiscountVo(ShopPurchasesDiscountVo discountVo) {
        this.discountVo = discountVo;
    }

    public List<ShopPurchasesDiscountVo> getDiscountList() {
        return CollectionUtils.isEmpty(discountList) ? new ArrayList<>() : discountList;
    }

    public void setDiscountList(List<ShopPurchasesDiscountVo> discountList) {
        this.discountList = discountList;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(String pushStatus) {
        this.pushStatus = pushStatus;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Integer getBuyType() {
        return (buyType == null) ? 0 : buyType;
    }

    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
    }

    @Override
    public String toString() {
        return "ShopPurchasesVo{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", consignee='" + consignee + '\'' +
                ", mobile='" + mobile + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", address='" + address + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                ", couponId='" + couponId + '\'' +
                ", deliveryType='" + deliveryType + '\'' +
                ", deliveryFee='" + deliveryFee + '\'' +
                ", amount='" + amount + '\'' +
                ", discount='" + discount + '\'' +
                ", actualAmount='" + actualAmount + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", payedModel='" + payedModel + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", payedTime='" + payedTime + '\'' +
                ", sendedTime='" + sendedTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", logisticsCode='" + logisticsCode + '\'' +
                ", gyLogisticsCode='" + gyLogisticsCode + '\'' +
                ", districtCode='" + districtCode + '\'' +
                ", addressId='" + addressId + '\'' +
                ", pushStatus='" + pushStatus + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", districtName='" + districtName + '\'' +
                ", totalSkuNum=" + totalSkuNum +
                ", totalRefundNum=" + totalRefundNum +
                ", preAmount='" + preAmount + '\'' +
                ", discountType='" + discountType + '\'' +
                ", discountRate='" + discountRate + '\'' +
                ", buyType=" + buyType +
                '}';
    }
}
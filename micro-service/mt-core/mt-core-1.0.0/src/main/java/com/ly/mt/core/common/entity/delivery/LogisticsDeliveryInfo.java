package com.ly.mt.core.common.entity.delivery;

/**
 * 封装管易返回的订单物流快递信息实体模型，不用extends extends BaseController
 *
 * @author zhanglifeng
 * @date 2019-05-31
 */
public class LogisticsDeliveryInfo {
    /**
     * 单据编号
     */
    private String code;
    /**
     * 快递单号
     */
    private String expressNo;
    /**
     * 快递公司code
     */
    private String expressCode;

    /**
     * 快递公司名字
     */
    private String expressName;

    /**
     * 平台单号-即订单编号
     */
    private String platformCode;

    /**
     * 预计发货时间
     */
    private String planDeliveryDate;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }

    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }
}

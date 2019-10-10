package com.ly.mt.home.tob.purchases.vo;

import java.io.Serializable;

/**
 * 物流
 *
 * @author: linan
 * @date: 2019/9/10
 **/
public class LogisticsDeliveryInfo implements Serializable {

    private static final long serialVersionUID = 4091169973589452778L;

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

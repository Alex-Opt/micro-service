package com.ly.mt.home.tob.pay.vo;

/**
 * @author linan
 * @description : PaymentDetailVo
 * @date 2019/7/17
 */
public class PaymentDetailVo {
    private String id;

    private String orderNo;

    private String payee;

    private String payer;

    private String money;

    private String tradeType;

    private String tradeStatus;

    private String paymentType;

    private String paymentNo;

    private String createTime;

    private String remark;

    public PaymentDetailVo() {
    }

    private PaymentDetailVo(Builder builder) {
        setId(builder.id);
        setOrderNo(builder.orderNo);
        setPayee(builder.payee);
        setPayer(builder.payer);
        setMoney(builder.money);
        setTradeType(builder.tradeType);
        setTradeStatus(builder.tradeStatus);
        setPaymentType(builder.paymentType);
        setPaymentNo(builder.paymentNo);
        setCreateTime(builder.createTime);
        setRemark(builder.remark);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public static final class Builder {
        private String id;
        private String orderNo;
        private String payee;
        private String payer;
        private String money;
        private String tradeType;
        private String tradeStatus;
        private String paymentType;
        private String paymentNo;
        private String createTime;
        private String remark;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder orderNo(String val) {
            orderNo = val;
            return this;
        }

        public Builder payee(String val) {
            payee = val;
            return this;
        }

        public Builder payer(String val) {
            payer = val;
            return this;
        }

        public Builder money(String val) {
            money = val;
            return this;
        }

        public Builder tradeType(String val) {
            tradeType = val;
            return this;
        }

        public Builder tradeStatus(String val) {
            tradeStatus = val;
            return this;
        }

        public Builder paymentType(String val) {
            paymentType = val;
            return this;
        }

        public Builder paymentNo(String val) {
            paymentNo = val;
            return this;
        }

        public Builder createTime(String val) {
            createTime = val;
            return this;
        }

        public Builder remark(String val) {
            remark = val;
            return this;
        }

        public PaymentDetailVo build() {
            return new PaymentDetailVo(this);
        }
    }
}

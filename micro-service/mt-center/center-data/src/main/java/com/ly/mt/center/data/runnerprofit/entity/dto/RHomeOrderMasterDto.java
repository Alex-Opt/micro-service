package com.ly.mt.center.data.runnerprofit.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description
 * 到家c端订单主体表
 * 赚钱人业务需要dto
 * @author panjingtian
 * @date 2019/7/2 11:20 AM
 */
public class RHomeOrderMasterDto implements Serializable {



    /**
     * 订单单号id
     */
    private Long orderId;

    /**
     * 下单用户id
     */
    private Long userId;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public RHomeOrderMasterDto() {
    }
}

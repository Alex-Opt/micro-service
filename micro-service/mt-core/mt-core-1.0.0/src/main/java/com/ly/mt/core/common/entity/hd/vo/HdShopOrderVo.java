package com.ly.mt.core.common.entity.hd.vo;


import java.util.List;

/**
 * @description
 * 商家查看订单vo
 * @author panjingtian
 * @date 2019/6/17 12:41 PM
 */
public class HdShopOrderVo {


    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单单号
     */
    private String orderId;

    /**
     * 订单详情列
     */
    private List<HdShopAttDetailVo> detailVos;

    /**
     * 订单状态
     *      {@link com.ly.mt.core.common.dict.OrderStatusEnum}
     */
    private String orderStatus;

    /**
     * 活动用户vo
     */
    private HdActivityUserVo activityUserVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<HdShopAttDetailVo> getDetailVos() {
        return detailVos;
    }

    public void setDetailVos(List<HdShopAttDetailVo> detailVos) {
        this.detailVos = detailVos;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public HdActivityUserVo getActivityUserVo() {
        return activityUserVo;
    }

    public void setActivityUserVo(HdActivityUserVo activityUserVo) {
        this.activityUserVo = activityUserVo;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 订单总价
     */
    private Double totalPrice;

    public HdShopOrderVo() {
    }

    public HdShopOrderVo(Long id, String orderId, List<HdShopAttDetailVo> detailVos, String orderStatus, HdActivityUserVo activityUserVo, Double totalPrice) {
        this.id = id;
        this.orderId = orderId;
        this.detailVos = detailVos;
        this.orderStatus = orderStatus;
        this.activityUserVo = activityUserVo;
        this.totalPrice = totalPrice;
    }
}

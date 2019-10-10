package com.ly.mt.core.common.entity.trade;


import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * 退货详情表 trade_order_refund_item
 * 
 * @author ruoyi
 * @date 2019-06-27
 */
public class TradeOrderRefundItem extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** Id */
	private String id;
	/** 退货单Id */
	private String refundId;
	/** 订单Id */
	private String orderId;
	/** 商品skuId */
	private String  skuId;
	/** 退货数量 */
	private String refundNum;
	/** 商品单价（分） */
	private String skuPrice;
	/** 商品订单金额（分） */
	private String skuOrderPrice;
	/** 实际退货金额（分） */
	private String skuRefundPrice;
	/** 扣减分摊优惠金额（分） */
	private String subtractCouponPrice;
	/** 扣减分摊运费金额（分 */
	private String subtractFreightPrice;
	/** 详情状态*/
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}





	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(String refundNum) {
		this.refundNum = refundNum;
	}

	public String getSkuPrice() {
		return skuPrice;
	}

	public void setSkuPrice(String skuPrice) {
		this.skuPrice = skuPrice;
	}

	public String getSkuOrderPrice() {
		return skuOrderPrice;
	}

	public void setSkuOrderPrice(String skuOrderPrice) {
		this.skuOrderPrice = skuOrderPrice;
	}

	public String getSkuRefundPrice() {
		return skuRefundPrice;
	}

	public void setSkuRefundPrice(String skuRefundPrice) {
		this.skuRefundPrice = skuRefundPrice;
	}

	public String getSubtractCouponPrice() {
		return subtractCouponPrice;
	}

	public void setSubtractCouponPrice(String subtractCouponPrice) {
		this.subtractCouponPrice = subtractCouponPrice;
	}

	public String getSubtractFreightPrice() {
		return subtractFreightPrice;
	}

	public void setSubtractFreightPrice(String subtractFreightPrice) {
		this.subtractFreightPrice = subtractFreightPrice;
	}
}

package com.ly.mt.mis.home.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.EnumUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.coupon.entity.CouponInfo;
import com.ly.mt.core.data.goods.entity.GoodsSkuCode;
import com.ly.mt.core.data.order.entity.OrderHcView;
import com.ly.mt.core.data.order.entity.OrdersBattleInfo;
import com.ly.mt.core.data.payment.entity.PaymentDetail;
import com.ly.mt.core.data.shop.entity.ShopInfo;
import com.ly.mt.core.data.trade.entity.TradeOrderCoupon;
import com.ly.mt.core.data.trade.entity.TradeOrderItems;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.core.data.user.entity.UserAddress;
import com.ly.mt.core.logistics.dict.Kd100Com;
import com.ly.mt.core.logistics.entity.LogisticsInfo;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.home.order.service.HomecOrderService;
import com.ly.mt.mis.home.order.vo.HomecOrderDatagridVo;
import com.ly.mt.mis.home.order.vo.HomecOrderInfoGoodsVo;
import com.ly.mt.mis.home.order.vo.HomecOrderInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.dict.CouponType.COUPON_TYPE_COUPON;
import static com.ly.mt.core.base.dict.DistributeType.DISTRIBUTE_TYPE_ONE_HOUR;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.logistics.dict.Kd100Com.KD_COM_SF;

/**
 * MOTI到家-订单管理-订单列表
 *
 * @author taoye
 */
@Service
public class HomecOrderServiceImpl extends BaseServiceImpl implements HomecOrderService {
    private final static Logger LOGGER = LoggerFactory.getLogger(HomecOrderServiceImpl.class);
    @Override
    public HomecOrderInfoVo loadOrderInfo(String id) throws Exception {
        HomecOrderInfoVo vo = new HomecOrderInfoVo();
        OrderHcView view = orderHcViewDao.getOrderHcViewByIdFromRedis(id);
        // 订单信息
        packageOrderInfo(vo, view);
        // 商品信息
        packageGoodsInfo(vo, id);
        // 支付信息
        packagePaymentInfo(vo, view);
        // 物流信息
        packageLogisticsInfo(vo, view);
        // 客户信息
        packageBuyerInfo(vo, view);
        // 商家信息
        if (DISTRIBUTE_TYPE_ONE_HOUR.getId().equals(view.getDistributionId())) {
            packageSellerInfo(vo, view);
        }
        return vo;
    }

    /**
     * 订单信息封装
     *
     * @author taoye
     */
    private void packageOrderInfo(HomecOrderInfoVo vo, OrderHcView view) {
        vo.setOrderNo(view.getOrderNo());
        vo.setCreateTime(view.getCreateTime());
        vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", view.getOrderStatus()));
        vo.setOrderTypeName(EnumUtil.getNameById("OrderType", view.getOrderType()));
    }

    /**
     * 商品信息封装
     *
     * @author taoye
     */
    private void packageGoodsInfo(HomecOrderInfoVo vo, String id) {
        List<HomecOrderInfoGoodsVo> vos = new ArrayList<>();
        List<TradeOrderItems> list = tradeOrderItemsDao.listTradeOrderItemsByOrderIdFromRedis(id);
        if (null != list && list.size() > 0) {
            vos = JSONObject.parseObject(JSONObject.toJSONString(list), new TypeReference<List<HomecOrderInfoGoodsVo>>() {
            });
            vos.forEach(
                    goods -> {
                        String skuId = goods.getSkuId();
                        if (StringUtil.isNotEmpty(skuId)) {
                            GoodsSkuCode skuCode = goodsSkuCodeDao.getGoodsSkuCodeBySkuIdFromRedis(skuId);
                            goods.setCode(skuCode.getCode());
                        }
                    }
            );
        }
        vo.setGoods(vos);
    }

    /**
     * 支付信息封装
     *
     * @author taoye
     */
    private void packagePaymentInfo(HomecOrderInfoVo vo, OrderHcView view) {
        vo.setOldMoney(view.getOrderOldMoney());
        vo.setDenomination(view.getOrderDiscountMoney());
        vo.setPayMoney(view.getOrderMoney());
        vo.setPayTime(view.getPayTime());
        vo.setFreight(view.getFreight());
        vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", view.getPaymentType()));
        String orderNo = view.getOrderNo();
        if (StringUtil.isNotEmpty(orderNo)) {
            PaymentDetail paymentDetail = new PaymentDetail();
            paymentDetail.setOrderNo(orderNo);
            paymentDetail.setTradeStatus(TRADE_STATUS_PAY_SUCCESS.getId());
            paymentDetail = paymentDetailDao.getPaymentDetailFromMysql(paymentDetail);
            vo.setPaymentNo(paymentDetail.getPaymentNo());
        }
        List<TradeOrderCoupon> tradeOrderCoupons = tradeOrderCouponDao.listTradeOrderCouponByOrderIdAndCouponTypeFromRedis(view.getId(), COUPON_TYPE_COUPON.getId());
        if (tradeOrderCoupons.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (TradeOrderCoupon tradeOrderCoupon : tradeOrderCoupons) {
                String couponId = tradeOrderCoupon.getCouponActivityId();
                if (StringUtil.isNotEmpty(couponId)) {
                    CouponInfo couponInfo = couponInfoDao.getCouponInfoByIdFromRedis(couponId);
                    sb.append(couponInfo.getDiscountRate()).append("<br/>");
                }
            }
            if (sb.length() > 0) {
                vo.setDiscountRate(sb.substring(0, sb.length() - 6).toString());
            }
        }
    }

    /**
     * 物流信息封装
     *
     * @author taoye
     */
    private void packageLogisticsInfo(HomecOrderInfoVo vo, OrderHcView view) {
        vo.setShopName(view.getShopName());
        UserAddress userAddress = userAddressDao.getUserAddressByIdFromRedis(view.getAddressId());
        vo.setReceivingAddress(userAddress.getUserAddress());
        String distributeType = view.getDistributionId();
        vo.setDistributionName(EnumUtil.getNameById("DistributeType", distributeType));
        if (DISTRIBUTE_TYPE_ONE_HOUR.getId().equals(distributeType)) {
            OrdersBattleInfo ordersBattleInfo = ordersBattleInfoDao.getOrdersBattleInfoByOrderIdFromRedis(view.getId());
            vo.setLogisticsNo(ordersBattleInfo.getSendNo());
            vo.setLogisticsStateName(EnumUtil.getNameById("OrderBattleStatus", ordersBattleInfo.getStatus()));
            vo.setLogisticsName("蜂鸟配送");
            vo.setRider(ordersBattleInfo.getDeliverymanName() + ordersBattleInfo.getDeliverymanPhone());
        } else {
            String logisticsCode = view.getLogisticsCode();
            vo.setLogisticsName(Kd100Com.getNameByCode(logisticsCode));
            String logisticsNo = view.getGyLogisticsCode();
            vo.setLogisticsNo(logisticsNo);
            String mobile = null;
            if (KD_COM_SF.getGyCom().equals(logisticsCode)) {
                mobile = userAddress.getReceivePhone();
            }
            try {
                LogisticsInfo logisticsInfo = kd100Service.getLogisticsInfo(logisticsCode, logisticsNo, mobile);
                vo.setLogisticsStateName(logisticsInfo.getLogisticsStateName());
            } catch (Exception e) {
                LOGGER.error("OrderHcServiceImpl.loadOrderInfo getLogisticsInfo error ", e);
            }
        }
    }

    /**
     * 买家信息封装
     *
     * @author taoye
     */
    private void packageBuyerInfo(HomecOrderInfoVo vo, OrderHcView view) {
        String buyerId = view.getBuyerId();
        vo.setBuyerId(buyerId);
        vo.setBuyerName(view.getBuyerName());
        vo.setBuyerMemo(view.getBuyerMemo());
        if (StringUtil.isNotEmpty(buyerId)) {
            User buyer = userDao.getUserByIdFromRedis(buyerId);
            vo.setBuyerMobile(buyer.getMobile());
            vo.setBuyerRegisterTime(buyer.getCreateTime());
        }
    }

    /**
     * 商家信息封装
     *
     * @author taoye
     */
    private void packageSellerInfo(HomecOrderInfoVo vo, OrderHcView view) {
        String sellerId = view.getSellerId();
        vo.setSellerId(sellerId);
        vo.setSellerMobile(view.getSellerMobile());
        if (StringUtil.isNotEmpty(sellerId)) {
            User seller = userDao.getUserByIdFromRedis(sellerId);
            vo.setSellerName(seller.getUserName());
            vo.setSellerRegisterTime(seller.getCreateTime());
            String shopId = seller.getShopId();
            if (StringUtil.isNotEmpty(shopId)) {
                ShopInfo shopInfo = shopInfoDao.getShopInfoByIdFromRedis(shopId);
                vo.setShopAddress(shopInfo.getShopAddress());
            }
        }
    }


    @Override
    public ResponseJson loadOrderDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        OrderHcView view = JSONObject.toJavaObject(jsonObject, OrderHcView.class);
        view.setCreateTime(getBetweenSql(jsonObject.getString("createTimeStart"), jsonObject.getString("createTimeEnd")));
        view.setOrderMoney(getBetweenSql(jsonObject.getString("orderMoneyStart"), jsonObject.getString("orderMoneyEnd")));
        Datagrid datagrid = orderHcViewDao.loadDatagridFromMysql(view, page);
        List<HomecOrderDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<HomecOrderDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    vo.setDistributionName(EnumUtil.getNameById("DistributeType", vo.getDistributionId()));
                    vo.setOrderTypeName(EnumUtil.getNameById("OrderType", vo.getOrderType()));
                    vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", vo.getOrderStatus()));
                    vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", vo.getPaymentType()));
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }


    @Override
    public List<HomecOrderDatagridVo> listOrderExcel(JSONObject jsonObject) throws Exception {
        OrderHcView view = JSONObject.toJavaObject(jsonObject, OrderHcView.class);
        view.setCreateTime(getBetweenSql(jsonObject.getString("createTimeStart"), jsonObject.getString("createTimeEnd")));
        view.setOrderMoney(getBetweenSql(jsonObject.getString("orderMoneyStart"), jsonObject.getString("orderMoneyEnd")));
        List<OrderHcView> list = orderHcViewDao.listOrderHcViewFromMysql(view);
        List<HomecOrderDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(list), new TypeReference<List<HomecOrderDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", vo.getOrderStatus()));
                    vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", vo.getPaymentType()));
                    vo.setDistributionName(EnumUtil.getNameById("DistributeType", vo.getDistributionId()));
                    vo.setOrderTypeName(EnumUtil.getNameById("OrderType", vo.getOrderType()));
                }
        );
        return vos;
    }
}
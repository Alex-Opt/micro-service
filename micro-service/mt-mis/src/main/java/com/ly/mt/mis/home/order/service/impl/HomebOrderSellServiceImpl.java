package com.ly.mt.mis.home.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.EnumUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.coupon.dao.CouponInfoDao;
import com.ly.mt.core.data.coupon.entity.CouponInfo;
import com.ly.mt.core.data.goods.dao.GoodsSkuCodeDao;
import com.ly.mt.core.data.goods.entity.GoodsSkuCode;
import com.ly.mt.core.data.order.dao.OrderHbsViewDao;
import com.ly.mt.core.data.order.dao.OrdersBattleInfoDao;
import com.ly.mt.core.data.order.entity.OrderHbsView;
import com.ly.mt.core.data.order.entity.OrdersBattleInfo;
import com.ly.mt.core.data.payment.dao.PaymentDetailDao;
import com.ly.mt.core.data.payment.entity.PaymentDetail;
import com.ly.mt.core.data.shop.dao.ShopInfoDao;
import com.ly.mt.core.data.shop.entity.ShopInfo;
import com.ly.mt.core.data.trade.dao.TradeOrderCouponDao;
import com.ly.mt.core.data.trade.dao.TradeOrderItemsDao;
import com.ly.mt.core.data.trade.entity.TradeOrderCoupon;
import com.ly.mt.core.data.trade.entity.TradeOrderItems;
import com.ly.mt.core.data.user.dao.UserAddressDao;
import com.ly.mt.core.data.user.dao.UserDao;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.core.data.user.entity.UserAddress;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.home.order.service.HomebOrderSellService;
import com.ly.mt.mis.home.order.vo.HomebOrderSellDatagridVo;
import com.ly.mt.mis.home.order.vo.HomebOrderSellInfoGoodsVo;
import com.ly.mt.mis.home.order.vo.HomebOrderSellInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.dict.CouponType.COUPON_TYPE_COUPON;
import static com.ly.mt.core.base.dict.TradeStatus.TRADE_STATUS_PAY_SUCCESS;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * MOTI商家端-订单管理-售卖订单
 *
 * @author taoye
 */
@Service
public class HomebOrderSellServiceImpl extends BaseServiceImpl implements HomebOrderSellService {
    @Resource
    private OrderHbsViewDao orderHbsViewDao;
    @Resource
    private TradeOrderItemsDao tradeOrderItemsDao;
    @Resource
    private ShopInfoDao shopInfoDao;
    @Resource
    private GoodsSkuCodeDao goodsSkuCodeDao;
    @Resource
    private PaymentDetailDao paymentDetailDao;
    @Resource
    private TradeOrderCouponDao tradeOrderCouponDao;
    @Resource
    private CouponInfoDao couponInfoDao;
    @Resource
    private UserDao userDao;
    @Resource
    private UserAddressDao userAddressDao;
    @Resource
    private OrdersBattleInfoDao ordersBattleInfoDao;


    @Override
    public HomebOrderSellInfoVo loadOrderInfo(String id) throws Exception {
        HomebOrderSellInfoVo vo = new HomebOrderSellInfoVo();
        OrderHbsView view = orderHbsViewDao.getOrderHbsViewByIdFromRedis(id);
        // 订单信息
        packageOrderInfo(vo, view);
        // 商品信息
        packageGoodsInfo(vo, id);
        // 支付信息
        packagePaymentInfo(vo, view);
        // 物流信息
        packageLogisticsInfo(vo, id);
        // 客户信息
        packageBuyerInfo(vo, view);
        // 商家信息
        packageSellerInfo(vo, view);
        return vo;
    }

    /**
     * 订单信息封装
     *
     * @author taoye
     */
    private void packageOrderInfo(HomebOrderSellInfoVo vo, OrderHbsView view) {
        vo.setOrderNo(view.getOrderNo());
        vo.setCreateTime(view.getCreateTime());
        vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", view.getOrderStatus()));
    }

    /**
     * 商品信息封装
     *
     * @author taoye
     */
    private void packageGoodsInfo(HomebOrderSellInfoVo vo, String id) {
        List<TradeOrderItems> list = tradeOrderItemsDao.listTradeOrderItemsByOrderIdFromRedis(id);
        List<HomebOrderSellInfoGoodsVo> vos = JSONObject.parseObject(JSONObject.toJSONString(list), new TypeReference<List<HomebOrderSellInfoGoodsVo>>() {
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
        vo.setGoods(vos);
    }

    /**
     * 支付信息封装
     *
     * @author taoye
     */
    private void packagePaymentInfo(HomebOrderSellInfoVo vo, OrderHbsView view) {
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

    /**
     * 物流信息封装
     *
     * @author taoye
     */
    private void packageLogisticsInfo(HomebOrderSellInfoVo vo, String id) {
        OrdersBattleInfo ordersBattleInfo = ordersBattleInfoDao.getOrdersBattleInfoByOrderIdFromRedis(id);
        vo.setRider(ordersBattleInfo.getDeliverymanName() + ordersBattleInfo.getDeliverymanPhone());
        vo.setLogisticsStatusName(EnumUtil.getNameById("OrderBattleStatus", ordersBattleInfo.getStatus()));
        vo.setLogisticsNo(ordersBattleInfo.getSendNo());
    }

    /**
     * 买家信息封装
     *
     * @author taoye
     */
    private void packageBuyerInfo(HomebOrderSellInfoVo vo, OrderHbsView view) {
        String buyerId = view.getBuyerId();
        vo.setBuyerId(buyerId);
        vo.setBuyerName(view.getBuyerName());
        vo.setBuyerMemo(view.getBuyerMemo());
        if (StringUtil.isNotEmpty(buyerId)) {
            User buyer = userDao.getUserByIdFromRedis(buyerId);
            vo.setBuyerMobile(buyer.getMobile());
            vo.setBuyerRegisterTime(buyer.getCreateTime());
        }
        String addressId = view.getAddressId();
        if (StringUtil.isNotEmpty(addressId)) {
            UserAddress address = userAddressDao.getUserAddressByIdFromRedis(addressId);
            vo.setReceivingAddress(address.getUserAddress());
        }
    }

    /**
     * 商家信息封装
     *
     * @author taoye
     */
    private void packageSellerInfo(HomebOrderSellInfoVo vo, OrderHbsView view) {
        String sellerId = view.getSellerId();
        vo.setSellerId(sellerId);
        vo.setSellerMobile(view.getSellerMobile());
        if (StringUtil.isNotEmpty(sellerId)) {
            User seller = userDao.getUserByIdFromRedis(sellerId);
            vo.setSellerName(seller.getUserName());
            vo.setSellerRegisterTime(seller.getCreateTime());
        }
        String shopId = view.getShopId();
        if (StringUtil.isNotEmpty(shopId)) {
            ShopInfo shopInfo = shopInfoDao.getShopInfoByIdFromRedis(shopId);
            vo.setShopAddress(shopInfo.getShopAddress());
        }
    }


    @Override
    public ResponseJson loadOrderDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        OrderHbsView view = JSONObject.toJavaObject(jsonObject, OrderHbsView.class);
        view.setCreateTime(getBetweenSql(jsonObject.getString("createTimeStart"), jsonObject.getString("createTimeEnd")));
        view.setOrderMoney(getBetweenSql(jsonObject.getString("orderMoneyStart"), jsonObject.getString("orderMoneyEnd")));
        Datagrid datagrid = orderHbsViewDao.loadDatagridFromMysql(view, page);
        List<HomebOrderSellDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<HomebOrderSellDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", vo.getOrderStatus()));
                    vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", vo.getPaymentType()));
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }


    @Override
    public List<HomebOrderSellDatagridVo> listOrderExcel(JSONObject jsonObject) throws Exception {
        OrderHbsView view = JSONObject.toJavaObject(jsonObject, OrderHbsView.class);
        view.setCreateTime(getBetweenSql(jsonObject.getString("createTimeStart"), jsonObject.getString("createTimeEnd")));
        view.setOrderMoney(getBetweenSql(jsonObject.getString("orderMoneyStart"), jsonObject.getString("orderMoneyEnd")));
        List<OrderHbsView> views = orderHbsViewDao.listOrderHbsViewFromMysql(view);
        List<HomebOrderSellDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(views), new TypeReference<List<HomebOrderSellDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", vo.getOrderStatus()));
                    vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", vo.getPaymentType()));
                }
        );
        return vos;
    }
}
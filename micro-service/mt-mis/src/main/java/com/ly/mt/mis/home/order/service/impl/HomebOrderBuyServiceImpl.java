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
import com.ly.mt.core.data.order.dao.OrderHbpViewDao;
import com.ly.mt.core.data.order.entity.OrderHbpView;
import com.ly.mt.core.data.shop.dao.ShopInfoDao;
import com.ly.mt.core.data.shop.dao.ShopPurchasesDiscountDao;
import com.ly.mt.core.data.shop.dao.ShopPurchasesItemsDao;
import com.ly.mt.core.data.shop.entity.ShopInfo;
import com.ly.mt.core.data.shop.entity.ShopPurchasesDiscount;
import com.ly.mt.core.data.shop.entity.ShopPurchasesItems;
import com.ly.mt.core.data.user.dao.UserDao;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.core.logistics.entity.LogisticsInfo;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.home.order.service.HomebOrderBuyService;
import com.ly.mt.mis.home.order.vo.HomebOrderBuyDatagridVo;
import com.ly.mt.mis.home.order.vo.HomebOrderBuyInfoGoodsVo;
import com.ly.mt.mis.home.order.vo.HomebOrderBuyInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.dict.DiscountType.DISCOUNT_TYPE_COUPON;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * MOTI商家端-订单管理-进货订单
 *
 * @author taoye
 */
@Service
public class HomebOrderBuyServiceImpl extends BaseServiceImpl implements HomebOrderBuyService {
    private final static Logger LOGGER = LoggerFactory.getLogger(HomebOrderBuyServiceImpl.class);
    @Resource
    private OrderHbpViewDao orderHbpViewDao;
    @Resource
    private ShopPurchasesItemsDao shopPurchasesItemsDao;
    @Resource
    private GoodsSkuCodeDao goodsSkuCodeDao;
    @Resource
    private UserDao userDao;
    @Resource
    private ShopInfoDao shopInfoDao;
    @Resource
    private ShopPurchasesDiscountDao shopPurchasesDiscountDao;
    @Resource
    private CouponInfoDao couponInfoDao;


    @Override
    public HomebOrderBuyInfoVo loadOrderInfo(String id) throws Exception {
        HomebOrderBuyInfoVo vo = new HomebOrderBuyInfoVo();
        // 订单信息
        OrderHbpView view = orderHbpViewDao.getOrderHbpViewByIdFromRedis(id);
        vo.setOrderNo(view.getId());
        vo.setCreateTime(view.getCreateTime());
        vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", view.getStatus()));
        // 商品信息
        List<HomebOrderBuyInfoGoodsVo> vos = null;
        List<ShopPurchasesItems> list = shopPurchasesItemsDao.listShopPurchasesItemsByShopPurchasesIdFromRedis(id);
        if (null != list && list.size() > 0) {
            vos = JSONObject.parseObject(JSONObject.toJSONString(list), new TypeReference<List<HomebOrderBuyInfoGoodsVo>>() {
            });
            vos.forEach(
                    goods -> {
                        String skuId = goods.getSkuId();
                        if (StringUtil.isNotEmpty(skuId)) {
                            GoodsSkuCode skuCode = goodsSkuCodeDao.getGoodsSkuCodeBySkuIdFromRedis(skuId);
                            goods.setCode(skuCode.getCode());
                        }
                        double pomotionPrice = 0;
                        String p1 = goods.getPomotionPrice();
                        if (StringUtil.isNotEmpty(p1)) {
                            pomotionPrice = Double.valueOf(p1);
                        }
                        double paymentPrice = 0;
                        String p2 = goods.getPaymentPrice();
                        if (StringUtil.isNotEmpty(p2)) {
                            paymentPrice = Double.valueOf(p2);
                        }
                        goods.setOldMoney(String.valueOf(pomotionPrice + paymentPrice));
                    }
            );
        }
        vo.setGoods(vos);
        // 支付信息
        vo.setOldMoney(view.getAmount());
        vo.setDenomination(view.getDiscount());
        vo.setPayMoney(view.getActualAmount());
        vo.setPayTime(view.getPayedTime());
        vo.setFreight(view.getDeliveryFee());
        vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", view.getPayedModel()));
        vo.setPaymentNo(view.getTransactionId());
        ShopPurchasesDiscount discount = new ShopPurchasesDiscount();
        discount.setPurchasesId(id);
        discount.setDiscountType(DISCOUNT_TYPE_COUPON.getId());
        List<ShopPurchasesDiscount> shopPurchasesDiscounts = shopPurchasesDiscountDao.listShopPurchasesDiscount(discount);
        if (shopPurchasesDiscounts.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (ShopPurchasesDiscount shopPurchasesDiscount : shopPurchasesDiscounts) {
                String couponId = shopPurchasesDiscount.getShopCouponId();
                if (StringUtil.isNotEmpty(couponId)) {
                    CouponInfo couponInfo = couponInfoDao.getCouponInfoByIdFromRedis(couponId);
                    sb.append(couponInfo.getDiscountRate()).append("<br/>");
                }
            }
            if (sb.length() > 0) {
                vo.setDiscountRate(sb.substring(0, sb.length() - 6).toString());
            }
        }
        // 物流信息
        String logisticsNo = view.getGyLogisticsCode();
        String logisticsCode = view.getLogisticsCode();
        String mobile = view.getMobile();
        if (StringUtil.isNotEmpty(logisticsNo) && StringUtil.isNotEmpty(logisticsCode)) {
            try {
                LogisticsInfo logisticsInfo = kd100Service.getLogisticsInfo(logisticsCode, logisticsNo, mobile);
                vo.setLogisticsNo(logisticsInfo.getLogisticsNo());
                vo.setLogisticsName(logisticsInfo.getLogisticsName());
                vo.setLogisticsStateName(logisticsInfo.getLogisticsStateName());
            } catch (Exception e) {
                LOGGER.error("PurchaseOrderServiceImpl.loadOrderInfo getLogisticsInfo error ", e);
            }
        }
        vo.setReceivingAddress(view.getFullAddress());
        vo.setDistributeTypeName(EnumUtil.getNameById("DistributeType", view.getDeliveryType()));
        // 商家信息
        String userId = view.getUserId();
        if (StringUtil.isNotEmpty(userId)) {
            User user = userDao.getUserByIdFromRedis(userId);
            vo.setUserId(user.getId());
            vo.setUserName(user.getUserName());
            vo.setUserMobile(user.getMobile());
            vo.setUserRegisterTime(user.getCreateTime());
        }
        String shopId = view.getShopId();
        if (StringUtil.isNotEmpty(shopId)) {
            ShopInfo shopInfo = shopInfoDao.getShopInfoByIdFromRedis(shopId);
            vo.setShopName(shopInfo.getShopName());
            vo.setShopAddress(shopInfo.getShopAddress());
        }
        return vo;
    }


    @Override
    public ResponseJson loadOrderDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        OrderHbpView view = JSONObject.toJavaObject(jsonObject, OrderHbpView.class);
        view.setCreateTime(getBetweenSql(jsonObject.getString("createTimeStart"), jsonObject.getString("createTimeEnd")));
        view.setActualAmount(getBetweenSql(jsonObject.getString("orderMoneyStart"), jsonObject.getString("orderMoneyEnd")));
        Datagrid datagrid = orderHbpViewDao.loadDatagridFromMysql(view, page);
        List<HomebOrderBuyDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<HomebOrderBuyDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", vo.getStatus()));
                    vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", vo.getPayedModel()));
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }


    @Override
    public List<HomebOrderBuyDatagridVo> listOrderExcel(JSONObject jsonObject) throws Exception {
        OrderHbpView view = JSONObject.toJavaObject(jsonObject, OrderHbpView.class);
        view.setCreateTime(getBetweenSql(jsonObject.getString("createTimeStart"), jsonObject.getString("createTimeEnd")));
        view.setActualAmount(getBetweenSql(jsonObject.getString("orderMoneyStart"), jsonObject.getString("orderMoneyEnd")));
        List<OrderHbpView> list = orderHbpViewDao.listOrderHbpViewFromMysql(view);
        List<HomebOrderBuyDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(list), new TypeReference<List<HomebOrderBuyDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", vo.getStatus()));
                    vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", vo.getPayedModel()));
                }
        );
        return vos;
    }
}
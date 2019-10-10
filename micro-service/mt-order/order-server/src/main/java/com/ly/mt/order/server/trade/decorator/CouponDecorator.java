package com.ly.mt.order.server.trade.decorator;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.coupon.CouponGoods;
import com.ly.mt.core.base.entity.coupon.CouponInfo;
import com.ly.mt.core.base.entity.coupon.CouponModel;
import com.ly.mt.core.base.entity.goods.GoodsSkuModel;
import com.ly.mt.core.base.entity.trade.OrderModel;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.order.server.coupon.service.CouponCodeService;
import com.ly.mt.order.server.coupon.service.CouponGoodsService;
import com.ly.mt.order.server.coupon.service.CouponInfoService;
import com.ly.mt.order.server.trade.component.PriceComponent;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.ly.mt.core.base.dict.CouponUseScope.COUPON_FOR_ALL_GOODS;
import static com.ly.mt.core.base.dict.CouponUseScope.COUPON_FOR_PART_GOODS;

/**
 * @author zhanglifeng
 * @description 优惠活动-----优惠券的优惠价格计算
 * @date 2019-05-23
 */
public class CouponDecorator extends PriceDecorator {

    private final static Logger LOGGER = LoggerFactory.getLogger(CouponDecorator.class);

    private PriceComponent priceComponent;

    public CouponDecorator(PriceComponent priceComponent) {
        this.priceComponent = priceComponent;
    }

    /**
     * 查看满足该【用户】预订单下【所有商品】的优惠券，放到对应模型OrderModel的couponList中
     * 处理逻辑：券分为两种券。用户领的券和商品本身的券。
     * 1.用户领的券不一定使用与购买的这几个商品。这种券分为两种类型
     * 1.1 全品类的 ，肯定支持购买的商品
     * 1.2 非全品类的，要查询这些领取的couponId查询商品券表coupon_goods支持的商品spuId，如果购买商品的spuId包含在里面，则满足。
     * 2.商品本身的券 这种肯定可以用。根据spuId查询coupon_goods中的couponId.注意这和1.2的券可能重复，通过map，把couponId作为key去重
     * 3.有可能商品支持的券，用户得到已经使用，则要剔除掉用户已经使用的优惠券。
     * <p>
     * 券的使用规则为：当券数量大于1时，用户只能选中优惠券中的一个使用。
     * 券的展示规则为：使用过的不可以使用，但要展示到前台。这里可以根据使用状态来区分
     *
     * @param itemList                        购买的商品集合
     * @param decoratorCommonServiceComponent
     * @return
     */
    @Override
    public OrderModel calcOrderPrice(List<GoodsSkuModel> itemList, DecoratorCommonServiceComponent decoratorCommonServiceComponent, String userId) {
        LOGGER.info("================计算优惠券使用后的订单价格========================");
        List<CouponModel> couponList = new ArrayList<>(1);
        //必须放在第一行，先走父类中的价格计算方法。
        OrderModel orderModel = priceComponent.calcOrderPrice(itemList, decoratorCommonServiceComponent, userId);
        //订单原始金额
        String orderOldMoney = orderModel.getOrderVo().getOrderOldMoney();
        Set set = new HashSet(1);
        for (GoodsSkuModel goodsSkuModel : itemList) {
            set.add(goodsSkuModel.getSpuId());
        }
        LOGGER.info("--------------------------------获取用户id:" + userId);
        Map<String, CouponModel> couponMap = getUserReceiveCoupon(set, decoratorCommonServiceComponent, orderOldMoney, Long.parseLong(userId));
        LOGGER.info("--------------------------------查询处用户领取可用的优惠券集合：" + JSONObject.toJSONString(couponMap));
        //2.查询商品对应的限定商品优惠券（普通优惠券coupon_type=30）
        CouponGoodsService couponGoodsService = decoratorCommonServiceComponent.getCouponGoodsService();
        List<CouponGoods> couponGoodsList = couponGoodsService.selectByCouponIdAndSpuIds(new ArrayList(set), null);
        List<CouponInfo> coupons = null;
        List<String> couponIdList = null;
        if (couponGoodsList != null && couponGoodsList.size() > 0) {
            couponIdList = new ArrayList<>(couponGoodsList.size());
            for (CouponGoods couponGoods : couponGoodsList) {
                couponIdList.add(couponGoods.getCouponId());
            }
        }
        //查询商品对应的全品类的优惠券（普通优惠券coupon_type=30）
        CouponInfoService couponInfoService = decoratorCommonServiceComponent.getCouponInfoService();
        List<String> couponIdsByLimitType = couponInfoService.getCouponIdsByLimitType(DateUtil.getNowTimeStr());
        if (couponIdsByLimitType != null && couponIdsByLimitType.size() > 0) {
            if (couponIdList != null && couponIdList.size() > 0) {
                couponIdsByLimitType.addAll(couponIdList);
            }
        } else {
            couponIdsByLimitType = couponIdList;
        }
        if (couponIdsByLimitType != null && couponIdsByLimitType.size() > 0) {
            coupons = couponInfoService.getValidCouponListByIds(couponIdsByLimitType);
        }
        LOGGER.info("--------------------------------查询在有效期内的优惠券coupons：" + JSONObject.toJSONString(coupons));
        if (coupons != null && coupons.size() > 0) {
            CouponModel couponModel = null;
            for (CouponInfo couponInfo : coupons) {
                couponModel = new CouponModel();
                BeanUtils.copyProperties(couponInfo, couponModel);
                couponModel.setCouponId(couponInfo.getId());
                couponMap.put(couponInfo.getId(), couponModel);
            }
        }
        CouponCodeService couponCodeService = decoratorCommonServiceComponent.getCouponCodeService();
        List<String> list = couponCodeService.selectUsedCouponIdByUserId(Long.parseLong(userId));
        LOGGER.info("-------------------------------查询出用户已经使用过得优惠券id集合：" + JSONObject.toJSONString(list));
        Iterator<Map.Entry<String, CouponModel>> iterator = couponMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, CouponModel> entry = iterator.next();
            String couponId = entry.getKey();
            //剔除掉已使用的优惠券
            if (!list.contains(couponId)) {
                String startFee = entry.getValue().getStartFee();
                if ("null".equals(startFee) || StringUtils.isEmpty(startFee)) {
                    //说明没有最小金额限制。
                } else {
                    if (new BigDecimal(orderOldMoney).compareTo(new BigDecimal(startFee)) >= 0) {
                        //说明优惠券使用满足最小条件,剩下五折券优惠券的使用限制
                        if ("604336790610325462".equals(couponId) || "604336790610325461".equals(couponId)) {
                            LOGGER.info("---------------------查询的券中包含五折券-------------------------");
                            if (itemList.size() == 1 && Integer.parseInt(itemList.get(0).getSkuNum()) == 1) {
                                couponList.add(entry.getValue());
                            }
                        } else {
                            couponList.add(entry.getValue());
                        }
                    }
                }
            }
        }
        //对优惠券按照优惠券金额排序
        Collections.sort(couponList, new Comparator<CouponModel>() {
            @Override
            public int compare(CouponModel o1, CouponModel o2) {
                Double price1 = Double.valueOf(o1.getDenomination() == null ? "0.0" : o1.getDenomination());
                Double price2 = Double.valueOf(o2.getDenomination() == null ? "0.0" : o2.getDenomination());
                if (price1 - price2 > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        orderModel.setCouponList(couponList);
        return orderModel;
    }

    /**
     * 查询用户领取的券
     *
     * @param decoratorCommonServiceComponent
     * @param orderOldMoney
     * @return
     */
    private Map<String, CouponModel> getUserReceiveCoupon(Set set, DecoratorCommonServiceComponent decoratorCommonServiceComponent, String orderOldMoney, Long uid) {
        Map<String, CouponModel> couponModelMap = new ConcurrentHashMap<>(16);
        CouponCodeService couponCodeService = decoratorCommonServiceComponent.getCouponCodeService();
        //得到用户领取的未使用的券集合
        List<String> couponIdList = couponCodeService.selectNotUsedCouponIdByUserId(uid);
        if (couponIdList != null && couponIdList.size() > 0) {
            CouponInfoService couponInfoService = decoratorCommonServiceComponent.getCouponInfoService();
            //从用户的券中筛选出当前时间还有效的优惠券
            List<CouponInfo> coupons = couponInfoService.getValidCouponListFromCouponCodeByIds(couponIdList, uid);
            LOGGER.info("----------用户领取的未使用的券集合----------:{}", JSONObject.toJSONString(couponIdList));
            LOGGER.info("----------从用户的券中筛选出当前时间还有效的优惠券----------:{}", JSONObject.toJSONString(coupons));
            CouponModel couponMode = null;
            for (CouponInfo couponInfo : coupons) {
                couponMode = new CouponModel();
                //先判断优惠券是否有最小订单金额限制，如果有则优先考虑这个。
                String startFee = couponInfo.getStartFee();
                if ("null".equals(startFee) || StringUtils.isEmpty(startFee)) {
                    //说明没有最小金额限制。
                } else {
                    if (new BigDecimal(orderOldMoney).compareTo(new BigDecimal(startFee)) < 0) {
                        //说明不满足最小金额的条件，跳过本次循环
                        continue;
                    }
                }
                //优惠类型
                String limitType = couponInfo.getLimitType();
                if (COUPON_FOR_ALL_GOODS.getId().equals(limitType)) {
                    //1：全品类
                    BeanUtils.copyProperties(couponInfo, couponMode);
                    couponMode.setCouponId(couponInfo.getId());
                    couponModelMap.put(couponInfo.getId(), couponMode);
                }
                if (COUPON_FOR_PART_GOODS.getId().equals(limitType)) {
                    // 2：限定商品
                    CouponGoodsService couponGoodsService = decoratorCommonServiceComponent.getCouponGoodsService();
                    List<CouponGoods> couponGoods = couponGoodsService.selectByCouponIdAndSpuIds(new ArrayList(set), couponInfo.getId());
                    if (couponGoods != null && couponGoods.size() > 0) {
                        BeanUtils.copyProperties(couponInfo, couponMode);
                        couponMode.setCouponId(couponInfo.getId());
                        couponModelMap.put(couponInfo.getId(), couponMode);
                    }
                }
            }
        }
        return couponModelMap;
    }
}

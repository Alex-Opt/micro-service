package com.ly.mt.order.server.trade.mapper;


import com.ly.mt.core.base.entity.activity.ActivityInfo;
import com.ly.mt.core.base.entity.border.OrdersBattleInfo;
import com.ly.mt.core.base.entity.coupon.CouponInfo;
import com.ly.mt.core.base.entity.delivery.ExpressCompanyInfo;
import com.ly.mt.core.base.entity.goods.GoodsSkuAttr;
import com.ly.mt.core.base.entity.goods.GoodsSkuPrice;
import com.ly.mt.core.base.entity.trade.*;
import com.ly.mt.core.base.entity.user.User;
import com.ly.mt.core.base.entity.user.UserAddress;
import com.ly.mt.core.base.entity.user.UserAddressVo;
import com.ly.mt.core.base.entity.user.UserMH5RegistVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单主表持久层
 *
 * @Author zhanglifeng
 * @Description
 * @Date 2019-05-21
 */
@Mapper
public interface TradeOrdersServiceMapper {

    /**
     * 按非空参数新增一条订单主表信息
     *
     * @param record
     * @return
     */
    int insert(TradeOrder record);

    /**
     * 按主键查询一条订单信息
     *
     * @param id
     * @return
     * @Author zhanglifeng
     * @Date 2019-05-21
     */
    TradeOrder selectByPrimaryKey(@Param("id") Long id);

    /**
     * 根据订单编号查询订单
     *
     * @param orderNo
     * @return
     */
    List<TradeOrder> selectByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 按照非空入参更新一条记录
     *
     * @param record
     * @return
     * @Author zhanglifeng
     * @Date 2019-05-21
     */
    int updateByPrimaryKey(TradeOrder record);

    /**
     * 查询用户的订单列表
     *
     * @param userId  用户id即订单表的买家Id: buyer_id
     * @param orderYn 是否取消状态  非必传
     * @return
     * @Author zhanglifeng
     * @Date 2019-05-21
     */
    List<TradeOrder> queryListByUserId(@Param("userId") Long userId, @Param("orderYn") Integer orderYn);

    /**
     * 根据商品的sku_id查询商品表，得到商品的url
     *
     * @param skuId
     * @return
     */
    String getPictureUrlBySkuId(@Param("skuId") Long skuId);

    /**
     * 根据订单主表id查询订单明细表查询商品信息
     *
     * @param orderId
     * @return
     */
    List<TradeOrderItem> getTradeOrderItemByOrderId(@Param("orderId") Long orderId);


    /**
     * 根据id查询一条收货地址
     *
     * @param id
     * @return
     */
    UserAddress selectByAddressId(@Param("id") Long id);

    /**
     * 根据订单查询该订单使用的优惠信息
     *
     * @param orderId
     * @return
     */
    List<TradeOrderCoupon> selectCouponInfoByOrderId(@Param("orderId") Long orderId);

    /**
     * 根据优惠活动id查询优惠活动信息
     *
     * @param id
     * @return
     */
    ActivityInfo getActivityInfoById(@Param("id") Long id);


    /**
     * 根据conponId的集合查询出优惠券集合
     *
     * @param list
     * @return
     */
    List<CouponInfo> batchQueryByCouponId(@Param("list") List<String> list, @Param("nowTime") String nowTime);


    /**
     * 根据activityId的集合查询出优惠券集合
     *
     * @param list
     * @return
     */
    List<ActivityInfo> batchQueryByActivityId(@Param("list") List<String> list, @Param("nowTime") String nowTime);


    /**
     * 根据促销活动id集合查询促销活动实体集合
     *
     * @param idList
     * @return
     */
    List<ActivityInfo> listActivityByIds(@Param("idList") List<Long> idList);

    /**
     * 根据优惠券id获取一优惠券
     *
     * @param couponId
     * @return
     */
    CouponInfo selectCouponInfoByPrimaryKey(Long couponId);

    /**
     * 根据主键查询配送费用信息
     *
     * @param distributionId
     * @return
     */
    TradeDistributionMode selectDistributionModeByPrimaryKey(Long distributionId);

    /**
     * 批量插入订单明细表
     *
     * @param list
     * @return
     */
    int batchInsertOrderItems(@Param("list") List<TradeOrderItem> list);

    /**
     * 批量插入订单优惠表
     *
     * @param list
     * @return
     */
    int batchInsertTradeOrderCoupon(@Param("list") List<TradeOrderCoupon> list);

    /**
     * 更新订单状态为1-未取消的状态，且待付款-10的订单为已取消-2。
     *
     * @param orderId
     * @return
     */
    int cancleOrder(@Param("orderId") String orderId, @Param("cancleTime") String cancleTime);

    /**
     * 根据订单号更新订单的发货信息，订单状态
     *
     * @param tradeOrder
     * @return
     */
    int updateOrderDeliveryInfoByOrderId(TradeOrder tradeOrder);


    /**
     * 新增一条收货地址
     *
     * @param record
     * @return
     */
    int insertAddress(UserAddress record);

    int updateAddress(UserAddress record);

    List<UserAddressVo> getAddress(@Param("userId") Long userId);

    UserMH5RegistVo getUser(UserMH5RegistVo user);

    /**
     * 根据活动id和商品spuId查询符合活动指定的商品spuId。
     *
     * @param activityId
     * @param list
     * @return
     */
    List<String> getSpuIdBySpuIdsAndActivityId(@Param("activityId") Long activityId, @Param("list") List list);

    /**
     * 根据优惠券id和商品spuId查询符合指定范围内的商品spuId集合
     *
     * @param couponId
     * @param list
     * @return
     */
    List<String> getSpuIdsByCouponIdAndSpuIds(@Param("couponId") Long couponId, @Param("list") List list);


    /**
     * 插入活动页面订单
     *
     * @return
     */
    int insertActivityOrder(TradeOrder record);

    /**
     * 根据skuId 查询价格
     */
    GoodsSkuPrice selectSkuPriceBySkuId(@Param("skuId") Long skuId);


    int insertOrderItem(TradeOrderItem item);

    TradeOrderItem selectSpuNameBySpuId(@Param("spuId") Long spuId);

    /**
     * 根据orderId查询抢单信息
     *
     * @param orderId
     * @return
     */
    OrdersBattleInfo getBattleInfoByOrderId(@Param("orderId") String orderId);

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    User getUserInfoById(Long id);

    /**
     * 更新申请退货状态
     *
     * @param map
     * @return
     */
    int updateTradeOrderRefundType(Map<String, Object> map);


    /**
     * 根据商品skuID查询商品属性
     */

    GoodsSkuAttr selectSkuAttr(@Param("skuId") Long skuId);

    /**
     * 根据属性Id查询属性值
     *
     * @param list
     * @return
     */
    List<Map> selectGoodsAttrValueListByAttrId(@Param("list") List list);

    /**
     * 获取商品的月销量数据
     *
     * @param skuId
     * @param startTime
     * @param endTime
     * @return
     */
    String getSkuMonthSellerNumber(@Param("skuId") Long skuId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询订单使用的优惠券列表
     *
     * @param orderId
     * @return
     */
    List<String> getCouponListByOrderId(Long orderId);

    /**
     * 更新用户使用订单的优惠券状态为未使用
     *
     * @param userId
     * @param couponList
     * @return
     */
    int updateCouponCodeUserStatusByCouponIdAndUserId(@Param("userId") Long userId, @Param("list") List couponList);

    /**
     * 根据城市名称查询数据存在否
     *
     * @param cityName
     * @return
     */
    String getHourCitByCityName(String cityName);

    /**
     * 获取当前用户购买的蓝牙产品数量
     *
     * @param userId
     * @return
     */
    int getBlueToothCount(@Param("userId") Long userId);

    /**
     * 根据订单id查询抢单信息。
     *
     * @param orderId
     * @return
     */
    OrdersBattleInfo getOrderBattleByOrderId(Long orderId);

    /**
     * 通过管易系统返回的快递code获取快递100的code
     *
     * @param code
     * @return
     */
    Map getKD100ExpressCodeByGyExpressCode(String code);

    /**
     * 保存订单的渠道来源等媒体信息
     * @param tradeOrderMedia
     * @return
     */
    int saveTradeMediaInfo(TradeOrderMedia tradeOrderMedia);

    /**
     * 根据code查询物流公司信息
     * @param code
     * @return
     */
    ExpressCompanyInfo getExpressCompanyInfoByCode(String code);

}
package com.ly.mt.order.server.mq.mapper;

import com.ly.mt.core.base.entity.border.OrderBattleExpresses;
import com.ly.mt.core.base.entity.border.OrderBattleShop;
import com.ly.mt.core.base.entity.border.OrdersBattleInfo;
import com.ly.mt.core.base.entity.shop.ShopAddress;
import com.ly.mt.core.base.entity.shop.ShopInfo;
import com.ly.mt.core.base.entity.trade.TradeOrder;
import com.ly.mt.core.base.entity.trade.TradeOrderItem;
import com.ly.mt.core.base.entity.user.User;
import com.ly.mt.core.base.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 处理一小时达订单
 *
 * @author zhanglifeng
 * @date 2019-06-26
 */
@Mapper
public interface RabbitMqServiceMapper {
    /**
     * 根据id查询一条一小时达的订单
     *
     * @param id
     * @return
     */
    TradeOrder getTradeOrderById(Long id);

    /**
     * 根据订单编号查询查询一小时达订单
     *
     * @param orderNo        订单编号
     * @param distributionId 配送方式Id
     * @return
     */
    List<TradeOrder> getTradeOrderByOrderNo(@Param("orderNo") Long orderNo, @Param("distributionId") Long distributionId);


    /**
     * 插入发货单
     *
     * @param ordersBattleInfo
     * @return
     * @author zhanglifeng
     */
    int insertOrderBattle(OrdersBattleInfo ordersBattleInfo);

    /**
     * 获取下单的买家经纬度信息
     *
     * @param id
     * @return
     * @author zhanglifeng
     */
    UserAddress getBuyerAddressById(Long id);

    /**
     * 查询在经纬度范围内的小B或者兜底商家集合
     *
     * @param minLon 经度1
     * @param maxLon 经度2
     * @param minLat 维度1
     * @param maxLat 维度2
     * @return
     * @author zhanglifeng
     */
    List<ShopAddress> getShopAddressByLonAndLatRange(@Param("minLon") String minLon, @Param("maxLon") String maxLon, @Param("minLat") String minLat, @Param("maxLat") String maxLat);

    /**
     * 根据经纬度查询在五公里范围内的兜底商家信息。
     *
     * @param minLon
     * @param maxLon
     * @param minLat
     * @param maxLat
     * @return
     */
    List<ShopInfo> getShopInfoByLonAndLatRange(@Param("minLon") String minLon, @Param("maxLon") String maxLon, @Param("minLat") String minLat, @Param("maxLat") String maxLat);

    /**
     * 获取黑名单的商家小B
     *
     * @return
     */
    List<String> getBlackList();

    /**
     * 插入符合范围的抢单的小B
     *
     * @param list
     * @return
     */
    int batchInsertOrderBattleShop(@Param("list") List<OrderBattleShop> list);


    /**
     * 查询一笔订单下所有符合条件的小B的clientId
     *
     * @param list
     * @return
     */
    List<String> getClientIdByUserId(@Param("list") List<OrderBattleShop> list);

    /**
     * 更新订单的状态为推送成功。
     *
     * @param list
     * @return
     */
    int updateOrderPushStatusById(@Param("list") List<String> list);

    /**
     * 根据orderId查询抢单信息
     *
     * @param orderId
     * @return
     */
    OrdersBattleInfo getBattleInfoByOrderId(@Param("orderId") String orderId);

    /**
     * 查询userId对应的客户号
     *
     * @param userId
     * @return
     */
    String getClientIdById(Long userId);

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param finishTime
     * @return
     */
    int updateOrderStatusById(@Param("orderId") String orderId, @Param("finishTime") String finishTime, @Param("status") Integer status);

    /**
     * 蜂鸟回调接口更新抢单状态
     *
     * @param ordersBattleInfo
     * @return
     */
    int updateBattleOrderDeliveryStatus(OrdersBattleInfo ordersBattleInfo);


    /**
     * 新增订单快递信息
     *
     * @return
     */
    int insertOrderBattleExpress(OrderBattleExpresses orderBattleExpresses);

    /**
     * 据订单号id查询对应订单的收货地址信息
     *
     * @param orderId
     * @return
     */
    UserAddress getAddressByOrderId(@Param("orderId") Long orderId);


    /**
     * 更新抢单状态
     *
     * @param ordersBattleInfo
     * @return
     */
    int updateBattleInfoById(OrdersBattleInfo ordersBattleInfo);

    /**
     * 将兜底商家的shopId更新进订单表。
     *
     * @param shopId
     * @param id
     * @return
     */
    int updateShopIdIntoTradeOrders(@Param("shopId") Long shopId, @Param("sellerId") Long sellerId, @Param("id") Long id);

    /**
     * 根据订单id获取订单号
     *
     * @param orderId
     * @return
     */
    String getOrderNoByOrderId(Long orderId);

    /**
     * 根据订单编号获取订单id
     *
     * @param orderNo
     * @return
     */
    String getOrderIdByOrderNo(Long orderNo);

    /**
     * 将一小时达订单更新为普通订单信息。
     *
     * @param tradeOrder
     * @return
     */
    int updateTradeOrdersDefaultShopInfoById(TradeOrder tradeOrder);

    /**
     * 根据orderId查询出收货地址，由收货地址得到对应收货地址的省份，从而得到该省份的兜底商家。
     *
     * @param orderId
     * @return
     */
    List<ShopInfo> getShopDefaultByOrderId(Long orderId);

    /**
     * 更新一小时达订单的快递方式为次日达,已同意
     *
     * @param orderId
     * @return
     */
    int updateTradeOrdersDeliveryTypeById(Long orderId);

    /**
     * 更新订单的状态
     *
     * @param orderNo
     * @param status
     * @param paymentType
     * @return
     */
    int updateTradeOrderByOrderNo(@Param("orderNo") Long orderNo, @Param("status") Integer status, @Param("paymentType") String paymentType);

    /**
     * 查询订单买家信息
     *
     * @param orderId
     * @return
     */
    User getBuyerInfoByOrderId(Long orderId);

    /**
     * 根据订单id查询商品购买件数
     *
     * @param orderId
     * @return
     */
    String getTradeOrderItemsByOrderId(Long orderId);

    /**
     * 根据订单主表id查询订单明细表查询商品信息
     *
     * @param orderId
     * @return
     */
    List<TradeOrderItem> getTradeOrderItemByOrderId(@Param("orderId") Long orderId);
}

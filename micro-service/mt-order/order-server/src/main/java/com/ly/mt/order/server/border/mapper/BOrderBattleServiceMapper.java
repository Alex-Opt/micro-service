package com.ly.mt.order.server.border.mapper;

import com.ly.mt.core.base.entity.border.*;
import com.ly.mt.core.base.entity.goods.GoodsSkuCode;
import com.ly.mt.core.base.entity.goods.GoodsSkuInfo;
import com.ly.mt.core.base.entity.shop.ShopAddress;
import com.ly.mt.core.base.entity.shop.ShopProfitLogs;
import com.ly.mt.core.base.entity.shop.ShopProfitsForBattleOrder;
import com.ly.mt.core.base.entity.shop.ShopStocks;
import com.ly.mt.core.base.entity.trade.TradeOrder;
import com.ly.mt.core.base.entity.trade.TradeOrderItem;
import com.ly.mt.core.base.entity.user.User;
import com.ly.mt.core.base.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 抢单dao层
 *
 * @author zhanglifeng
 * @date 2019-06-14
 */
@Mapper
public interface BOrderBattleServiceMapper {
    /**
     * 根据小B的userId,shopId 查询出所有的可以抢的单子集合
     *
     * @param userId
     * @param shopId
     * @return
     */
    List<OrderBattleShop> getOrdeBattleShopByUserIdAndShopId(@Param("userId") Long userId, @Param("shopId") Long shopId);

    /**
     * 根据订单Id查询订单信息
     *
     * @param id
     * @return
     */
    TradeOrder getTradeOrdersById(Long id);

    /**
     * 根据订单id查询订单明细表
     *
     * @param orderId
     * @return
     */
    List<TradeOrderItem> getTradeOrderItemByOrderId(Long orderId);

    /**
     * 获取买家的地址经纬度
     *
     * @param addressId
     * @return
     */
    UserAddress getBuyerAddressById(Long addressId);

    /**
     * 获取卖家的地址经纬度
     *
     * @param userId
     * @param shopId
     * @return
     */
    ShopAddress getShopInfoByUserInfo(@Param("userId") Long userId, @Param("shopId") Long shopId);


    /**
     * 更新抢单状态
     *
     * @param orderBattleShop
     * @return
     */
    int updateOrderBattleStatus(OrderBattleShop orderBattleShop);

    /**
     * 根据主键更新抢单信息
     *
     * @param ordersBattleInfo
     * @return
     */
    int updateBattleInfoById(OrdersBattleInfo ordersBattleInfo);

    /**
     * 小B抢到单后，将小B的shopId更新进订单表。
     *
     * @param shopId
     * @param id
     * @return
     */
    int updateShopIdIntoTradeOrders(@Param("shopId") Long shopId,@Param("sellerId") Long sellerId, @Param("id") Long id);


    /**
     * 插入抢单日志信息
     *
     * @param orderBattleLogs
     * @return
     */
    int insertOrderBattleLogs(OrderBattleLogs orderBattleLogs);

    /**
     * 根据id查询出抢单次数
     *
     * @param id
     * @return
     */
    OrdersBattleInfo getGrabNumById(@Param("id") Long id);

    /**
     * 更新小b列表中的抢单状态
     *
     * @param ordersBattleShop
     * @return
     */
    int updateBattleOrderRelease(OrderBattleShop ordersBattleShop);

    /**
     * 查询小B针对抢的一单的验货日志集合
     *
     * @return
     */
    List<OrderBattleCheckLogs> getShopCheckLogs(@Param("ordersBattleId") Long ordersBattleId, @Param("shopId") Long shopId);

    /**
     * 更新小B的库存代发货数量
     *
     * @param shopStocks
     * @return
     */
    int updateStockDeliveryNums(ShopStocks shopStocks);

    /**
     * 根据商品的sku_id查询商品表，得到商品的url
     *
     * @param skuId
     * @return
     */
    String getPictureUrlBySkuId(@Param("skuId") Long skuId);

    /**
     * 更新订单状态为取消
     *
     * @param id
     * @param time
     * @return
     */
    int cancleOrderByOrderId(@Param("id") Long id, @Param("time") String time);

    /**
     * 取消抢单表
     *
     * @param id
     * @param time
     * @return
     */
    int updateBattleOrderStatusById(@Param("id") Long id, @Param("time") String time, @Param("status") String status);

    /**
     * 据订单号查询商品信息
     *
     * @param orderId
     * @return
     */
    List<TradeOrderItem> getOrderItemsByOrderId(Long orderId);

    /**
     * 根据code商品唯一码查询商品的信息
     *
     * @param code
     * @return
     */
    GoodsSkuCode getGoodsSkuCodeInfoByCode(@Param("code") String code);

    /**
     * 根据bar_code商品条码查询商品的信息
     *
     * @param barCode
     * @return
     */
    GoodsSkuInfo getGoodsSkuInfoByBarCode(@Param("barCode") String barCode);

    /**
     * 更新商品的校验次数
     *
     * @param code
     * @param modifyTime
     * @return
     */
    int updateGoodsSkuCodeCheckTimes(@Param("code") String code, @Param("modifyTime") String modifyTime);

    /**
     * 蜂鸟回调接口更新抢单状态
     *
     * @param ordersBattleInfo
     * @return
     */
    int updateBattleOrderDeliveryStatus(OrdersBattleInfo ordersBattleInfo);

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param finishTime
     * @return
     */
    int updateOrderStatusById(@Param("orderId") String orderId, @Param("finishTime") String finishTime, @Param("status") Integer status);

    /**
     * 新增商家捡货日志
     *
     * @param orderBattleCheckLogs
     * @return
     */
    int insertOrderBattleCheckLogs(OrderBattleCheckLogs orderBattleCheckLogs);

    /**
     * 根据shopId和skuId更新代发货数量
     *
     * @param shopId
     * @param skuId
     * @param modifyTime
     * @return
     */
    int updateGoodsSocksByShopIdAndSkuId(@Param("shopId") Long shopId, @Param("skuId") Long skuId, @Param("modifyTime") String modifyTime);

    /**
     * 校验成功，扣减库存。
     *
     * @param nums
     * @param shopId
     * @param skuId
     * @param modifyTime
     * @return
     */
    int deductionShopGoodsStocks(@Param("nums") Integer nums, @Param("shopId") Long shopId, @Param("skuId") Long skuId, @Param("modifyTime") String modifyTime);

    /**
     * 释放单子，要恢复已扣减的库存和增加的销量。
     *
     * @param nums
     * @param shopId
     * @param skuId
     * @param modifyTime
     * @return
     */
    int recoverShopGoodsStocks(@Param("nums") Integer nums, @Param("shopId") Long shopId, @Param("skuId") Long skuId, @Param("modifyTime") String modifyTime);

    /**
     * 根据用户id查询用户的绑定信息
     *
     * @param userId
     * @return
     */
    User getUserInfoById(@Param("userId") Long userId);

    /**
     * 确认收货后增加小B的收益日志
     *
     * @return
     */
    int insertShopProfitLogs(ShopProfitLogs shopProfitLogs);

    /**
     * 如果该用户尚没有收益记录，则新增一条抢单收益的信息
     *
     * @param shopProfitsForBattleOrder
     * @return
     */
    int insertShopProfits(ShopProfitsForBattleOrder shopProfitsForBattleOrder);

    /**
     * 如果该用户尚有收益记录，则更新一条抢单收益的信息
     *
     * @param shopProfitsForBattleOrder
     * @return
     */
    int updateShopProfitsByPrimaryKey(ShopProfitsForBattleOrder shopProfitsForBattleOrder);

    /**
     * 查询用户收益存不存在
     *
     * @param userId
     * @param shopId
     * @return
     */
    ShopProfitsForBattleOrder getShopProfitsByUserIdAndShopId(@Param("userId") String userId, @Param("shopId") String shopId);

    /**
     * 根据orderId查询抢单信息
     *
     * @param orderId
     * @return
     */
    List<OrdersBattleInfo> getBattleInfoByOrderId(@Param("orderId") String orderId);

    /**
     * 新增订单快递信息
     *
     * @return
     */
    int insertOrderBattleExpress(OrderBattleExpresses orderBattleExpresses);

    /**
     * 更新订单的快递信息
     *
     * @return
     */
    int updateByPrimaryKeySelective(OrderBattleExpresses orderBattleExpresses);

    /**
     * 根据订单id获取订单号
     *
     * @param orderId
     * @return
     */
    String getOrderNoByOrderId(Long orderId);


}

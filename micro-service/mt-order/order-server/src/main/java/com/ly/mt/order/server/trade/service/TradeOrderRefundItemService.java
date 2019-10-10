package com.ly.mt.order.server.trade.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.trade.TradeOrderRefundItem;

import java.util.List;

/**
 * 退货详情 服务层
 *
 * @author 484876123@qq.com
 * @date 2019-06-27
 */
public interface TradeOrderRefundItemService {
    /**
     * 查询退货详情信息
     *
     * @param id 退货详情ID
     * @return 退货详情信息
     */
    public TradeOrderRefundItem selectTradeOrderRefundItemById(Long id);

    /**
     * 查询退货详情列表
     *
     * @param tradeOrderRefundItem 退货详情信息
     * @return 退货详情集合
     */
    public List<TradeOrderRefundItem> selectTradeOrderRefundItemList(TradeOrderRefundItem tradeOrderRefundItem);

    /**
     * 新增退货详情
     *
     * @param tradeOrderRefundItem 退货详情信息
     * @return 结果
     */
    public int insertTradeOrderRefundItem(TradeOrderRefundItem tradeOrderRefundItem);

    /**
     * 修改退货详情
     *
     * @param tradeOrderRefundItem 退货详情信息
     * @return 结果
     */
    public int updateTradeOrderRefundItem(TradeOrderRefundItem tradeOrderRefundItem);

    public List<TradeOrderRefundItem> selectTradeOrderRefundItemByOrderId(Long orderId);

    public List<TradeOrderRefundItem> selectTradeOrderRefundItemByOrderIdAndSkuId(JSONObject jsonObject);

    public int insertByBatchTradeOrderRefundItem(List<TradeOrderRefundItem> list);

    public int updateTradeOrderRefundItemStatus(TradeOrderRefundItem tradeOrderRefundItem);

    public List<TradeOrderRefundItem> getRerundid(Long refundId);

}

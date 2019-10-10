package com.ly.mt.order.server.trade.mapper;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.trade.TradeOrderRefundItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 退货详情 数据层
 *
 * @author ruoyi
 * @date 2019-06-27
 */

@Mapper
public interface TradeOrderRefundItemMapper {
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

    /**
     * 删除退货详情
     *
     * @param id 退货详情ID
     * @return 结果
     */
    public int deleteTradeOrderRefundItemById(Long id);

    public List<TradeOrderRefundItem> selectTradeOrderRefundItemByOrderId(Long orderId);

    public List<TradeOrderRefundItem> selectTradeOrderRefundItemByOrderIdAndSkuId(JSONObject jsonObject);

    /**
     * 批量新增详情信息
     *
     * @param list
     * @return
     */
    public int insertByBatchTradeOrderRefundItem(List<TradeOrderRefundItem> list);


    /**
     * 更新退货单详情状态
     *
     * @param jsonObject
     */
    public int updateTradeOrderRefundItemStatus(TradeOrderRefundItem jsonObject);

    public List<TradeOrderRefundItem> getRerundid(Long refundId);

}
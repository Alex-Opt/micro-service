package com.ly.mt.order.server.trade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.trade.TradeOrderRefundItem;
import com.ly.mt.order.server.trade.mapper.TradeOrderRefundItemMapper;
import com.ly.mt.order.server.trade.service.TradeOrderRefundItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 退货详情 服务层实现
 *
 * @author 484876123@qq.com
 * @date 2019-06-27
 */
@Service
public class TradeOrderRefundItemServiceImpl implements TradeOrderRefundItemService {
    @Resource
    private TradeOrderRefundItemMapper tradeOrderRefundItemMapper;

    /**
     * 查询退货详情信息
     *
     * @param id 退货详情ID
     * @return 退货详情信息
     */
    @Override
    public TradeOrderRefundItem selectTradeOrderRefundItemById(Long id) {
        return tradeOrderRefundItemMapper.selectTradeOrderRefundItemById(id);
    }

    /**
     * 查询退货详情列表
     *
     * @param tradeOrderRefundItem 退货详情信息
     * @return 退货详情集合
     */
    @Override
    public List<TradeOrderRefundItem> selectTradeOrderRefundItemList(TradeOrderRefundItem tradeOrderRefundItem) {
        return tradeOrderRefundItemMapper.selectTradeOrderRefundItemList(tradeOrderRefundItem);
    }

    /**
     * 新增退货详情
     *
     * @param tradeOrderRefundItem 退货详情信息
     * @return 结果
     */
    @Override
    public int insertTradeOrderRefundItem(TradeOrderRefundItem tradeOrderRefundItem) {
        return tradeOrderRefundItemMapper.insertTradeOrderRefundItem(tradeOrderRefundItem);
    }

    /**
     * 修改退货详情
     *
     * @param tradeOrderRefundItem 退货详情信息
     * @return 结果
     */
    @Override
    public int updateTradeOrderRefundItem(TradeOrderRefundItem tradeOrderRefundItem) {
        return tradeOrderRefundItemMapper.updateTradeOrderRefundItem(tradeOrderRefundItem);
    }


    /**
     * 根据订单id查询退款单列表
     *
     * @param orderId
     * @return
     */
    @Override
    public List<TradeOrderRefundItem> selectTradeOrderRefundItemByOrderId(Long orderId) {
        return tradeOrderRefundItemMapper.selectTradeOrderRefundItemByOrderId(orderId);
    }

    @Override
    public List<TradeOrderRefundItem> selectTradeOrderRefundItemByOrderIdAndSkuId(JSONObject jsonObject) {
        return tradeOrderRefundItemMapper.selectTradeOrderRefundItemByOrderIdAndSkuId(jsonObject);
    }

    @Override
    public int insertByBatchTradeOrderRefundItem(List<TradeOrderRefundItem> list) {
        return tradeOrderRefundItemMapper.insertByBatchTradeOrderRefundItem(list);
    }

    @Override
    public int updateTradeOrderRefundItemStatus(TradeOrderRefundItem tradeOrderRefundItem) {
        return tradeOrderRefundItemMapper.updateTradeOrderRefundItemStatus(tradeOrderRefundItem);
    }

    @Override
    public List<TradeOrderRefundItem> getRerundid(Long refundId) {
        return tradeOrderRefundItemMapper.getRerundid(refundId);
    }
}

package com.ly.mt.order.server.trade.mapper;


import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.trade.TradeOrderRefundInfo;
import com.ly.mt.core.base.entity.trade.TradeOrderRefundInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单退货 数据层
 *
 * @author ruoyi
 * @date 2019-06-25
 */
@Mapper
public interface TradeOrderRefundMapper {
    /**
     * 查询订单退货信息
     *
     * @param id 订单退货ID
     * @return 订单退货信息
     */
    public TradeOrderRefundInfo selectTradeOrderRefundById(Long id);

    /**
     * 查询订单退货列表
     *
     * @param tradeOrderRefund 订单退货信息
     * @return 订单退货集合
     */
    public List<TradeOrderRefundInfo> selectTradeOrderRefundList(TradeOrderRefundInfo tradeOrderRefund);

    /**
     * 新增订单退货
     *
     * @param tradeOrderRefund 订单退货信息
     * @return 结果
     */
    public int insertTradeOrderRefund(TradeOrderRefundInfo tradeOrderRefund);

    /**
     * 修改订单退货
     *
     * @param tradeOrderRefund 订单退货信息
     * @return 结果
     */
    public int updateTradeOrderRefund(TradeOrderRefundInfo tradeOrderRefund);

    /**
     * 删除订单退货
     *
     * @param id 订单退货ID
     * @return 结果
     */
    public int deleteTradeOrderRefundById(Long id);

    /**
     * 批量删除订单退货
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTradeOrderRefundByIds(String[] ids);

    /**
     * 根据买家Id获取退货列表 分页
     *
     * @param buyerId
     * @return
     */
    public List<TradeOrderRefundInfoVo> getBuyerRefunds(Long buyerId);

    /**
     * 根据id更新状态
     *
     * @param json
     */
    public void updateRefundStatus(JSONObject json);

    /**
     * 更具Id更新退货单信息
     *
     * @param json
     */
    public void updateRefundInfo(TradeOrderRefundInfo json);

    /**
     * 更新取消退货单状态
     *
     * @param json
     */
    public void updateRefundCancel(JSONObject json);

    /**
     * 根据状态id与状态查询退款单列表
     *
     * @param jsonObject
     * @return
     */
    public List<TradeOrderRefundInfo> getByOrderIdAndStatus(TradeOrderRefundInfo jsonObject);

}
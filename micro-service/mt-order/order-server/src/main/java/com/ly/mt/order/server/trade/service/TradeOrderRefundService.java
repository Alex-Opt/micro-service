package com.ly.mt.order.server.trade.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.trade.TradeOrderRefundInfo;

import java.util.List;

/**
 * 订单退货 服务层
 *
 * @author 484876123@qq.com
 * @date 2019-06-25
 */
public interface TradeOrderRefundService {
    /**
     * 查询订单退货信息
     *
     * @param id 订单退货ID
     * @return 订单退货信息
     */
    public JSONObject selectTradeOrderRefundById(String id);

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
     * 创建退货单
     *
     * @param jsonObject
     * @return
     * @throws Exception
     */
    public JSONObject addTradeOrderRefund(String jsonObject) throws Exception;

    /**
     * 根据买家Id获取退货单列表
     *
     * @param jsonObject
     * @return
     */
    public JSONObject getBuyerRefunds(String jsonObject);

    /**
     * 更新退货单状态
     *
     * @param jsonObject
     * @return
     */
    public JSONObject updateRefundStatus(String jsonObject);

    /**
     * 更新退货单信息
     *
     * @param jsonObject
     * @return
     */
    public JSONObject updateRefundInfo(String jsonObject);

    /**
     * 取消申请退货
     */
    public JSONObject updateRefundCancel(String json);

    /**
     * 根据退货单id查询退货单详情
     */
    public JSONObject getRefundItem(String json);


    /**
     * 微信退款
     * @param json
     * @return
     */
    public JSONObject wxRefund(String json);

}

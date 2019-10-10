package com.ly.mt.center.data.trade.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface TradeOrderItemsService {
    /**
     * @Description 保存TradeOrderItems
     * @Author taoye
     */
    ResponseJson insertTradeOrderItems(JSONObject jsonObject);

    /**
     * @Description 删除TradeOrderItems
     * @Author taoye
     */
    ResponseJson deleteTradeOrderItems(JSONObject jsonObject);

    /**
     * @Description 更新TradeOrderItems
     * @Author taoye
     */
    ResponseJson updateTradeOrderItems(JSONObject jsonObject);

    /**
     * @Description 查询TradeOrderItems
     * @Author taoye
     */
    ResponseJson getTradeOrderItems(JSONObject jsonObject);

    /**
     * @Description 根据order_id查询TradeOrderItems
     * @Author taoye
     */
    ResponseJson listOrderItemsByOrderId(JSONObject jsonObject);


    /**
     *根据userId和skuId查询小燕的购买数量
     * @param jsonObject
     * @return
     */
    ResponseJson getFreeLittleSmokeCount(JSONObject jsonObject);

    /**
     *根据userId和skuId查询mtgo的购买数量
     * @param jsonObject
     * @return
     */
    ResponseJson getmogozeropriceCount(JSONObject jsonObject);

    /**
     *根据userId和skuId查询套装的购买数量
     * @param jsonObject
     * @return
     */
    public ResponseJson gettaozhuangzeropriceCount(JSONObject jsonObject);
}
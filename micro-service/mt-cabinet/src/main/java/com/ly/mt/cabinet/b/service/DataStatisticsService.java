package com.ly.mt.cabinet.b.service;

import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.DataStarusticsOrdersRequestBody;
import com.ly.mt.cabinet.b.common.request.DataStarusticsRequestBody;
import com.ly.mt.cabinet.b.common.response.*;

import java.util.List;

/**
 * 数据统计
 */
public interface DataStatisticsService {


    /**
     * 查询bd下边的店铺
     * @param user
     * @return
     */
    List<ShopRespVo> findStores(TokenUserMessage user);


    /**
     * 查询bd的数据总汇信息
     * @param user
     * @return
     */
    BdDetailRespVo findBdDataDetail(DataStarusticsRequestBody body, TokenUserMessage user);


    /**
     * 查询bd旗下所有店铺详情信息集合
     * @param body
     * @param user
     * @return
     */
    PageInfoResponseVo<BdNewDataStaticsticRespVo> findBdDataStatistics(DataStarusticsOrdersRequestBody body, TokenUserMessage user);


    /**
     * 查询指定bd的指定店铺的订单情况
     * @param body
     * @param user
     * @return
     */
    BdStoreOrdersRespVo<OrdersRespVo> findBdStoreOrders(DataStarusticsOrdersRequestBody body, TokenUserMessage user);

}

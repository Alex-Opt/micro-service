package com.ly.mt.cabinet.b.controller;

import com.ly.mt.cabinet.b.common.annotation.MixController;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.DataStarusticsOrdersRequestBody;
import com.ly.mt.cabinet.b.common.request.DataStarusticsRequestBody;
import com.ly.mt.cabinet.b.common.response.*;
import com.ly.mt.cabinet.b.service.DataStatisticsService;
import com.ly.mt.cabinet.b.util.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 数据统计
 */
@Api(description = "数据统计")
@MixController("/cabinet/b/data")
public class DataStatisticsController  extends  BaseMessageController{


    @Autowired
    private DataStatisticsService dataStatisticsService;


    @ApiOperation("bd的店铺")
    @PostMapping("/bdShops")
    public Resp<List<ShopRespVo>> bdShops(HttpServletRequest request) {
        TokenUserMessage tokenUserMessage = getTokenUserMessage(request);
        List<ShopRespVo> vos = dataStatisticsService.findStores(tokenUserMessage);
        return Resp.createBySuccess(vos);
    }


    @ApiOperation("bd数据统计汇总详情信息，不用加分页的两个参数")
    @PostMapping("/bdDataDetail")
    public Resp<BdDetailRespVo> bdDataDetail(@RequestBody DataStarusticsRequestBody body, HttpServletRequest request){
        TokenUserMessage tokenUserMessage = getTokenUserMessage(request);
        BdDetailRespVo vo = dataStatisticsService.findBdDataDetail(body, tokenUserMessage);
        return Resp.createBySuccess(vo);
    }


    @ApiOperation("/店铺的数据统计详情集合")
    @PostMapping("/bdDataStatistics")
    public Resp<PageInfoResponseVo<BdNewDataStaticsticRespVo>> bdDataStatistics(@RequestBody DataStarusticsOrdersRequestBody body, HttpServletRequest request) {
        TokenUserMessage tokenUserMessage = getTokenUserMessage(request);
        PageInfoResponseVo<BdNewDataStaticsticRespVo> vo = dataStatisticsService.findBdDataStatistics(body, tokenUserMessage);
        return Resp.createBySuccess(vo);
    }

    @ApiOperation("/指定店铺的数据统计")
    @PostMapping("/bdStoreOrders")
    public Resp<BdStoreOrdersRespVo<OrdersRespVo>> bdStoreOrders(@RequestBody DataStarusticsOrdersRequestBody body,HttpServletRequest request){
        TokenUserMessage tokenUserMessage = getTokenUserMessage(request);
        BdStoreOrdersRespVo<OrdersRespVo> vo = dataStatisticsService.findBdStoreOrders(body, tokenUserMessage);
        return Resp.createBySuccess(vo);
    }

}

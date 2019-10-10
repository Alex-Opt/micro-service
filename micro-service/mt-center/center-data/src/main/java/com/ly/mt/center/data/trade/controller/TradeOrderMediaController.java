package com.ly.mt.center.data.trade.controller;


import com.ly.mt.center.data.trade.entity.TradeOrderMedia;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "订单商品表接口")
@RestController
@RequestMapping("/data/center/tradeOrderMedia")
public class TradeOrderMediaController {

    @ApiOperation(value = "保存TradeOrderMedia")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserTradeOrderMedia")
    public ResponseJson inserTradeOrderMedia(@RequestBody TradeOrderMedia tradeOrderMedia) {
        return null;
    }

}

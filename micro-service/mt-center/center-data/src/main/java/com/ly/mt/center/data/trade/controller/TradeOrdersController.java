package com.ly.mt.center.data.trade.controller;

import com.ly.mt.center.data.trade.entity.TradeOrders;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "订单接口")
@RestController
@RequestMapping("/data/center/tradeOrders")
public class TradeOrdersController {
    @ApiOperation(value = "保存TradeOrders")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/insertTradeOrders")
    public ResponseJson insertTradeOrders(@RequestBody TradeOrders tradeOrders) {
        return null;
    }


    @ApiOperation(
            value = "更新TradeOrders",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id、order_no不能同时为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateTradeOrders")
    public ResponseJson updateTradeOrders(@RequestBody TradeOrders tradeOrders) {
        return null;
    }


    @ApiOperation(value = "查询TradeOrders", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getTradeOrders")
    public TradeOrders getTradeOrders() {
        return null;
    }


    @ApiOperation(value = "查询TradeOrders集合", notes = "查询条件order_no不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order_no", value = "订单号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/listTradeOrders")
    public List<TradeOrders> listTradeOrders() {
        return null;
    }
}
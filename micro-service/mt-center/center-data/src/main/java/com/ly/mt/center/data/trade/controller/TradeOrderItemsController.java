package com.ly.mt.center.data.trade.controller;

import com.ly.mt.center.data.trade.entity.TradeOrderItems;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "订单商品表接口")
@RestController
@RequestMapping("/data/center/tradeOrderItems")
public class TradeOrderItemsController {
    @ApiOperation(value = "保存TradeOrderItems")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserTradeOrderItems")
    public ResponseJson insertTradeOrderItems(@RequestBody TradeOrderItems tradeOrderItems) {
        return null;
    }


    @ApiOperation(
            value = "删除TradeOrderItems",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteTradeOrderItems")
    public ResponseJson deleteTradeOrderItems() {
        return null;
    }


    @ApiOperation(
            value = "更新TradeOrderItems",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateTradeOrderItems")
    public ResponseJson updateTradeOrderItems(@RequestBody TradeOrderItems tradeOrderItems) {
        return null;
    }


    @ApiOperation(value = "查询TradeOrderItems", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getTradeOrderItems")
    public ResponseJson getTradeOrderItems() {
        return null;
    }


    @ApiOperation(value = "查询TradeOrderItems集合", notes = "查询条件order_id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/listOrderItemsByOrderId")
    public ResponseJson listOrderItemsByOrderId() {
        return null;
    }
}
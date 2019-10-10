package com.ly.mt.center.data.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.entity.ShopStocks;
import com.ly.mt.center.data.shop.service.ShopStocksService;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "商家库存接口")
@RestController
@RequestMapping("/data/center/shopStocks")
public class ShopStocksController {

    @Resource
    ShopStocksService shopStocksService;

    @ApiOperation(value = "保存ShopStocks")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserShopStocks")
    public ResponseJson insertShopStocks(@RequestBody JSONObject jsonObject) {
        return shopStocksService.insertShopStocks(jsonObject);
    }


    @ApiOperation(
            value = "删除ShopStocks",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteShopStocks")
    public ResponseJson deleteShopStocks(@RequestBody JSONObject jsonObject) {
        return null;
    }


    @ApiOperation(
            value = "更新ShopStocks",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateShopStocks")
    public ResponseJson updateShopStocks(@RequestBody JSONObject jsonObject) {
        return shopStocksService.updateShopStocks(jsonObject);
    }


    @ApiOperation(value = "查询ShopStocks", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopStocks")
    public ResponseJson getShopStocks(@RequestBody JSONObject jsonObject) {
        return shopStocksService.getShopStocks(jsonObject);
    }
}
package com.ly.mt.center.data.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.entity.ShopPurchases;
import com.ly.mt.center.data.shop.service.ShopPurchasesService;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "商家进货订单接口")
@RestController
@RequestMapping("/data/center/shopPurchases")
public class ShopPurchasesController {
    
    @Resource
    ShopPurchasesService shopPurchasesService;
    
    @ApiOperation(value = "保存ShopPurchases")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserShopPurchases")
    public ResponseJson insertShopPurchases(@RequestBody JSONObject jsonObject) {
        return shopPurchasesService.insertShopPurchases(jsonObject);
    }


    @ApiOperation(
            value = "删除ShopPurchases",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteShopPurchases")
    public ResponseJson deleteShopPurchases() {
        return null;
    }


    @ApiOperation(
            value = "更新ShopPurchases",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateShopPurchases")
    public ResponseJson updateShopPurchases(@RequestBody JSONObject jsonObject) {
        return shopPurchasesService.updateShopPurchases(jsonObject);
    }


    @ApiOperation(value = "查询ShopPurchases", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopPurchases")
    public ResponseJson getShopPurchases(@RequestBody JSONObject jsonObject) {
        return shopPurchasesService.getShopPurchases(jsonObject);
    }
}
package com.ly.mt.center.data.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.service.ShopPurchasesItemsService;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "进货商品接口")
@RestController
@RequestMapping("/data/center/shopPurchasesItems")
public class ShopPurchasesItemsController {

    @Resource
    ShopPurchasesItemsService shopPurchasesItemsService;
    @ApiOperation(value = "保存ShopPurchasesItems")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserShopPurchasesItems")
    public ResponseJson insertShopPurchasesItems(@RequestBody JSONObject jsonObject) {
        return shopPurchasesItemsService.insertShopPurchasesItems(jsonObject);
    }


    @ApiOperation(
            value = "删除ShopPurchasesItems",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteShopPurchasesItems")
    public ResponseJson deleteShopPurchasesItems() {
        return null;
    }


    @ApiOperation(
            value = "更新ShopPurchasesItems",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateShopPurchasesItems")
    public ResponseJson updateShopPurchasesItems(@RequestBody JSONObject jsonObject) {
        return shopPurchasesItemsService.updateShopPurchasesItems(jsonObject);
    }


    @ApiOperation(value = "查询ShopPurchasesItems", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopPurchasesItems")
    public ResponseJson getShopPurchasesItems(@RequestBody JSONObject jsonObject) {
        return shopPurchasesItemsService.getShopPurchasesItems(jsonObject);
    }

    @ApiOperation(value = "查询getItemList", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getItemList")
    public ResponseJson getItemList(@RequestBody JSONObject jsonObject) {
        return shopPurchasesItemsService.getItemList(jsonObject);
    }

    @ApiOperation(value = "查询商家进货总数", notes = "查询条件shop_id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shop_id", value = "商家id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopItemNum")
    public ResponseJson getShopItemNum(@RequestBody JSONObject jsonObject) {
        return shopPurchasesItemsService.getShopItemNumByShopId(jsonObject);
    }
}
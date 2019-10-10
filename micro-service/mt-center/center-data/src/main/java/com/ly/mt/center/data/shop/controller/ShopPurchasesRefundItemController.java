package com.ly.mt.center.data.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.entity.ShopPurchasesRefundItem;
import com.ly.mt.center.data.shop.service.ShopPurchasesRefundItemService;
import com.ly.mt.center.data.shop.service.ShopPurchasesRefundService;
import com.ly.mt.center.data.shop.service.ShopPurchasesService;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "商家进货退货详情接口")
@RestController
@RequestMapping("/data/center/shopPurchasesRefundItem")
public class ShopPurchasesRefundItemController {
    @Resource
    ShopPurchasesRefundItemService shopPurchasesRefundItemService;
    @ApiOperation(value = "保存ShopPurchasesRefundItem")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserShopPurchasesRefundItem")
    public ResponseJson insertShopPurchasesRefundItem(@RequestBody ShopPurchasesRefundItem shopPurchasesRefundItem) {
        return null;
    }


    @ApiOperation(
            value = "删除ShopPurchasesRefundItem",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteShopPurchasesRefundItem")
    public ResponseJson deleteShopPurchasesRefundItem() {
        return null;
    }


    @ApiOperation(
            value = "更新ShopPurchasesRefundItem",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateShopPurchasesRefundItem")
    public ResponseJson updateShopPurchasesRefundItem(@RequestBody ShopPurchasesRefundItem shopPurchasesRefundItem) {
        return null;
    }


    @ApiOperation(value = "查询ShopPurchasesRefundItem", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopPurchasesRefundItem")
    public ResponseJson getShopPurchasesRefundItem() {
        return null;
    }

    @ApiOperation(value = "查询商家进货总数", notes = "查询条件shop_id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shop_id", value = "商家id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopRefundItemNum")
    public ResponseJson getShopRefundItemNum(@RequestBody JSONObject jsonObject) {
        return shopPurchasesRefundItemService.getShopRefundItemNum(jsonObject);
    }
}
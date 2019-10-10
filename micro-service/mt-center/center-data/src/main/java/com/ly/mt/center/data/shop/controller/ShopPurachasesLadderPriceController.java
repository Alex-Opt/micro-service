package com.ly.mt.center.data.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.entity.ShopPurachasesLadderPrice;
import com.ly.mt.center.data.shop.service.ShopPurachasesLadderPriceService;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "商家进货阶梯价配置接口")
@RestController
@RequestMapping("/data/center/shopPurachasesLadderPrice")
public class ShopPurachasesLadderPriceController {
    @Resource
    ShopPurachasesLadderPriceService shopPurachasesLadderPriceService;

    @ApiOperation(value = "保存ShopPurachasesLadderPrice")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserShopPurachasesLadderPrice")
    public ResponseJson insertShopPurachasesLadderPrice(@RequestBody JSONObject jsonObject) {
        return shopPurachasesLadderPriceService.insertShopPurachasesLadderPrice(jsonObject);
    }


    @ApiOperation(
            value = "删除ShopPurachasesLadderPrice",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteShopPurachasesLadderPrice")
    public ResponseJson deleteShopPurachasesLadderPrice(@RequestBody JSONObject jsonObject) {
        return shopPurachasesLadderPriceService.deleteShopPurachasesLadderPrice(jsonObject);
    }


    @ApiOperation(
            value = "更新ShopPurachasesLadderPrice",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateShopPurachasesLadderPrice")
    public ResponseJson updateShopPurachasesLadderPrice(@RequestBody JSONObject jsonObject) {
        return shopPurachasesLadderPriceService.updateShopPurachasesLadderPrice(jsonObject);
    }


    @ApiOperation(value = "查询ShopPurachasesLadderPrice", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopPurachasesLadderPrice")
    public ResponseJson getShopPurachasesLadderPrice(@RequestBody JSONObject jsonObject) {
        return shopPurachasesLadderPriceService.getShopPurachasesLadderPrice(jsonObject);
    }

    @ApiOperation(value = "查询ShopPurachasesLadderPrice集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopPurachasesLadderPriceList")
    public ResponseJson getShopPurachasesLadderPriceList(@RequestBody JSONObject jsonObject) {
        return shopPurachasesLadderPriceService.getShopPurachasesLadderPriceList(jsonObject);
    }
}
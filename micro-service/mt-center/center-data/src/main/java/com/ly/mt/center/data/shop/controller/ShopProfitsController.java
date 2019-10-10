package com.ly.mt.center.data.shop.controller;

import com.ly.mt.center.data.shop.entity.ShopProfits;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "商家收益汇总接口")
@RestController
@RequestMapping("/data/center/shopProfits")
public class ShopProfitsController {
    @ApiOperation(value = "保存ShopProfits")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserShopProfits")
    public ResponseJson insertShopProfits(@RequestBody ShopProfits shopProfits) {
        return null;
    }


    @ApiOperation(
            value = "删除ShopProfits",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteShopProfits")
    public ResponseJson deleteShopProfits() {
        return null;
    }


    @ApiOperation(
            value = "更新ShopProfits",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateShopProfits")
    public ResponseJson updateShopProfits(@RequestBody ShopProfits shopProfits) {
        return null;
    }


    @ApiOperation(value = "查询ShopProfits", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopProfits")
    public ResponseJson getShopProfits() {
        return null;
    }
}
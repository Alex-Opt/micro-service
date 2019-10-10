package com.ly.mt.center.data.shop.controller;

import com.ly.mt.center.data.shop.entity.ShopDefault;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "兜底商家信息接口")
@RestController
@RequestMapping("/data/center/shopDefault")
public class ShopDefaultController {
    @ApiOperation(value = "保存ShopDefault")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserShopDefault")
    public ResponseJson insertShopDefault(@RequestBody ShopDefault shopDefault) {
        return null;
    }


    @ApiOperation(
            value = "删除ShopDefault",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteShopDefault")
    public ResponseJson deleteShopDefault() {
        return null;
    }


    @ApiOperation(
            value = "更新ShopDefault",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateShopDefault")
    public ResponseJson updateShopDefault(@RequestBody ShopDefault shopDefault) {
        return null;
    }


    @ApiOperation(value = "查询ShopDefault", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopDefault")
    public ResponseJson getShopDefault() {
        return null;
    }
}
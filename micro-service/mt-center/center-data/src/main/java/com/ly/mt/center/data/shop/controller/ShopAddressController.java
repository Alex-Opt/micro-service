package com.ly.mt.center.data.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.entity.ShopAddress;
import com.ly.mt.center.data.shop.service.ShopAddressService;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "商家发货地址信息接口")
@RestController
@RequestMapping("/data/center/shopAddress")
public class ShopAddressController {
    @Resource
    ShopAddressService shopAddressService;

    @ApiOperation(value = "保存ShopAddress")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserShopAddress")
    public ResponseJson insertShopAddress(@RequestBody JSONObject jsonObject) {
        return shopAddressService.insertShopAddress(jsonObject);
    }


    @ApiOperation(
            value = "删除ShopAddress",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteShopAddress")
    public ResponseJson deleteShopAddress(@RequestBody JSONObject jsonObject) {
        return shopAddressService.deleteShopAddressById(jsonObject);
    }


    @ApiOperation(
            value = "更新ShopAddress",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateShopAddress")
    public ResponseJson updateShopAddress(@RequestBody JSONObject jsonObject) {
        return shopAddressService.updateShopAddressById(jsonObject);
    }


    @ApiOperation(value = "查询ShopAddress", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopAddress")
    public ResponseJson getShopAddress(@RequestBody JSONObject jsonObject) {
        return shopAddressService.getShopAddress(jsonObject);
    }

    @ApiOperation(value = "查询getShopDefaultAddressByShopId", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopDefaultAddressByShopId")
    public ResponseJson getShopDefaultAddressByShopId(@RequestBody JSONObject jsonObject) {
        return shopAddressService.getShopDefaultAddressByShopId(jsonObject);
    }
}
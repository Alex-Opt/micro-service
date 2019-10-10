package com.ly.mt.center.data.hd.controller;

import com.ly.mt.center.data.hd.entity.HdShopAttGoodsSpu;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "门店活动门店的商品sku接口")
@RestController
@RequestMapping("/data/center/hdShopAttGoodsSpu")
public class HdShopAttGoodsSpuController {
    @ApiOperation(value = "保存HdShopAttGoodsSpu")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserHdShopAttGoodsSpu")
    public ResponseJson insertHdShopAttGoodsSpu(@RequestBody HdShopAttGoodsSpu hdShopAttGoodsSpu) {
        return null;
    }


    @ApiOperation(
            value = "删除HdShopAttGoodsSpu",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteHdShopAttGoodsSpu")
    public ResponseJson deleteHdShopAttGoodsSpu() {
        return null;
    }


    @ApiOperation(
            value = "更新HdShopAttGoodsSpu",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateHdShopAttGoodsSpu")
    public ResponseJson updateHdShopAttGoodsSpu(@RequestBody HdShopAttGoodsSpu hdShopAttGoodsSpu) {
        return null;
    }


    @ApiOperation(value = "查询HdShopAttGoodsSpu", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getHdShopAttGoodsSpu")
    public ResponseJson getHdShopAttGoodsSpu() {
        return null;
    }
}
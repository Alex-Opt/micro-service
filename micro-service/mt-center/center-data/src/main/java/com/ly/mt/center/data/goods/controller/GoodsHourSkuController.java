package com.ly.mt.center.data.goods.controller;

import com.ly.mt.center.data.goods.entity.GoodsHourSku;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(description = "一小时达关联商品接口")
@RestController
@RequestMapping("/data/center/goodsHourSku")
public class GoodsHourSkuController {
    @ApiOperation(value = "保存GoodsHourSku")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserGoodsHourSku")
    public ResponseJson insertGoodsHourSku(@RequestBody GoodsHourSku goodsHourSku) {
        return null;
    }


    @ApiOperation(
            value = "删除GoodsHourSku",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteGoodsHourSku")
    public ResponseJson deleteGoodsHourSku() {
        return null;
    }


    @ApiOperation(
            value = "更新GoodsHourSku",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateGoodsHourSku")
    public ResponseJson updateGoodsHourSku(@RequestBody GoodsHourSku goodsHourSku) {
        return null;
    }


    @ApiOperation(value = "查询GoodsHourSku", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getGoodsHourSku")
    public ResponseJson getGoodsHourSku() {
        return null;
    }


    @ApiOperation("根绝skuId集合查询购物车商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuIds", value = "skuIds", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "查询成功!"),
            @ApiResponse(code = 1,message = "查询失败!")
    })
    public ResponseJson getGoodsSkuHourInfoBySkuIds(){
        return null;
    }
}
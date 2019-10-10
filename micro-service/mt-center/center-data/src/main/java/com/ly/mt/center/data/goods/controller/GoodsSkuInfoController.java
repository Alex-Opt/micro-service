package com.ly.mt.center.data.goods.controller;

import com.ly.mt.center.data.goods.entity.GoodsSkuInfo;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "商品SKU信息接口")
@RestController
@RequestMapping("/data/center/goodsSkuInfo")
public class GoodsSkuInfoController {
    @ApiOperation(value = "保存GoodsSkuInfo")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserGoodsSkuInfo")
    public ResponseJson insertGoodsSkuInfo(@RequestBody GoodsSkuInfo goodsSkuInfo) {
        return null;
    }


    @ApiOperation(
            value = "删除GoodsSkuInfo",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteGoodsSkuInfo")
    public ResponseJson deleteGoodsSkuInfo() {
        return null;
    }


    @ApiOperation(
            value = "更新GoodsSkuInfo",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateGoodsSkuInfo")
    public ResponseJson updateGoodsSkuInfo(@RequestBody GoodsSkuInfo goodsSkuInfo) {
        return null;
    }


    @ApiOperation(value = "查询GoodsSkuInfo", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getGoodsSkuInfo")
    public ResponseJson getGoodsSkuInfo() {
        return null;
    }


    @ApiOperation(value = "统计spu商品的月销量", notes = "查询spuId商品的月销量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "spuId", value = "goods_spu_info主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getSpuGoodsSellerNumberEachMonth")
    public ResponseJson getSpuGoodsSellerNumberEachMonth() {
        return null;
    }
}
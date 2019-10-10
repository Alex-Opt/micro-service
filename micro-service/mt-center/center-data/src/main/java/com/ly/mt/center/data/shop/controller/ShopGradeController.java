package com.ly.mt.center.data.shop.controller;

import com.ly.mt.center.data.shop.entity.ShopGrade;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "店铺会员等级配置接口")
@RestController
@RequestMapping("/data/center/shopGrade")
public class ShopGradeController {
    @ApiOperation(value = "保存ShopGrade")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserShopGrade")
    public ResponseJson insertShopGrade(@RequestBody ShopGrade shopGrade) {
        return null;
    }


    @ApiOperation(
            value = "删除ShopGrade",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteShopGrade")
    public ResponseJson deleteShopGrade() {
        return null;
    }


    @ApiOperation(
            value = "更新ShopGrade",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateShopGrade")
    public ResponseJson updateShopGrade(@RequestBody ShopGrade shopGrade) {
        return null;
    }


    @ApiOperation(value = "查询ShopGrade", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getShopGrade")
    public ResponseJson getShopGrade() {
        return null;
    }
}
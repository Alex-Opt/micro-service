package com.ly.mt.center.data.gy.controller;

import com.ly.mt.center.data.gy.entity.GyShopInfo;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "管易店铺信息接口")
@RestController
@RequestMapping("/data/center/gyShopInfo")
public class GyShopInfoController {
    @ApiOperation(value = "保存GyShopInfo")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserGyShopInfo")
    public ResponseJson insertGyShopInfo(@RequestBody GyShopInfo gyShopInfo) {
        return null;
    }


    @ApiOperation(
            value = "删除GyShopInfo",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteGyShopInfo")
    public ResponseJson deleteGyShopInfo() {
        return null;
    }


    @ApiOperation(
            value = "更新GyShopInfo",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateGyShopInfo")
    public ResponseJson updateGyShopInfo(@RequestBody GyShopInfo gyShopInfo) {
        return null;
    }


    @ApiOperation(value = "查询GyShopInfo", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getGyShopInfo")
    public ResponseJson getGyShopInfo() {
        return null;
    }
}
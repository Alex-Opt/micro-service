package com.ly.mt.center.data.battle.controller;

import com.ly.mt.center.data.battle.entity.OrdersBattleInfo;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "商家抢单信息接口")
@RestController
@RequestMapping("/data/center/ordersBattleInfo")
public class OrdersBattleInfoController {
    @ApiOperation(value = "保存OrdersBattleInfo")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserOrdersBattleInfo")
    public ResponseJson insertOrdersBattleInfo(@RequestBody OrdersBattleInfo ordersBattleInfo) {
        return null;
    }


    @ApiOperation(
            value = "删除OrdersBattleInfo",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteOrdersBattleInfo")
    public ResponseJson deleteOrdersBattleInfo() {
        return null;
    }


    @ApiOperation(
            value = "更新OrdersBattleInfo",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateOrdersBattleInfo")
    public ResponseJson updateOrdersBattleInfo(@RequestBody OrdersBattleInfo ordersBattleInfo) {
        return null;
    }


    @ApiOperation(value = "查询OrdersBattleInfo", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getOrdersBattleInfo")
    public ResponseJson getOrdersBattleInfo() {
        return null;
    }
}
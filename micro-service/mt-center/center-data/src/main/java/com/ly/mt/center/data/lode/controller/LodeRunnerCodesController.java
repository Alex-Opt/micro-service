package com.ly.mt.center.data.lode.controller;

import com.ly.mt.center.data.lode.entity.LodeRunnerCodes;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "淘金者专属邀请码接口")
@RestController
@RequestMapping("/data/center/lodeRunnerCodes")
public class LodeRunnerCodesController {
    @ApiOperation(value = "保存LodeRunnerCodes")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserLodeRunnerCodes")
    public ResponseJson insertLodeRunnerCodes(@RequestBody LodeRunnerCodes lodeRunnerCodes) {
        return null;
    }


    @ApiOperation(
            value = "删除LodeRunnerCodes",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteLodeRunnerCodes")
    public ResponseJson deleteLodeRunnerCodes() {
        return null;
    }


    @ApiOperation(
            value = "更新LodeRunnerCodes",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateLodeRunnerCodes")
    public ResponseJson updateLodeRunnerCodes(@RequestBody LodeRunnerCodes lodeRunnerCodes) {
        return null;
    }


    @ApiOperation(value = "查询LodeRunnerCodes", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getLodeRunnerCodes")
    public ResponseJson getLodeRunnerCodes() {
        return null;
    }
}
package com.ly.mt.center.data.lode.controller;

import com.ly.mt.center.data.lode.entity.LodeRunnerConfigs;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "淘金人配置接口")
@RestController
@RequestMapping("/data/center/lodeRunnerConfigs")
public class LodeRunnerConfigsController {
    @ApiOperation(value = "保存LodeRunnerConfigs")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserLodeRunnerConfigs")
    public ResponseJson insertLodeRunnerConfigs(@RequestBody LodeRunnerConfigs lodeRunnerConfigs) {
        return null;
    }


    @ApiOperation(
            value = "删除LodeRunnerConfigs",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteLodeRunnerConfigs")
    public ResponseJson deleteLodeRunnerConfigs() {
        return null;
    }


    @ApiOperation(
            value = "更新LodeRunnerConfigs",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateLodeRunnerConfigs")
    public ResponseJson updateLodeRunnerConfigs(@RequestBody LodeRunnerConfigs lodeRunnerConfigs) {
        return null;
    }


    @ApiOperation(value = "查询LodeRunnerConfigs", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getLodeRunnerConfigs")
    public ResponseJson getLodeRunnerConfigs() {
        return null;
    }
}
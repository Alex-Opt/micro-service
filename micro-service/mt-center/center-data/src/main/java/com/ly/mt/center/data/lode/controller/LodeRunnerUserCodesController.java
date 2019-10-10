package com.ly.mt.center.data.lode.controller;

import com.ly.mt.center.data.lode.entity.LodeRunnerUserCodes;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "C端用户专属邀请码接口")
@RestController
@RequestMapping("/data/center/lodeRunnerUserCodes")
public class LodeRunnerUserCodesController {
    @ApiOperation(value = "保存LodeRunnerUserCodes")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserLodeRunnerUserCodes")
    public ResponseJson insertLodeRunnerUserCodes(@RequestBody LodeRunnerUserCodes lodeRunnerUserCodes) {
        return null;
    }


    @ApiOperation(
            value = "删除LodeRunnerUserCodes",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteLodeRunnerUserCodes")
    public ResponseJson deleteLodeRunnerUserCodes() {
        return null;
    }


    @ApiOperation(
            value = "更新LodeRunnerUserCodes",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateLodeRunnerUserCodes")
    public ResponseJson updateLodeRunnerUserCodes(@RequestBody LodeRunnerUserCodes lodeRunnerUserCodes) {
        return null;
    }


    @ApiOperation(value = "查询LodeRunnerUserCodes", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getLodeRunnerUserCodes")
    public ResponseJson getLodeRunnerUserCodes() {
        return null;
    }
}
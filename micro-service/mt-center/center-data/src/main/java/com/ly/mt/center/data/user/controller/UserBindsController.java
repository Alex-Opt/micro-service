package com.ly.mt.center.data.user.controller;

import com.ly.mt.center.data.user.entity.UserBinds;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "用户绑定接口")
@RestController
@RequestMapping("/data/center/userBinds")
public class UserBindsController {
    @ApiOperation(value = "保存UserBinds")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserUserBinds")
    public ResponseJson insertUserBinds(@RequestBody UserBinds userBinds) {
        return null;
    }


    @ApiOperation(
            value = "删除UserBinds",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteUserBinds")
    public ResponseJson deleteUserBinds() {
        return null;
    }


    @ApiOperation(
            value = "更新UserBinds",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateUserBinds")
    public ResponseJson updateUserBinds(@RequestBody UserBinds userBinds) {
        return null;
    }


    @ApiOperation(value = "查询UserBinds", notes = "查询条件id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getUserBinds")
    public ResponseJson getUserBinds() {
        return null;
    }
}
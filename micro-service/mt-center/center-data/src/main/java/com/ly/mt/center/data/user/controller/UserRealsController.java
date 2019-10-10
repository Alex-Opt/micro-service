package com.ly.mt.center.data.user.controller;

import com.ly.mt.center.data.user.entity.UserReals;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "用户实名信息接口")
@RestController
@RequestMapping("/data/center/userReals")
public class UserRealsController {
    @ApiOperation(value = "保存UserReals")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/inserUserReals")
    public ResponseJson insertUserReals(@RequestBody UserReals userReals) {
        return null;
    }


    @ApiOperation(
            value = "删除UserReals",
            notes = "删除条件id不能为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteUserReals")
    public ResponseJson deleteUserReals() {
        return null;
    }


    @ApiOperation(
            value = "更新UserReals",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id不能为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateUserReals")
    public ResponseJson updateUserReals(@RequestBody UserReals userReals) {
        return null;
    }


    @ApiOperation(value = "查询UserReals", notes = "查询条件user_id不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "主键id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "查询成功!"),
            @ApiResponse(code = 1, message = "查询失败!")
    })
    @PostMapping("/getUserReals")
    public ResponseJson getUserReals() {
        return null;
    }
}
package com.ly.mt.center.data.user.controller;

import com.ly.mt.center.data.user.entity.User;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "用户中心——用户信息接口")
@RestController
@RequestMapping("/data/center/user")
public class UserController {
    @ApiOperation(value = "保存User")
    @ApiResponses({
            @ApiResponse(code = 0, message = "保存成功!"),
            @ApiResponse(code = 1, message = "保存失败!")
    })
    @PostMapping("/insertUser")
    public ResponseJson insertUser(@RequestBody User user) {
        return null;
    }


    @ApiOperation(
            value = "删除User",
            notes = "删除条件id、mobile、login_name不能同时为空"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除成功!"),
            @ApiResponse(code = 1, message = "删除失败!")
    })
    @PostMapping("/deleteUser")
    public ResponseJson deleteUser() {
        return null;
    }


    @ApiOperation(
            value = "更新User",
            notes = "1、字段值为null时不更新,为空符串时更新数据库字段为空  \n" +
                    "2、更新条件id、mobile、login_name不能同时为空  \n"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "更新成功!"),
            @ApiResponse(code = 1, message = "更新失败!")
    })
    @PostMapping("/updateUser")
    public ResponseJson updateUser(@RequestBody User user) {
        return null;
    }


    @ApiOperation(value = "根据id查询用户信息", notes = "只需传入用户id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/getUserById")
    public User getUserById() {
        return null;
    }


    @ApiOperation(value = "根据mobile查询用户信息", notes = "只需传入用户mobile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "用户手机号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/getUserByMobile")
    public User getUserByMobile() {
        return null;
    }


    @ApiOperation(value = "根据login_name查询用户信息", notes = "只需传入用户login_name")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "login_name", value = "用户帐号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/getUserByLoginName")
    public User getUserByLoginName() {
        return null;
    }
}
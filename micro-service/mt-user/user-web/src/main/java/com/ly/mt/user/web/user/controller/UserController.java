package com.ly.mt.user.web.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.user.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;
import static com.ly.mt.core.common.method.UserMethodEnum.*;


@Api(description = "用户注册接口")
@RestController
@RequestMapping("/user/user")
public class UserController extends BaseController {
    @ApiOperation("校验账号是否已注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "登录帐号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "账号未注册!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!"),
            @ApiResponse(code = 20, message = "账号已注册!")
    })
    @PostMapping("/checkLoginName")
    public JSONObject checkLoginName(@RequestParam(value = "loginName") String loginName) {
        // 校验参数
        if (StringUtil.isEmpty(loginName)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginName", loginName);
        return callUserServer(USER_CHECK_LOGIN_NAME, jsonObject);
    }


    @ApiOperation("校验手机号是否已注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "手机号未注册!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!"),
            @ApiResponse(code = 21, message = "手机号已注册!")
    })
    @PostMapping("/checkUserMobile")
    public JSONObject checkUserMobile(@RequestParam(value = "mobile") String mobile) {
        // 校验参数
        if (StringUtil.isEmpty(mobile) || !StringUtil.isPhoneNumber(mobile)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", mobile);
        return callUserServer(USER_CHECK_USER_MOBILE, jsonObject);
    }


    @ApiOperation(value = "M端H5商城注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quickType", value = "注册来源:1-M端H5商城", paramType = "query", required = true),
            @ApiImplicitParam(name = "loginName", value = "用户名", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "动态验证码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "注册成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/mH5Regist")
    public JSONObject mH5Regist(@RequestParam(value = "quickType") String quickType,
                                @RequestParam(value = "loginName") String loginName,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "mobile") String mobile,
                                @RequestParam(value = "dynamicCode") String dynamicCode) {
        // 校验参数
        if (StringUtil.isEmpty(quickType)
                || StringUtil.isEmpty(loginName)
                || !StringUtil.isPhoneNumber(mobile)
                || StringUtil.isEmpty(password)
                || StringUtil.isEmpty(dynamicCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("quickType", quickType);
        jsonObject.put("loginName", loginName);
        jsonObject.put("password", password);
        jsonObject.put("mobile", mobile);
        jsonObject.put("dynamicCode", dynamicCode);
        return callUserServer(USER_M_H5_NAME_REGIST, jsonObject);
    }
}
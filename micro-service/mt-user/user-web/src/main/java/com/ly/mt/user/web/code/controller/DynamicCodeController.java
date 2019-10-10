package com.ly.mt.user.web.code.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.core.common.util.VerifyCodeUtils;
import com.ly.mt.user.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;
import static com.ly.mt.core.common.method.UserMethodEnum.DYNAMIC_CODE_GET_DYNAMIC_CODE;

@Api(description = "动态验证码接口")
@RestController
@RequestMapping("/user/code")
public class DynamicCodeController extends BaseController {
    @ApiOperation("获取动态验证码并发送到手机")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCodeType", value = "验证码类型(0:用户注册；1:手机号登录；2:修改密码；3:绑定手机)", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!"),
            @ApiResponse(code = 12, message = "90秒内重复获取!"),
    })
    @PostMapping("/getDynamicCode")
    public JSONObject getDynamicCode(@RequestParam(value = "mobile") String mobile,
                                     @RequestParam(value = "dynamicCodeType") String dynamicCodeType) {
        // 校验参数非空
        if (StringUtil.isEmpty(mobile) || !StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCodeType)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", mobile);
        jsonObject.put("dynamicCodeType", dynamicCodeType);
        return callUserServer(DYNAMIC_CODE_GET_DYNAMIC_CODE, jsonObject);
    }

    @ApiOperation("获取图片验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "verifyCodeType", value = "图片验证码类型(1:注册验证,2:登陆验证,3:重置密码,4:绑定手机 )", paramType = "query", required = true),
    })
    @GetMapping("/getVerifyCode")
    public void getVerifyCodeImage(
            @RequestParam(value = "verifyCodeType",required = true)String verifyCodeType,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        System.out.println(verifyCode);
        // 生成图片
        int w = 200, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        //获取类型
        switch (verifyCodeType){
            case "1":
                redisServer.set(RedisEnum.MAGE_VERIFY_CODE_REGIST,verifyCodeType,verifyCode);
                redisServer.expire(RedisEnum.MAGE_VERIFY_CODE_REGIST, verifyCodeType, 90);
                return;
            case "2":
                redisServer.set(RedisEnum.IMAGE_VERIFY_CODE_LOGIN,verifyCodeType,verifyCode);
                redisServer.expire(RedisEnum.IMAGE_VERIFY_CODE_LOGIN, verifyCodeType, 90);
                return;
            case "3":
                redisServer.set(RedisEnum.IMAGE_VERIFY_CODE_RESET_PASSWORD,verifyCodeType,verifyCode);
                redisServer.expire(RedisEnum.IMAGE_VERIFY_CODE_RESET_PASSWORD, verifyCodeType, 90);
                return;
            case "4":
                redisServer.set(RedisEnum.IMAGE_VERIFY_CODE_BIND_MOBILE,verifyCodeType,verifyCode);
                redisServer.expire(RedisEnum.IMAGE_VERIFY_CODE_BIND_MOBILE, verifyCodeType, 90);
                return;
                default:
                return;
        }


    }

}
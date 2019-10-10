package com.ly.mt.home.tob.code.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.base.util.VerifyCodeUtils;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.home.tob.code.service.DynamicCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * @author: linan
 * @date: 2019/9/10
 **/
@Api(tags = "动态验证码接口")
@RestController
@RequestMapping("/home/code")
public class DynamicCodeController {
    @Resource
    DynamicCodeService service;
    @Resource
    RedisService redisService;

    @ApiOperation(value = "发送动态验证到手机")
    @PostMapping("/sendDynamicCode")
    public ResponseJson sendDynamicCode(@ApiParam(value = "手机号", required = true)
                                            @RequestParam(value = "mobile") String mobile,
                                        @ApiParam(value = "验证码类型(105:用户注册；106:手机号登录；107:修改密码;)", required = true)
                                            @RequestParam(value = "codeType") String dynamicCodeType) {
        // 校验参数非空
        if (!StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCodeType)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.sendDynamicCode(mobile, dynamicCodeType);
    }

    @ApiOperation("获取图片验证码")
    @GetMapping("/getVerifyCode")
    public void getVerifyCodeImage(@ApiParam(value = "图片验证码类型(1:注册验证,2:登陆验证,3:重置密码,4:绑定手机 )", required = true)
                                       @RequestParam(value = "verifyCodeType") String verifyCodeType, HttpServletResponse response) throws IOException {
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
        switch (verifyCodeType) {
            case "1":
                redisService.set(RedisKey.MAGE_VERIFY_CODE_REGIST, verifyCodeType, verifyCode, 90L, TimeUnit.SECONDS);
                return;
            case "2":
                redisService.set(RedisKey.IMAGE_VERIFY_CODE_LOGIN, verifyCodeType, verifyCode, 90L, TimeUnit.SECONDS);
                return;
            case "3":
                redisService.set(RedisKey.IMAGE_VERIFY_CODE_RESET_PASSWORD, verifyCodeType, verifyCode, 90L, TimeUnit.SECONDS);
                return;
            case "4":
                redisService.set(RedisKey.IMAGE_VERIFY_CODE_BIND_MOBILE, verifyCodeType, verifyCode, 90L, TimeUnit.SECONDS);
                return;
            default:
                throw new IllegalArgumentException("Unexpected value: " + verifyCodeType);
        }
    }
}
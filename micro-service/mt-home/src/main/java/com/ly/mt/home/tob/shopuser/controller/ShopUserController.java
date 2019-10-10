package com.ly.mt.home.tob.shopuser.controller;

import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.home.tob.shopuser.service.ShopUserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author wanglong
 */
@Api(description = "b端用户登录注册接口控制器")
@RestController
@RequestMapping("/home/shopuser")
public class ShopUserController {
    /**
     * 日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopUserController.class);

    @Resource
    private ShopUserService service;
    @Resource
    private RedisService redisService;

    @ApiOperation("b端校验手机号是否已注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 4, message = "手机号未注册!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!"),
            @ApiResponse(code = 21, message = "手机号已注册! 返回 userId,ShopId,请完善基本信息"),
            @ApiResponse(code = 24, message = "未设置登陆密码"),
    })

    @PostMapping("/checkShopUserMobile")
    public ResponseJson checkUserMobile(@RequestParam(value = "mobile") String mobile) {
        // 校验参数
        if (StringUtil.isEmpty(mobile) || !StringUtil.isPhoneNumber(mobile)) {
            return ResponseUtil.getResponseCode(ResponseCode.RESULT_CODE_PARAM_ERROR);
        }
        return service.bShopUserCheckMobile(mobile);
    }

    @ApiOperation("b端注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "动态码", paramType = "query", required = true),
            @ApiImplicitParam(name = "verifyCode", value = "验证码", paramType = "query", required = true),
            @ApiImplicitParam(name = "inviteCode", value = "邀请码", paramType = "query", required = true),
            @ApiImplicitParam(name = "sourceFlag", value = "来源标识", paramType = "query", required = false),
            @ApiImplicitParam(name = "quickType", value = "来源标识", paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! 返回 userId"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!"),
            @ApiResponse(code = 10, message = "动态验证码失效!"),
            @ApiResponse(code = 11, message = "动态验证码错误!"),
            @ApiResponse(code = 13, message = "图片码错误!"),
            @ApiResponse(code = 14, message = "图片验证码错误!"),
            @ApiResponse(code = 15, message = "注册邀请码无效 !"),
    })
    @PostMapping("/shopUserRegist")
    public ResponseJson shopUserRegist(@RequestParam(value = "mobile", required = true) String mobile,
                                       @RequestParam(value = "dynamicCode", required = true) String dynamicCode,
                                       @RequestParam(value = "sourceFlag", required = false, defaultValue = "") String sourceFlag,
                                       @RequestParam(value = "quickType", required = false, defaultValue = "") String quickType,
                                       @RequestParam(value = "inviteCode", required = false, defaultValue = "") String inviteCode) {

        // 校验参数
        if (!StringUtil.isPhoneNumber(mobile) ||
                StringUtil.isEmpty(dynamicCode)) {
            return ResponseUtil.getResponseCode(ResponseCode.RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        return service.bShopUserRegist(inviteCode, mobile, sourceFlag, quickType, dynamicCode);
    }

    @ApiOperation("b端设置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "insert", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = '0', message = "操作成功"),
            @ApiResponse(code = '1', message = "系统异常"),
    })
    @PostMapping("/setPassword")
    public ResponseJson setPassword(@RequestParam(value = "password", required = true) String password,
                                    @RequestParam(value = "userId", required = true) String userId) {
        //校验参数
        if (StringUtil.isEmpty(password) || StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(ResponseCode.RESULT_CODE_PARAM_ERROR);
        }
        return service.bShopUserSetPassword(password, userId);
    }

    @ApiOperation("b端手机号密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "用户手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! 返回b端用户信息  发货地址"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 4, message = "账号或密码错误!"),
            @ApiResponse(code = 22, message = "手机号未入驻MOTO到家!"),
            @ApiResponse(code = 23, message = "该b端未通过认证!")
    })
    @PostMapping("/namelogin")
    public ResponseJson namelogin(@RequestParam(value = "mobile") String mobile,
                                  @RequestParam(value = "password") String password) {
        //校验参数
        if (StringUtil.isEmpty(mobile) || StringUtil.isEmpty(password)) {
            return ResponseUtil.getResponseCode(ResponseCode.RESULT_CODE_PARAM_ERROR);
        }
        try {
            return service.nameLogin(mobile, password);
        } catch (Exception e) {
            return ResponseUtil.getResponseCode(ResponseCode.RESULT_CODE_SYSTEM_ERROR);
        }
    }

    @ApiOperation("b端手机号动态码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "用户手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "动态验证码", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 10, message = "验证码失效!"),
            @ApiResponse(code = 11, message = "验证码错误!"),
            @ApiResponse(code = 22, message = "手机号未入驻Moti到家!"),
            @ApiResponse(code = 23, message = "b端未通过认证!")
    })
    @PostMapping("/mobilelogin")
    public ResponseJson mobilelogin(@RequestParam(value = "mobile", required = true) String mobile,
                                    @RequestParam(value = "dynamicCode", required = true) String dynamicCode) {
        //校验参数
        if (!StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCode)) {
            return ResponseUtil.getResponseCode(ResponseCode.RESULT_CODE_PARAM_ERROR);
        }

        return service.mobileLogin(dynamicCode, mobile);
    }


    @ApiOperation("b端图片验证码验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "verifyCode", value = "动态验证码", paramType = "query", required = true),
            @ApiImplicitParam(name = "verifyCodeType", value = "动态验证码类型(1:注册 2:登录 3:重置密码 4:绑定手机号)", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 18, message = "图片验证码失效!"),
            @ApiResponse(code = 19, message = "图片验证码错误!"),
    })
    @PostMapping("/checkVerifyCode")
    public ResponseJson checkVerifyCode(@RequestParam(value = "verifyCode", required = true) String verifyCode,
                                        @RequestParam(value = "verifyCodeType", required = true) String verifyCodeType) {
        if (StringUtil.isEmpty(verifyCode) || StringUtil.isEmpty(verifyCodeType)) {
            return ResponseUtil.getResponseCode(ResponseCode.RESULT_CODE_PARAM_ERROR);
        }
        String redisVerifyCode = null;
        switch (verifyCodeType) {
            case "1":
                redisVerifyCode = redisService.get(RedisKey.MAGE_VERIFY_CODE_REGIST, verifyCodeType);
                break;
            case "2":
                redisVerifyCode = redisService.get(RedisKey.IMAGE_VERIFY_CODE_LOGIN, verifyCodeType);
                break;
            case "3":
                redisVerifyCode = redisService.get(RedisKey.IMAGE_VERIFY_CODE_RESET_PASSWORD, verifyCodeType);
                break;
            case "4":
                redisVerifyCode = redisService.get(RedisKey.IMAGE_VERIFY_CODE_BIND_MOBILE, verifyCodeType);
                break;
            default:
                break;
        }

        if (StringUtil.isEmpty(redisVerifyCode)) {
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_VERIFY_CODE_INVALID);
        }
        if (!redisVerifyCode.equalsIgnoreCase(verifyCode)) {
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_VERIFY_CODE_ERROR);
        }
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }

    @ApiOperation("退出登录")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
    })
    @PostMapping("/loginout")
    public ResponseJson loginout(HttpServletRequest request) {
      /*  HttpSession session = request.getSession();
        session.invalidate();*/
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }

    @ApiOperation("b端重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "用户手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "用户新密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "手机动态码", paramType = "query", required = true),

    })
    @ApiResponses({
            @ApiResponse(code = 13, message = "图片码错误!"),
            @ApiResponse(code = 14, message = "图片验证码错误!"),
    })
    @PostMapping("/resetPassword")
    ResponseJson resetPassword(@RequestParam(value = "mobile", required = true) String mobile,
                               @RequestParam(value = "password", required = true) String password,
                               @RequestParam(value = "dynamicCode", required = true) String dynamicCode) {

        //校验参数
        if (!StringUtil.isPhoneNumber(mobile) ||
                StringUtil.isEmpty(password) ||
                StringUtil.isEmpty(dynamicCode)) {
            //返回参数错误
            return ResponseUtil.getResponseCode(ResponseCode.RESULT_CODE_PARAM_ERROR);
        }

        return service.resetPassword(password, mobile, dynamicCode);
    }

    @ApiOperation("b端活动注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "动态码", paramType = "query", required = true),
            @ApiImplicitParam(name = "quickType", value = "来源标识", paramType = "query", required = true)

    })
    @PostMapping("/activityShopUserRegist")
    public ResponseJson activityShopUserRegist(@RequestParam(value = "mobile", required = true) String mobile,
                                       @RequestParam(value = "dynamicCode", required = true) String dynamicCode,
                                       @RequestParam(value = "quickType", required = true, defaultValue = "") String quickType) {
        // 校验参数
        if (!StringUtil.isPhoneNumber(mobile)
                || StringUtil.isEmpty(dynamicCode)
                || StringUtil.isEmpty(quickType)) {
            return ResponseUtil.getResponseCode(ResponseCode.RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        return service.activityShopUserRegist(mobile, quickType, dynamicCode);
    }
}

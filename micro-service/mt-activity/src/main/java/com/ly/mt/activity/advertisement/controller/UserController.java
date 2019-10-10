package com.ly.mt.activity.advertisement.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.advertisement.service.UserService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.core.redis.dict.DynamicCodeType;
import com.ly.mt.core.redis.service.RedisService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.entity.ResponseCode.*;


/**
 * 活动用户注册类
 */
@Api(description = "用户登录接口")
@RestController
@RequestMapping("/activity1/user")
public class UserController {
    //日志
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Resource
    private RedisService redisService;

    @Resource
    private UserService service;

    @ApiOperation("活动H5页面登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quickType", value = "来源:4-活动H5页面", paramType = "query", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "userName", value = "用户姓名", paramType = "query", required = true),
            @ApiImplicitParam(name = "userAddress", value = "地址", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/activityH5Regist")
    public ResponseJson activityH5Regist(@RequestParam(value = "quickType") String quickType,
                                         @RequestParam(value = "mobile") String mobile,
                                         @RequestParam(value = "userName") String userName,
                                         @RequestParam(value = "userAddress") String userAddress,
                                         @RequestParam(value = "provinceCode") String provinceCode,
                                         @RequestParam(value = "provinceName") String provinceName,
                                         @RequestParam(value = "cityName") String cityName,
                                         @RequestParam(value = "cityCode") String cityCode,
                                         @RequestParam(value = "districtName") String districtName,
                                         @RequestParam(value = "districtCode") String districtCode,
                                         HttpServletRequest request) {
        LOGGER.info("活动用户注册请求开始");
        // 校验参数
        if (StringUtil.isEmpty(quickType)
                || !StringUtil.isPhoneNumber(mobile)
                || StringUtil.isEmpty(userName)
                || StringUtil.isEmpty(userAddress)) {
            LOGGER.error("活动用户注册参数错误");
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        String clickToken  = redisService.get(RedisKey.REDIS_REPEAT_CLICK_ACTION_ACTIVITY_SAFE_USERREGIST, mobile);
        if(StringUtil.isEmpty(clickToken)){
            redisService.set(RedisKey.REDIS_REPEAT_CLICK_ACTION_ACTIVITY_SAFE_USERREGIST,mobile,mobile,8L, TimeUnit.SECONDS);
        }else {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_OPERATOR_TOO_FASTER);
        }
        try {
            LOGGER.info("活动用户注册开始");
            JSONObject param = new JSONObject();
            param.put("quickType", quickType);
            param.put("mobile", mobile);
            param.put("userName", userName);
            param.put("userAddress", userAddress);
            param.put("provinceCode", provinceCode);
            param.put("provinceName", provinceName);
            param.put("cityName", cityName);
            param.put("cityCode", cityCode);
            param.put("districtName", districtName);
            param.put("districtCode", districtCode);
           // redisService.del(RedisKey.REDIS_REPEAT_CLICK_ACTION_ACTIVITY_SAFE_USERREGIST,mobile);
            return service.activityH5Regist(param, request);
        } catch (Exception e) {
            LOGGER.error("活动用户注册异常", e);
            redisService.del(RedisKey.REDIS_REPEAT_CLICK_ACTION_ACTIVITY_SAFE_USERREGIST,mobile);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }


    }

    @ApiOperation("校验手机号是否已注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "已注册!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 4, message = "未注册!")
    })
    @PostMapping("/checkUserMobile")
    public ResponseJson checkUserMobile(@RequestParam(value = "mobile") String mobile) {
        // 校验参数
        if (!StringUtil.isPhoneNumber(mobile)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.checkUserMobile(mobile);
    }


    @ApiOperation("活动页-跳转至扑克牌对应-本接口主要是保存用户的手机号。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "验证码", paramType = "query", required = true),
            @ApiImplicitParam(name = "codeType", value = "验证码类型(101:用户注册；102:手机号登录；103:修改密码；104:绑定手机)", paramType = "query", required = true),
            @ApiImplicitParam(name = "quickType", value = "用户来源:这里 quickType 值传9（9:对接到扑克牌的活动页）", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/savePhoneNumber")
    public ResponseJson savePhoneNumber(@RequestParam(value = "mobile") String mobile,
                                        @RequestParam(value = "quickType") String quickType,
                                        @RequestParam(value = "dynamicCode") String dynamicCode,
                                        @RequestParam(value = "codeType") String dynamicCodeType) {
        if (StringUtil.isEmpty(mobile) || StringUtil.isEmpty(quickType) || StringUtil.isEmpty(dynamicCode) || StringUtil.isEmpty(dynamicCodeType)) {
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "参数缺失!");
        }
        // 校验参数
        if (!StringUtil.isPhoneNumber(mobile)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        DynamicCodeType code = DynamicCodeType.getDynamicCodeTypeById(dynamicCodeType);
        // 校验90秒内重复获取
        String redisCode = redisService.get(code.getRedisKey(), mobile);
        if (StringUtil.isEmpty(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码失效,请重新获取!");
        }
        if (!redisCode.equals(dynamicCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
        }
        return service.savePhoneNumber(mobile, quickType);
    }

}

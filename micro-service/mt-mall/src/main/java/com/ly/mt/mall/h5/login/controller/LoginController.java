package com.ly.mt.mall.h5.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.mall.h5.login.service.LoginService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.redis.RedisKey.*;

@Api(description = "登录接口")
@RestController
@RequestMapping("/mall/h5/login")
public class LoginController {
    @Resource
    LoginService service;
    @Resource
    private RedisService redisService;

    @ApiOperation("登出")
    @ApiResponses({
            @ApiResponse(code = 0, message = "登出成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/logout")
    public ResponseJson out(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        String wxAppletToken = request.getHeader("token");
        if (StringUtil.isNotEmpty(wxAppletToken)) {
            redisService.del(REDIS_WX_LOGIN_TOKEN, wxAppletToken);
            String userCache = redisService.get(REDIS_WX_LOGIN_TOKEN, wxAppletToken);
            if (StringUtil.isNotEmpty(userCache)) {
                JSONObject userJson = JSONObject.parseObject(userCache);
                redisService.del(REDIS_RUNNER_TREE_ID, userJson.getString("id"));
                //最后一步删除token
                redisService.del(REDIS_TOKEN_LOGIN_MALL_H5, userJson.getString("login_name"));
            }
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @ApiOperation("账号密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户名/手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/nameLogin")
    public ResponseJson nameLogin(@RequestParam(value = "loginName") String loginName,
                                  @RequestParam(value = "password") String password,
                                  HttpServletRequest request) {
        if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(password)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.nameLogin(loginName, password, request);
    }

    /**
     * 只有H5页面会有活动页过来的数据，所以这个接口需要有channel,material,data_source这些来源，区分渠道的入参。
     *
     * @param mobile
     * @param dynamicCode
     * @param quickType
     * @param request
     * @return
     */
    @ApiOperation(value = "到家C-H5-手机号验证码登录", notes = "登录成功后返回userId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "验证码", paramType = "query", required = true),
            @ApiImplicitParam(name = "quickType", value = "咱们系统区分用户来源的值，比如：到家C，蓝牙，格子柜等", paramType = "query", required = true),
            @ApiImplicitParam(name = "data_source", value = "区分投放到不同渠道用户来源，比如：头条，抖音，腾讯新闻等", paramType = "query", required = false),
            @ApiImplicitParam(name = "material", value = "媒体素材号", paramType = "query", required = false),
            @ApiImplicitParam(name = "channel", value = "媒体渠道", paramType = "query", required = false),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/mobileLogin")
    public ResponseJson mobileLogin(@RequestParam(value = "mobile") String mobile,
                                    @RequestParam(value = "dynamicCode") String dynamicCode,
                                    @RequestParam(value = "quickType") String quickType,
                                    @RequestParam(value = "data_source", required = false) String data_source,
                                    @RequestParam(value = "material", required = false) String material,
                                    @RequestParam(value = "channel", required = false) String channel,
                                    HttpServletRequest request) {
        // 校验参数
        if (!StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCode) || StringUtil.isEmpty(quickType)) {
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "参数缺失!");
        }
        return service.mobileLogin(mobile, dynamicCode, quickType, data_source, material, channel, request);
    }

    @ApiOperation(value = "微信小程序/app：手机号验证码登录", notes = "登录成功后返回{userId：,token：},与h5的手机号验证码登陆的区别在于返回值，由于上面接口用的广泛，修改返回值会带来很大工作量，这里就重新写了个接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "验证码", paramType = "query", required = true),
            @ApiImplicitParam(name = "quickType", value = "区分用户来源的值", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/appletMobileLogin")
    public ResponseJson appletMobileLogin(@RequestParam(value = "mobile") String mobile,
                                          @RequestParam(value = "dynamicCode") String dynamicCode,
                                          @RequestParam(value = "quickType") String quickType,
                                          HttpServletRequest request) {
        // 校验参数
        if (!StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCode) || StringUtil.isEmpty(quickType)) {
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "参数缺失!");
        }
        return service.appletMobileLogin(mobile, dynamicCode, quickType, request);
    }

    @ApiOperation(value = "用户信息补全")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "账号", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/perfectInfo")
    public ResponseJson perfectInfo(@RequestParam(value = "loginName") String loginName,
                                    @RequestParam(value = "password") String password,
                                    HttpServletRequest request) {
        // 校验参数
        if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(password)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.perfectInfo(loginName, password, request);
    }


    @ApiOperation(value = "微信小程序：登陆授权接口，是微信小程序登陆的第一步")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "临时登录凭证 code", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/wxAppletLoginAuth")
    public ResponseJson wxAppletLoginAuth(HttpServletRequest request) {
        String code = request.getParameter("code");
        // 校验参数
        if (StringUtil.isEmpty(code)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数code缺失!");
        }
        return service.weChatAppletLoginAuth(code, request);
    }

    @ApiOperation(value = "微信小程序：获取用户绑定的微信手机号接口。微信小程序微信登陆的第二步")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "encryptedData", value = "包括敏感数据在内的完整用户信息的加密数据", paramType = "query", required = true),
            @ApiImplicitParam(name = "iv", value = "加密算法的初始向量", paramType = "query", required = true),
            @ApiImplicitParam(name = "userWxInfoCacheKey", value = "上一步微信授权接口返回的userWxInfoCacheKey", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 6, message = "参数缺失!"),
            @ApiResponse(code = 20, message = "获取微信绑定手机号异常，请重试!")
    })
    @PostMapping("/wxAppletGetBindMobileNumber")
    public ResponseJson wxAppletGetBindMobileNumber(HttpServletRequest request) {
        String encryptedData = request.getParameter("encryptedData");
        String iv = request.getParameter("iv");
        String userWxInfoCacheKey = request.getParameter("userWxInfoCacheKey");
        // 校验参数
        if (StringUtil.isEmpty(encryptedData)) {
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "参数 encryptedData 缺失!");
        }
        if (StringUtil.isEmpty(iv)) {
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "参数 iv 缺失");
        }
        if (StringUtil.isEmpty(userWxInfoCacheKey)) {
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "参数 userWxInfoCacheKey 缺失");
        }
        return service.wxAppletGetBindMobileNumber(encryptedData, iv, userWxInfoCacheKey);
    }


    @ApiOperation(value = "微信小程序：用户授权微信手机号登陆接口，微信小程序登陆的第三步。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "绑定微信的手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "userWxInfoCacheKey", value = "第一步微信授权接口返回的userWxInfoCacheKey", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 6, message = "参数缺失!"),
            @ApiResponse(code = 7, message = "微信小程序登陆信息过期!")
    })
    @PostMapping("/wxAppletMobileLogin")
    public ResponseJson wxAppletMobileLogin(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String userWxInfoCacheKey = request.getParameter("userWxInfoCacheKey");
        // 校验参数
        if (StringUtil.isEmpty(mobile)) {
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "参数 mobile 缺失!");
        }
        if (StringUtil.isEmpty(userWxInfoCacheKey)) {
            return ResponseUtil.getResponseMsg(RESULT_CODE_PARAM_NEED, "参数 userWxInfoCacheKey 缺失!");
        }
        return service.weChatAppletMobileLogin(mobile, userWxInfoCacheKey);
    }

    /**
     * 本接口已经优化为定时任务刷新。接口已废弃。暂留
     * @param request
     * @return
     */
    @ApiOperation(value = "微信小程序：模版消息推送前置条件，获取、刷新到家C小程序的access_token值.微信小程序模版消息推送第一步。")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/wxAppletAccessToken")
    public ResponseJson wxAppletAccessToken(HttpServletRequest request) {
        return service.wxAppletAccessToken();
    }


    @ApiOperation(value = "微信小程序：批量新增form_id数据接口，后台储备formId以供模版消息使用。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "formIds", value = "表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id，格式为逗号分隔的多个form_id", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/saveFormIds")
    public ResponseJson saveFormIds(HttpServletRequest request) {
        String openId = getOpenId(request);
        String formIds = request.getParameter("formIds");
        if (StringUtil.isEmpty(openId) || StringUtil.isEmpty(formIds)) {
            return ResponseUtil.getResponseCode(RESULT_CODE_PARAM_NEED);
        }
        return service.saveFormId(formIds, openId);
    }


    private String getOpenId(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtil.isEmpty(token)) {
            return null;
        }
        String userCache = redisService.get(REDIS_WX_LOGIN_TOKEN, token);
        JSONObject userJson = JSONObject.parseObject(userCache);
        return userJson.getString("wx_open_id");
    }
}
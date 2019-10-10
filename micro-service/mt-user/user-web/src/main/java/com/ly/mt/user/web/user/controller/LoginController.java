package com.ly.mt.user.web.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.user.UserLoginVo;
import com.ly.mt.core.common.entity.user.UserMobileLoginVO;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.user.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

import static com.ly.mt.core.common.constant.RedisEnum.LOGIN_TOKEN_REDIS;
import static com.ly.mt.core.common.constant.ResultCodeEnum.*;
import static com.ly.mt.core.common.dict.RegistStatusEnum.REGIST_STATUS_NO;
import static com.ly.mt.core.common.dict.RegistStatusEnum.REGIST_STATUS_YES;
import static com.ly.mt.core.common.method.UserMethodEnum.*;

@Api(description = "用户登录接口")
@RestController
@RequestMapping("/user/login")
public class LoginController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation("登出")
    @ApiResponses({
            @ApiResponse(code = 0, message = "登出成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/out")
    public JSONObject out(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return JsonUtil.getSuccessJson();
    }


    @ApiOperation("用户账号密码登录")
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
    public JSONObject nameLogin(@RequestParam(value = "loginName") String loginName,
                                @RequestParam(value = "password") String password,
                                HttpServletRequest request) {
        // 校验参数
        if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(password)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginName", loginName);
        jsonObject.put("password", password);
        jsonObject = callUserServer(LOGIN_NAME_LOGIN, jsonObject);
        // 判断校验是否成功
        String resultCode = jsonObject.getString("code");
        if (!RESULT_CODE_OPERATION_SUCCESS.getCode().equals(resultCode)) {
            return jsonObject;
        }
        // 保存session
        UserLoginVo user = JSONObject.parseObject(jsonObject.getJSONObject("result").toJSONString(), UserLoginVo.class);
        saveSession(request, user);
        return JsonUtil.getSuccessJson();
    }


    @ApiOperation(value = "用户手机动态验证码登录", notes = "登录成功后返回result实体，其中registStatus=0时为新用户需要补全数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "验证码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/mobileLogin")
    public JSONObject mobileLogin(@RequestParam(value = "mobile") String mobile,
                                  @RequestParam(value = "dynamicCode") String dynamicCode,
                                  HttpServletRequest request) {
        UserMobileLoginVO result = new UserMobileLoginVO();
        result.setRegistStatus(REGIST_STATUS_YES.getId());
        // 校验参数
        if (!StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 校验是否已注册
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", mobile);
        jsonObject = callUserServer(USER_CHECK_USER_MOBILE, jsonObject);
        String checkResultCode = jsonObject.getString("code");
        if (RESULT_CODE_SYSTEM_ERROR.getCode().equals(checkResultCode)) {
            // 校验异常
            return jsonObject;
        } else if (!RESULT_CODE_REGIST_MOBILE_ERROR.getCode().equals(checkResultCode)) {
            // 未注册时自动注册
            result.setRegistStatus(REGIST_STATUS_NO.getId());
            jsonObject = new JSONObject();
            jsonObject.put("mobile", mobile);
            jsonObject = callUserServer(USER_M_H5_MOBILE_REGIST, jsonObject);
            String registResultCode = jsonObject.getString("code");
            if (RESULT_CODE_SYSTEM_ERROR.getCode().equals(registResultCode)) {
                // 注册异常
                return jsonObject;
            }
        }
        //校验手机号和验证码
        jsonObject = new JSONObject();
        jsonObject.put("mobile", mobile);
        jsonObject.put("dynamicCode",dynamicCode);
        jsonObject = callUserServer(LOGIN_MOBILE_LOGIN, jsonObject);
        String loginCode = jsonObject.getString("code");
        if(RESULT_CODE_OPERATION_SUCCESS.getCode().equals(loginCode)) {
            // 获取用户信息进行登录
            jsonObject = new JSONObject();
            jsonObject.put("mobile", mobile);
            jsonObject = callUserServer(USER_INFO_LOAD_INFO_BY_USER_MOBILE, jsonObject);
            String getUserResultCode = jsonObject.getString("code");
            if (RESULT_CODE_SYSTEM_ERROR.getCode().equals(getUserResultCode)) {
                // 获取用户信息异常
                return jsonObject;
            }
            UserLoginVo user = JSONObject.parseObject(jsonObject.getJSONObject("result").toJSONString(), UserLoginVo.class);
            if (null != user) {
                // 保存session
                saveSession(request, user);
                return JsonUtil.getSuccessJson(result);
            }
        }else {
            return jsonObject;
        }
        return JsonUtil.getErrorJson(RESULT_CODE_OPERATION_FAIL);
    }


    @ApiOperation(value = "用户信息补全")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "账号", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/perfectInfo")
    public JSONObject perfectInfo(@RequestParam(value = "loginName") String loginName,
                                  @RequestParam(value = "password") String password,
                                  HttpServletRequest request) {
        // 校验参数
        if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(password)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginName", loginName);
        jsonObject.put("password", password);
        jsonObject = callUserServer(USER_INFO_PERFECT_INFO, jsonObject);
        // 判断校验是否成功
        String resultCode = jsonObject.getString("code");
        if (!RESULT_CODE_OPERATION_SUCCESS.getCode().equals(resultCode)) {
            return jsonObject;
        }
        // 更新session
        String json = jsonObject.getJSONObject("result").toJSONString();
        UserLoginVo user = JSONObject.parseObject(json, UserLoginVo.class);
        saveSession(request, user);
        return JsonUtil.getSuccessJson();
    }


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
    public JSONObject activityH5Regist(@RequestParam(value = "quickType") String quickType,
                                       @RequestParam(value = "mobile") String mobile,
                                       @RequestParam(value = "userName") String userName,
                                       @RequestParam(value = "userAddress") String userAddress,
                                       HttpServletRequest request) {
        // 校验参数
        if (StringUtil.isEmpty(quickType)
                || !StringUtil.isPhoneNumber(mobile)
                || StringUtil.isEmpty(userName)
                || StringUtil.isEmpty(userAddress)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 校验是否已注册
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", mobile);
        jsonObject = callUserServer(USER_CHECK_USER_MOBILE, jsonObject);
        String checkResultCode = jsonObject.getString("code");
        if (RESULT_CODE_SYSTEM_ERROR.getCode().equals(checkResultCode)) {
            // 校验异常
            return jsonObject;
        } else if (!RESULT_CODE_REGIST_MOBILE_ERROR.getCode().equals(checkResultCode)) {
            // 未注册时自动注册
            jsonObject = new JSONObject();
            jsonObject.put("quickType", quickType);
            jsonObject.put("mobile", mobile);
            jsonObject.put("userName", userName);
            jsonObject.put("userAddress", userAddress);
            jsonObject = callUserServer(USER_ACTIVITY_H5_REGIST, jsonObject);
            String registResultCode = jsonObject.getString("code");
            if (RESULT_CODE_SYSTEM_ERROR.getCode().equals(registResultCode)) {
                // 注册异常
                return jsonObject;
            }
        }
        // 获取用户信息进行登录
        jsonObject = new JSONObject();
        jsonObject.put("mobile", mobile);
        jsonObject = callUserServer(USER_INFO_LOAD_INFO_BY_USER_MOBILE, jsonObject);
        String getUserResultCode = jsonObject.getString("code");
        if (RESULT_CODE_SYSTEM_ERROR.getCode().equals(getUserResultCode)) {
            // 获取用户信息异常
            return jsonObject;
        }
        UserLoginVo user = JSONObject.parseObject(jsonObject.getJSONObject("result").toJSONString(), UserLoginVo.class);
        if (null != user) {
            // 保存session
            saveSession(request, user);
            return JsonUtil.getSuccessJson();
        }
        return JsonUtil.getErrorJson(RESULT_CODE_OPERATION_FAIL);
    }


    /**
     * @Description 用户登录保存session
     * @Author taoye
     */
    private void saveSession(HttpServletRequest request, UserLoginVo user) {
        // 存储常用信息
        String token = UUID.randomUUID().toString();
        redisServer.set(LOGIN_TOKEN_REDIS, user.getLoginName(), token);
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        session.setAttribute("userId", user.getId());
        session.setAttribute("loginName", user.getLoginName());
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("mobile", user.getMobile());
        session.setAttribute("ipAddress", getIpAddress(request));
        LOGGER.info("保存session:userId=" + user.getId());
        // 刷新session有效时间30分钟
        session.setMaxInactiveInterval(1800);
        // 刷新redis有效时间30分钟
        redisServer.expire(LOGIN_TOKEN_REDIS, user.getLoginName(), 1800);
    }

    /**
     * @Description 获取ip地址
     * @Author taoye
     */
    private String getIpAddress(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtil.isNotEmpty(ip)) {
            String[] ips = ip.split(",");
            return ips[0];
        }
        return ip;
    }
}
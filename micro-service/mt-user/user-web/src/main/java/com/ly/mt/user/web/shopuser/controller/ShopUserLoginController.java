package com.ly.mt.user.web.shopuser.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.ImageVerifyCodeTypeEnum;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.method.UserMethodEnum;
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
import java.util.Map;
import java.util.UUID;

import static com.ly.mt.core.common.constant.RedisEnum.LOGIN_TOKEN_REDIS;
import static com.ly.mt.core.common.constant.ResultCodeEnum.*;

@Api(description = "b端用户登陆操作接口")
@RestController
@RequestMapping("/user/shopUser/login")
public class ShopUserLoginController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ShopUserLoginController.class);


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
    public JSONObject namelogin(@RequestParam(value = "mobile") String mobile,
                                @RequestParam(value = "password") String password,
                                HttpServletRequest request) {
        //校验参数
        if (StringUtil.isEmpty(mobile) || StringUtil.isEmpty(password)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject params = new JSONObject();
        params.put("mobile", mobile);
        params.put("password", password);

        JSONObject jsonObject = callUserServer(UserMethodEnum.B_SHOP_USER_NAME_LOGIN, params);
        if (RESULT_CODE_OPERATION_SUCCESS.getCode().equals(jsonObject.getString("code"))) {
            String mapJsonStr = jsonObject.getJSONObject("result").toJSONString();
            Map<String, JSONObject> map = JSONObject.parseObject(mapJsonStr, Map.class);
            JSONObject shopInfoJsonObj = map.get("shop");
            // ShopInfo currentLoginShop = JSON.parseObject(shopInfoJsonStr, ShopInfo.class);

            // 存储常用信息
            saveSession(request, map);
        }
        return jsonObject;
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
    public JSONObject mobilelogin(@RequestParam(value = "mobile", required = true) String mobile,
                                  @RequestParam(value = "dynamicCode", required = true) String dynamicCode,
                                  HttpServletRequest request) {
        //校验参数
        if (!StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject param = new JSONObject();
        param.put("mobile", mobile);
        param.put("dynamicCode", dynamicCode);
        JSONObject jsonObject = callUserServer(UserMethodEnum.B_SHOP_USER_MOBILE_LOGIN, param);
        String checkResultCode = jsonObject.getString("code");
        if (RESULT_CODE_OPERATION_SUCCESS.getCode().equals(checkResultCode)) {
            String mapJsonStr = jsonObject.getJSONObject("result").toJSONString();
            Map<String, JSONObject> map = JSONObject.parseObject(mapJsonStr, Map.class);
            // String shopInfoJsonStr = map.get("shop");
            //   ShopInfo currentLoginShop = JSON.parseObject(shopInfoJsonStr, ShopInfo.class);
            // 存储常用信息
            saveSession(request, map);
        }
        return jsonObject;
    }


    @ApiOperation("b端h5页面手机号动态码登录(注册)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile",value = "用户手机号",paramType = "query",required = true),
            @ApiImplicitParam(name = "dynamicCode",value = "动态验证码",paramType = "query",required = true),
            @ApiImplicitParam(name = "verifyCode",value = "动态验证码",paramType = "query",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功!"),
            @ApiResponse(code = 1,message = "系统异常!"),
    })
    @PostMapping("/h5MolileLogin")
    public  JSONObject h5MolileLogin(@RequestParam(name = "mobile",required = true) String mobile,
                                     @RequestParam(name = "verifyCode",required = true) String verifyCode,
                                     @RequestParam(name = "dynamicCode",required = true)String dynamicCode,
                                     HttpServletRequest request){
        if(StringUtil.isEmpty(dynamicCode)
                && !StringUtil.isPhoneNumber(mobile)
                && !StringUtil.isEmpty(verifyCode)){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject param = new JSONObject();
        param.put("mobile",mobile);
        param.put("verifyCode",verifyCode);
        param.put("dynamicCode",dynamicCode);
        JSONObject jsonObject = callUserServer(UserMethodEnum.B_SHOP_USER_H5_MOBILE_LOGIN, param);
        if(RESULT_CODE_OPERATION_SUCCESS.getCode().equals(jsonObject.get("code"))){
            String resultStr = jsonObject.get("result").toString();
            Map<String,String> map = JSONObject.parseObject(resultStr, Map.class);
            //保存session
            String token = UUID.randomUUID().toString();
            redisServer.set(LOGIN_TOKEN_REDIS, map.get("mobile"), token);
            HttpSession session = request.getSession();
            session.setAttribute("token", token);
            session.setAttribute("userId", map.get("userId"));
            session.setAttribute("shopId", map.get("id"));
            session.setAttribute("mobile", map.get("mobile"));
            session.setMaxInactiveInterval(1800);
            // 刷新redis有效时间30分钟
            redisServer.expire(LOGIN_TOKEN_REDIS,map.get("mobile"), 1800);
        }
        return JsonUtil.getSuccessJson();
    }

    @ApiOperation("b端图片验证码验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "verifyCode", value = "动态验证码", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 10, message = "验证码失效!"),
            @ApiResponse(code = 11, message = "验证码错误!"),
            @ApiResponse(code = 22, message = "手机号未入驻Moti到家!"),
            @ApiResponse(code = 23, message = "b端未通过认证!")
    })
    @PostMapping("/checkVerifyCode")
    public JSONObject checkVerifyCode(@RequestParam(value = "verifyCode", required = true) String verifyCode,
                                      @RequestParam(value = "verifyCodeType", required = true) String verifyCodeType) {
        if (StringUtil.isEmpty(verifyCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        String redisVerifyCode = redisServer.get(RedisEnum.IMAGE_VERIFY_CODE_LOGIN, ImageVerifyCodeTypeEnum.IMAGE_VERIFY_CODE_LOGIN.getCode());
        if (StringUtil.isEmpty(redisVerifyCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
        }
        if (!redisVerifyCode.equalsIgnoreCase(verifyCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
        }
        return JsonUtil.getSuccessJson();
    }

    private void saveSession(HttpServletRequest request, Map<String, JSONObject> map) {
        JSONObject currentLoginShop = map.get("shop");
        String token = UUID.randomUUID().toString();
        redisServer.set(LOGIN_TOKEN_REDIS, (String) currentLoginShop.get("mobile"), token);
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        session.setAttribute("userId", (String) currentLoginShop.get("userId"));
        session.setAttribute("shopId", (String) currentLoginShop.get("id"));
        session.setAttribute("mobile", (String) currentLoginShop.get("mobile"));
        session.setAttribute("userName", (String) map.get("user").get("loginName"));
        LOGGER.info("保存session:shopId=" + currentLoginShop.get("id"));
        session.setMaxInactiveInterval(1800);
        // 刷新redis有效时间30分钟
        redisServer.expire(LOGIN_TOKEN_REDIS, (String) currentLoginShop.get("mobile"), 1800);
    }


}

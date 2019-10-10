package com.ly.mt.user.web.shopuser.controller;


import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.shop.ShopInfo;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.user.web.base.controller.BaseController;
import com.ly.mt.user.web.user.controller.LoginController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;
import static com.ly.mt.core.common.method.UserMethodEnum.B_SHOP_USER_CHECK_USER_MOBILE;
import static com.ly.mt.core.common.method.UserMethodEnum.B_SHOP_USER_REGIST;
import static com.ly.mt.core.common.method.UserMethodEnum.B_SHOP_USER_SET_PASSWORD;

@Api(description = "b端用户注册接口")
@RestController
@RequestMapping("/user/shopUser/user")
public class ShopUserController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ShopUserController.class);

    @ApiOperation("b端校验手机号是否已注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true)

    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "手机号未注册!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!"),
            @ApiResponse(code = 21, message = "手机号已注册! 返回 userId,ShopId,请完善基本信息"),
            @ApiResponse(code = 24, message = "未设置登陆密码"),
    })

    @PostMapping("/checkShopUserMobile")
    public JSONObject checkUserMobile(@RequestParam(value = "mobile") String mobile) {
        // 校验参数
        if (StringUtil.isEmpty(mobile) || !StringUtil.isPhoneNumber(mobile)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", mobile);

        return callUserServer(B_SHOP_USER_CHECK_USER_MOBILE, jsonObject);
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
    public JSONObject shopUserRegist(@RequestParam(value = "mobile",required = true) String mobile,
                                     @RequestParam(value="dynamicCode",required = true)String dynamicCode,
                                     @RequestParam(value="verifyCode",required = true)String verifyCode,
                                     @RequestParam(value = "sourceFlag",required = false) String sourceFlag,
                                     @RequestParam(value = "quickType",required = false) String quickType,
                                     @RequestParam(value = "inviteCode",required = false)String inviteCode){

        // 校验参数
        if (StringUtil.isEmpty(mobile) ||
                !StringUtil.isPhoneNumber(mobile) ||
                StringUtil.isEmpty(dynamicCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", mobile);
        jsonObject.put("dynamicCode", dynamicCode);
        jsonObject.put("sourceFlag", (sourceFlag == null)?"":sourceFlag);
        jsonObject.put("inviteCode", inviteCode);
        jsonObject.put("quickType", quickType);
        jsonObject.put("verifyCode", verifyCode);
        return callUserServer(B_SHOP_USER_REGIST, jsonObject);
    }


    @ApiOperation("b端设置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",paramType = "query",required = true),
            @ApiImplicitParam(name = "password",value = "密码",paramType = "insert",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = '0',message = "操作成功"),
            @ApiResponse(code = '1',message = "系统异常"),
    })
    @PostMapping("/setPassword")
    public JSONObject setPassword(@RequestParam(value = "password",required = true) String password,
                                  @RequestParam(value = "userId",required = true)String userId){
        //校验参数
        if(StringUtil.isEmpty(password) || StringUtil.isEmpty(userId)){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject param = new JSONObject();
        param.put("userId",userId);
        param.put("password",password);
        return callUserServer(B_SHOP_USER_SET_PASSWORD,param);
    }

}


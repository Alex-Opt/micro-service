package com.ly.mt.mall.h5.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.h5.user.service.UserService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "用户接口")
@RestController
@RequestMapping("/mall/h5/user")
public class UserController {
    @Resource
    UserService service;

    @ApiOperation("校验账号是否已注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "登录帐号", required = true, paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "已注册!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 4, message = "未注册!")
    })
    @PostMapping("/checkLoginName")
    public ResponseJson checkLoginName(@RequestParam(value = "loginName") String loginName) {
        // 校验参数
        if (StringUtil.isEmpty(loginName)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.checkLoginName(loginName);
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


    @ApiOperation(value = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户名", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "动态验证码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "注册成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/regist")
    public ResponseJson regist(@RequestParam(value = "loginName") String loginName,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "mobile") String mobile,
                               @RequestParam(value = "dynamicCode") String dynamicCode) {
        // 校验参数
        if (StringUtil.isEmpty(loginName)
                || !StringUtil.isPhoneNumber(mobile)
                || StringUtil.isEmpty(password)
                || StringUtil.isEmpty(dynamicCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.regist(loginName, password, mobile, dynamicCode);
    }


    @ApiOperation("身份认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "姓名", paramType = "query", required = true),
            @ApiImplicitParam(name = "idCard", value = "身份证号码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "认证成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/authentication")
    public ResponseJson authentication(@RequestParam(value = "userName") String userName,
                                       @RequestParam(value = "idCard") String idCard) {
        // 校验参数
        if (StringUtil.isEmpty(userName) || !StringUtil.isIdCard(idCard)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.authentication(userName, idCard);
    }


    @ApiOperation(value = "头像修改", notes = "修改成功后返回result：图片的显示路径")
    @ApiResponses({
            @ApiResponse(code = 0, message = "修改成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/uploadAvatarPic")
    public ResponseJson uploadAvatarPic(@ApiParam(value = "头像图片", name = "file", required = true) MultipartFile file) {
        // 校验参数
        if (null == file) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.uploadAvatarPic(file);
    }


    @ApiOperation("绑定手机")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "验证码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "修改成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/bindMobile")
    public ResponseJson bindMobile(@RequestParam(value = "mobile") String mobile,
                                   @RequestParam(value = "dynamicCode") String dynamicCode) {
        // 校验参数
        if (!StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", mobile);
        jsonObject.put("dynamicCode", dynamicCode);
        return service.bindMobile(mobile, dynamicCode);
    }


    @ApiOperation("修改信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provinceCode", value = "省代码", paramType = "query", required = true),
            @ApiImplicitParam(name = "provinceName", value = "省名称", paramType = "query", required = true),
            @ApiImplicitParam(name = "cityCode", value = "市代码", paramType = "query", required = true),
            @ApiImplicitParam(name = "cityName", value = "市名称", paramType = "query", required = true),
            @ApiImplicitParam(name = "districtCode", value = "区代码", paramType = "query", required = true),
            @ApiImplicitParam(name = "districtName", value = "区名称", paramType = "query", required = true),
            @ApiImplicitParam(name = "birthday", value = "生日", dataType = "datetime", paramType = "query", required = true),
            @ApiImplicitParam(name = "sex", value = "性别:0-女,1-男", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "修改成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/updateInfo")
    public ResponseJson updateInfo(@RequestParam(value = "provinceCode") String provinceCode,
                                   @RequestParam(value = "cityCode") String cityCode,
                                   @RequestParam(value = "districtCode") String districtCode,
                                   @RequestParam(value = "birthday") String birthday,
                                   @RequestParam(value = "sex") String sex) {
        // 校验参数
        if (StringUtil.isEmpty(provinceCode)
                || StringUtil.isEmpty(cityCode)
                || StringUtil.isEmpty(districtCode)
                || StringUtil.isEmpty(birthday)
                || StringUtil.isEmpty(sex)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.updateInfo(provinceCode, cityCode, districtCode, birthday, sex);
    }


    @ApiOperation(value = "获取用户信息", notes = "操作成功后返回result实体")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/loadInfo")
    public ResponseJson loadInfo() {
        return service.loadInfo();
    }


    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "新密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "动态验证码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/modifyPassword")
    public ResponseJson modifyPassword(@RequestParam(value = "password") String password,
                                       @RequestParam(value = "mobile") String mobile,
                                       @RequestParam(value = "dynamicCode") String dynamicCode) {
        // 校验参数
        if (StringUtil.isEmpty(password) || !StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.modifyPassword(password, mobile, dynamicCode);
    }


    @ApiOperation("修改默认帐号")
    @ApiImplicitParam(name = "loginName", value = "帐号", paramType = "query", required = true)
    @ApiResponses({
            @ApiResponse(code = 0, message = "修改成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/updateLoginName")
    public ResponseJson updateLoginName(@RequestParam(value = "loginName") String loginName) {
        // 校验参数
        if (StringUtil.isEmpty(loginName)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        return service.updateLoginName(loginName);
    }
}
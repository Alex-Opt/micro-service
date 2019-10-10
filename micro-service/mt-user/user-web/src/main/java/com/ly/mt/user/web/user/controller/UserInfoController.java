package com.ly.mt.user.web.user.controller;

import com.alibaba.fastjson.JSONObject;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.common.constant.FilePathEnum.FILE_PATH_AVATAR;
import static com.ly.mt.core.common.constant.ResultCodeEnum.*;
import static com.ly.mt.core.common.method.UserMethodEnum.*;

@Api(description = "用户信息操作接口")
@RestController
@RequestMapping("/user/info")
public class UserInfoController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    @ApiOperation("身份认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "姓名", paramType = "query", required = true),
            @ApiImplicitParam(name = "idCard", value = "身份证号码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "认证成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/authentication")
    public JSONObject authentication(@RequestParam(value = "userName") String userName,
                                     @RequestParam(value = "idCard") String idCard) {
        // 校验参数
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(idCard) || !StringUtil.isIdCard(idCard)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", userName);
        jsonObject.put("idCard", idCard);
        return callUserServer(USER_INFO_AUTHENTICATION, jsonObject);
    }


    @ApiOperation(value = "头像修改", notes = "修改成功后返回result图片信息，其中url为图片的显示路径")
    @ApiResponses({
            @ApiResponse(code = 0, message = "修改成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/uploadAvatarPic")
    public JSONObject uploadAvatarPic(@ApiParam(value = "头像图片", name = "file", required = true) MultipartFile file) {
        // 校验参数
        if (null == file) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        String uploadJson;
        try {
            uploadJson = ossServer.upload(file, FILE_PATH_AVATAR, null);
        } catch (Exception e) {
            LOGGER.error("文件上传阿里云出错：", e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        return callUserServer(USER_INFO_UPLOAD_AVATAR_PIC, JSONObject.parseObject(uploadJson));
    }


    @ApiOperation("绑定手机")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "验证码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "修改成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/bindMobile")
    public JSONObject bindMobile(@RequestParam(value = "mobile") String mobile,
                                 @RequestParam(value = "dynamicCode") String dynamicCode) {
        // 校验参数
        if (StringUtil.isEmpty(mobile) || !StringUtil.isPhoneNumber(mobile) || StringUtil.isEmpty(dynamicCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", mobile);
        jsonObject.put("dynamicCode", dynamicCode);
        return callUserServer(USER_INFO_BIND_MOBILE, jsonObject);
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
    public JSONObject updateInfo(@RequestParam(value = "provinceCode") String provinceCode,
                                 @RequestParam(value = "provinceName") String provinceName,
                                 @RequestParam(value = "cityCode") String cityCode,
                                 @RequestParam(value = "cityName") String cityName,
                                 @RequestParam(value = "districtCode") String districtCode,
                                 @RequestParam(value = "districtName") String districtName,
                                 @RequestParam(value = "birthday") String birthday,
                                 @RequestParam(value = "sex") String sex) {
        // 校验参数
        if (StringUtil.isEmpty(provinceCode)
                || StringUtil.isEmpty(provinceName)
                || StringUtil.isEmpty(cityCode)
                || StringUtil.isEmpty(cityName)
                || StringUtil.isEmpty(districtCode)
                || StringUtil.isEmpty(districtName)
                || StringUtil.isEmpty(birthday)
                || StringUtil.isEmpty(sex)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("provinceCode", provinceCode);
        jsonObject.put("provinceName", provinceName);
        jsonObject.put("cityCode", cityCode);
        jsonObject.put("cityName", cityName);
        jsonObject.put("districtCode", districtCode);
        jsonObject.put("districtName", districtName);
        jsonObject.put("birthday", birthday);
        jsonObject.put("sex", sex);
        return callUserServer(USER_INFO_UPDATE_INFO, jsonObject);
    }


    @ApiOperation(value = "获取用户信息", notes = "操作成功后返回result实体")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/loadInfo")
    public JSONObject loadInfo() {
        return callUserServer(USER_INFO_LOAD_INFO_BY_LOGIN_ID, new JSONObject());
    }


    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "新密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "动态验证码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/modifyPassword")
    public JSONObject modifyPassword(@RequestParam(value = "password") String password,
                                     @RequestParam(value = "mobile") String mobile,
                                     @RequestParam(value = "dynamicCode") String dynamicCode) {
        // 校验参数
        if (StringUtil.isEmpty(password) || StringUtil.isEmpty(mobile) || StringUtil.isEmpty(dynamicCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password", password);
        jsonObject.put("mobile", mobile);
        jsonObject.put("dynamicCode", dynamicCode);
        return callUserServer(USER_INFO_MODIFY_PASSWORD, jsonObject);
    }

    @ApiOperation("修改用户名")
    @ApiImplicitParam(name = "loginName", value = "用户名", paramType = "query", required = true)
    @ApiResponses({
            @ApiResponse(code = 0, message = "修改成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/updateLoginName")
    public JSONObject updateLoginName(@RequestParam(value = "loginName") String loginName) {
        // 校验参数
        if (StringUtil.isEmpty(loginName)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginName", loginName);
        return callUserServer(USER_INFO_UPDATE_LOGINNAME, jsonObject);
    }


    @ApiOperation("保存用户clientId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "query", required = true),
            @ApiImplicitParam(name = "clientId", value = "消息推送clientId", paramType = "query", required = true),
    })

    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/saveClientId")
    public JSONObject saveClientId(@RequestParam(value = "userId") String userId,
                                   @RequestParam(value = "clientId") String clientId,
                                   HttpServletRequest request){
        JSONObject param = new JSONObject();
        if(StringUtil.isEmpty(userId) || StringUtil.isEmpty(clientId)){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_NEED);
        }
        param.put("userId",userId);
        param.put("clientId",clientId);
        return callUserServer(USER_INFO_SAVE_CLIENTID,param);
    }
}
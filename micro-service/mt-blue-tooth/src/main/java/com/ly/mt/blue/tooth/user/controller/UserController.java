package com.ly.mt.blue.tooth.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.base.util.JsonUtils;
import com.ly.mt.blue.tooth.user.service.UserService;
import com.ly.mt.blue.tooth.user.vo.BlueToothCertficationOcrResultVo;
import com.ly.mt.blue.tooth.user.vo.BlueToothUserCertficationVo;
import com.ly.mt.blue.tooth.user.vo.BlueToothUserVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;


@Api(description = "用户信息操作接口")
@RestController
@RequestMapping("/bluetooth/user")
public class UserController {
    private final static Logger Logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    UserService userService;

    @ApiOperation(value = "查询用户是否注册", notes = "操作成功后返回status 0:未注册 1:已注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "手机号", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "登录名未注册!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @CustomLog
    @PostMapping("/checkUserLoginName")
    public ResponseJson checkUserLoginName(@RequestParam(value = "login_name") String loginName) {
        // 校验参数
        if (StringUtil.isEmpty(loginName) || !StringUtil.isPhoneNumber(loginName)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        try {
            return userService.checkUserLoginName(loginName);
        } catch (Exception e) {
            Logger.error("蓝牙APP-查询用户是否注册出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "蓝牙APP注册", notes = "操作成功后返回code:0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户名", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "动态验证码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "注册成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @CustomLog
    @PostMapping("/bluetoothRegister")
    public ResponseJson buletoothRegister(@RequestParam(value = "loginName") String loginName,
                                          @RequestParam(value = "password") String password,
                                          @RequestParam(value = "dynamicCode") String dynamicCode) {
        // 校验参数
        if (!StringUtil.isPhoneNumber(loginName)
                || StringUtil.isEmpty(password)
                || StringUtil.isEmpty(dynamicCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数不正确!");
        }
        if (!JsonUtils.validatePassword(password)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "密码格式不正确!");
        }
        try {
            return userService.buletoothRegister(loginName, password, dynamicCode);
        } catch (Exception e) {
            Logger.error("蓝牙APP-账户密码注册出错:", e);
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "注册失败!");
        }
    }

    @ApiOperation(value = "修改用户个人信息", notes = "操作成功后返回code:0 \n 性 别 0：男  1:女 生日格式:2018-01-01 10:01:01")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickName", value = "昵称", paramType = "query", required = false),
            @ApiImplicitParam(name = "sex", value = "性别", paramType = "query", required = false),
            @ApiImplicitParam(name = "birthday", value = "生日", paramType = "query", required = false),
            @ApiImplicitParam(name = "avatarPicSrc", value = "头像", paramType = "query", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "修改成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @CustomLog
    @PostMapping("/modifyUserInfo")
    public ResponseJson modifyUserInfo(@RequestParam(value = "nickName", required = false) String nickName,
                                       @RequestParam(value = "sex", required = false) String sex,
                                       @RequestParam(value = "birthday", required = false) String birthday) {
        // 校验参数
        if (StringUtil.isEmpty(nickName)
                && StringUtil.isEmpty(sex)
                && StringUtil.isEmpty(birthday)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        try {
            return userService.modifyUserInfo(nickName, sex, birthday);
        } catch (Exception e) {
            Logger.error("蓝牙APP-修改个人信息出错:", e);
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "修改失败,稍后再试！");
        }
    }

    @ApiOperation(value = "重置密码", notes = "操作成功后返回code:0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "验证码", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "新密码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "重置成功!"),
            @ApiResponse(code = 1, message = "重置失败!"),
    })
    @CustomLog
    @PostMapping("/resetPwd")
    public ResponseJson resetPwd(@RequestParam(value = "mobile") String mobile,
                                 @RequestParam(value = "dynamicCode") String dynamicCode,
                                 @RequestParam(value = "password") String password) {
        // 校验参数
        if (StringUtil.isPhoneNumber(password)
                || StringUtil.isEmpty(dynamicCode)
                || StringUtil.isEmpty(password)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数不正确");
        }
        if (!JsonUtils.validatePassword(password)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "密码格式不正确!");
        }
        try {
            return userService.resetPwd(mobile, dynamicCode, password);
        } catch (Exception e) {
            Logger.error("蓝牙APP-重置密码出错:", e);
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "重置失败");
        }
    }

    @ApiOperation(value = "修改用户密码", notes = "操作成功后返回code:0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "confirmPassword", value = "确认密码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "修改成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @CustomLog
    @PostMapping("/modifyUserPwd")
    public ResponseJson modifyUserPwd(@RequestParam(value = "password", required = true) String password,
                                      @RequestParam(value = "confirmPassword", required = true) String confirmPassword) {
        // 校验参数
        if (StringUtil.isEmpty(password)
                || StringUtil.isEmpty(confirmPassword)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数不正确");
        }
        //密码与确认密码一致
        if (!password.equals(confirmPassword)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "密码与确认密码不一致");
        }
        if (!JsonUtils.validatePassword(password)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "密码格式不正确!");
        }
        try {
            return userService.modifyPassword(password);
        } catch (Exception e) {
            Logger.error("蓝牙APP-修改密码出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "头像修改", notes = "修改成功后返回result：图片的显示路径")
    @ApiResponses({
            @ApiResponse(code = 0, message = "修改成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @CustomLog
    @PostMapping("/uploadAvatarPic")
    public ResponseJson uploadAvatarPic(@ApiParam(value = "头像图片", name = "file", required = true) MultipartFile file) {
        // 校验参数
        if (null == file) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        try {
            return userService.uploadAvatarPic(file);
        } catch (Exception e) {
            Logger.error("蓝牙APP-头像修改出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "获取用户信息", notes = "操作成功后返回result实体")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @CustomLog
    @PostMapping("/loadInfo")
    public ResponseJson<BlueToothUserVo> loadInfo() {
        try {
            return userService.loadUserInfo();
        } catch (Exception e) {
            Logger.error("蓝牙APP-加载用户信息出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "修改绑定手机号/效验验证码是否正确", notes = "操作成功后返回code:0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "验证码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @CustomLog
    @PostMapping("/validateCode")
    public ResponseJson validateCode(@RequestParam(value = "phone", required = true) String phone,
                                     @RequestParam(value = "dynamicCode", required = true) String dynamicCode) {
        try {
            return userService.validateCode(phone, dynamicCode);
        } catch (Exception e) {
            Logger.error("蓝牙APP-效验验证码是否正确出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "更换绑定手机号", notes = "操作成功后返回code:0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "验证码", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @CustomLog
    @PostMapping("/modifyBindPhone")
    public ResponseJson modifyBindPhone(@RequestParam(value = "phone", required = true) String phone,
                                        @RequestParam(value = "dynamicCode", required = true) String dynamicCode) {
        try {
            return userService.modifyBindPhone(phone, dynamicCode);
        } catch (Exception e) {
            Logger.error("蓝牙APP-更换绑定手机号出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 文件会自动绑定到MultipartFile中
     *
     * @param
     * @return
     */
    @ApiOperation("用户OCR图像识别身份证并上传(OCR图像识别身份证)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageFront", value = "身份证正面", paramType = "query", required = true),
            @ApiImplicitParam(name = "imageBack", value = "身份证反面", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!返回身份信息(真实姓名，身份证号,正反面身份证url)"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @CustomLog
    @PostMapping("/ocrSacnIdCard")
    ResponseJson<BlueToothCertficationOcrResultVo> ocrSacnIdCard(@ApiParam(value = "身份证正面", name = "imageFront", required = true) MultipartFile imageFront,
                                                                 @ApiParam(value = "身份证反面", name = "imageBack", required = true) MultipartFile imageBack) {
        try {
            MultipartFile[] multipartFiles = new MultipartFile[2];
            multipartFiles[0] = imageFront;
            multipartFiles[1] = imageBack;
            String frontImage = JsonUtils.getImageBase64EncodeStr(imageFront);
            String backImage = JsonUtils.getImageBase64EncodeStr(imageBack);
            String frontExt = imageFront.getOriginalFilename().substring(imageFront.getOriginalFilename().lastIndexOf(".") + 1);
            String backExt = imageBack.getOriginalFilename().substring(imageBack.getOriginalFilename().lastIndexOf(".") + 1);
            return userService.scanIdCard(frontImage, backImage, frontExt, backExt, multipartFiles);
        } catch (Exception e) {
            Logger.error("身份证扫描异常", e.getMessage());
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
    }

    @ApiOperation("用户实名认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "realName", value = "真实姓名", paramType = "query", required = true),
            @ApiImplicitParam(name = "idcard", value = "身份证号码", paramType = "query", required = true),
            @ApiImplicitParam(name = "mobile", value = "电话号码", paramType = "query", required = true),
            @ApiImplicitParam(name = "idCardFront", value = "身份证正面url", paramType = "query", required = true, defaultValue = ""),
            @ApiImplicitParam(name = "idCardBack", value = "身份证反面url", paramType = "query", required = true, defaultValue = ""),
    })
    @CustomLog
    @PostMapping("/certification")
    ResponseJson<BlueToothUserCertficationVo> certification(
            @RequestParam(value = "realName", required = true) String realName,
            @RequestParam(value = "idcard", required = true) String idcard,
            @RequestParam(value = "mobile", required = true) String mobile,
            @RequestParam(value = "idCardFront", required = true) String idCardFront,
            @RequestParam(value = "idCardBack", required = true) String idCardBack) {
        if (StringUtil.isEmpty(realName) || StringUtil.isEmpty(idcard) || !StringUtil.isPhoneNumber(mobile)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        JSONObject paramsJsonObject = new JSONObject();
        paramsJsonObject.put("name", realName);
        paramsJsonObject.put("idcard", idcard);
        paramsJsonObject.put("mobile", mobile);
        paramsJsonObject.put("idCardFront", idCardFront);
        paramsJsonObject.put("idCardBack", idCardBack);
        //调用三要素检测
        return userService.threeElementCheck(paramsJsonObject);
    }
}
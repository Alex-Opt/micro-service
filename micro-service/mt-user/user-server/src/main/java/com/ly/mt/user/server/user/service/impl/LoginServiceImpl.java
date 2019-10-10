package com.ly.mt.user.server.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.entity.user.UserLoginVo;
import com.ly.mt.core.common.util.*;
import com.ly.mt.user.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.user.server.user.service.LoginService;
import com.ly.mt.user.server.user.mapper.LoginServiceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.common.constant.RedisEnum.ENTITY_USER_LOGIN_NAME_REDIS;
import static com.ly.mt.core.common.constant.RedisEnum.ENTITY_USER_MOBILE_REDIS;
import static com.ly.mt.core.common.constant.ResultCodeEnum.*;
import static com.ly.mt.core.common.constant.SmsTemplateEnum.SMS_TEMPLATE_CODE_LOGIN;

/**
 * @Description 用户信息操作接口
 * @Author taoye
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
    @Resource
    LoginServiceMapper mapper;

    /**
     * @Description 账号密码登录
     * @Author taoye
     */
    @Override
    public JSONObject nameLogin(String json) throws Exception {
        UserLoginVo user = JSONObject.parseObject(json, UserLoginVo.class);
        // 校验用户是否存在
        User sysUser = getUser(user);
        if (null == sysUser) {
            return JsonUtil.getErrorJson(RESULT_CODE_LOGIN_ERROR);
        }
        // 校验密码
        if (!MD5Util.md5(user.getPassword()).equals(sysUser.getPassword())) {
            return JsonUtil.getErrorJson(RESULT_CODE_LOGIN_ERROR);
        }
        UserLoginVo result = getResultUser(sysUser);
        return JsonUtil.getSuccessJson(result);
    }


    /**
     * @Description 手机号登录
     * @Author taoye
     */
    @Override
    public JSONObject mobileLogin(String json) throws Exception {
        UserLoginVo user = JSONObject.parseObject(json, UserLoginVo.class);
        // 校验动态验证码
        String redisCode = redisServer.get(SMS_TEMPLATE_CODE_LOGIN, user.getMobile());
        if (StringUtil.isEmpty(redisCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
        }
        if (!redisCode.equals(user.getDynamicCode())) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
        }
        User sysUser = getUser(user);
        UserLoginVo result = getResultUser(sysUser);
        return JsonUtil.getSuccessJson(result);
    }


    /**
     * @Description 查询用户信息
     * @Author taoye
     */
    private User getUser(UserLoginVo user) {
        // 查询用户手机缓存
        String userJson = redisServer.get(ENTITY_USER_MOBILE_REDIS, user.getMobile());
        if (StringUtil.isEmpty(userJson)) {
            // 查询用户账号缓存
            userJson = redisServer.get(ENTITY_USER_LOGIN_NAME_REDIS, user.getLoginName());
            if (StringUtil.isEmpty(userJson)) {
                // 查询数据库
                User sysUser = mapper.getUser(user);
                if (null != sysUser) {
                    redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, sysUser.getMobile(), sysUser);
                    redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, sysUser.getLoginName(), sysUser);
                    return sysUser;
                } else {
                    return null;
                }
            }
        }
        return JSONObject.parseObject(userJson, User.class);
    }


    /**
     * @Description 获取登录返回用户信息
     * @Author taoye
     */
    private UserLoginVo getResultUser(User usre) {
        UserLoginVo reslut = new UserLoginVo();
        reslut.setId(usre.getId());
        reslut.setLoginName(usre.getLoginName());
        reslut.setUserName(usre.getUserName());
        reslut.setMobile(usre.getMobile());
        return reslut;
    }
}
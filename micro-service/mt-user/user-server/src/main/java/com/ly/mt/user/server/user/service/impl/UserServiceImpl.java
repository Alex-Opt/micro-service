package com.ly.mt.user.server.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.entity.user.UserActivityH5RegistVo;
import com.ly.mt.core.common.entity.user.UserAddressActivityH5RegistVo;
import com.ly.mt.core.common.entity.user.UserMH5RegistVo;
import com.ly.mt.core.common.util.*;
import com.ly.mt.user.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.user.server.user.mapper.UserServiceMapper;
import com.ly.mt.user.server.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.ly.mt.core.common.constant.IdEnum.USER;
import static com.ly.mt.core.common.constant.IdEnum.USER_ADDRESS;
import static com.ly.mt.core.common.constant.RedisEnum.ENTITY_USER_LOGIN_NAME_REDIS;
import static com.ly.mt.core.common.constant.RedisEnum.ENTITY_USER_MOBILE_REDIS;
import static com.ly.mt.core.common.constant.ResultCodeEnum.*;
import static com.ly.mt.core.common.constant.SmsTemplateEnum.SMS_TEMPLATE_CODE_REGIST;
import static com.ly.mt.core.common.dict.DefaultAddressEnum.DEFAULT_ADDRESS;
import static com.ly.mt.core.common.dict.UserStatusEnum.USER_STATUS_USING;
import static com.ly.mt.core.common.dict.ValidStatusEnum.VALID_STATUS_YES;

/**
 * @Description 用户信息操作接口
 * @Author taoye
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Resource
    UserServiceMapper mapper;

    /**
     * @Description 校验账号是否已注册
     * @Author taoye
     */
    @Override
    public JSONObject checkLoginName(String json) throws Exception {
        User user = JSONObject.parseObject(json, User.class);
        String loginName = user.getLoginName();
        String redisUser = redisServer.get(ENTITY_USER_LOGIN_NAME_REDIS, loginName);
        if (StringUtil.isNotEmpty(redisUser)) {
            return JsonUtil.getErrorJson(RESULT_CODE_REGIST_LOGIN_NAME_ERROR);
        }
        user = mapper.getUser(user);
        if (null != user) {
            redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, user.getMobile(), user);
            redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, user.getLoginName(), user);
            return JsonUtil.getErrorJson(RESULT_CODE_REGIST_LOGIN_NAME_ERROR);
        }
        return JsonUtil.getSuccessJson();
    }


    /**
     * @Description 校验手机号是否已注册
     * @Author taoye
     */
    @Override
    public JSONObject checkUserMobile(String json) throws Exception {
        User user = JSONObject.parseObject(json, User.class);
        String mobile = user.getMobile();
        String redisUser = redisServer.get(ENTITY_USER_MOBILE_REDIS, mobile);
        if (StringUtil.isNotEmpty(redisUser)) {
            return JsonUtil.getErrorJson(RESULT_CODE_REGIST_MOBILE_ERROR);
        }
        user = mapper.getUser(user);
        if (null != user) {
            redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, user.getMobile(), user);
            redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, user.getLoginName(), user);
            return JsonUtil.getErrorJson(RESULT_CODE_REGIST_MOBILE_ERROR);
        }
        return JsonUtil.getSuccessJson();
    }


    /**
     * @Description M端H5商城帐号密码注册
     * @Author taoye
     */
    @Override
    public JSONObject mH5NameRegist(String json) throws Exception {
        UserMH5RegistVo user = JSONObject.parseObject(json, UserMH5RegistVo.class);
        // 校验动态验证码
        String mobile = user.getMobile();
        String redisCode = redisServer.get(SMS_TEMPLATE_CODE_REGIST, mobile);
        if (StringUtil.isEmpty(redisCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
        }
        String dynamicCode = user.getDynamicCode();
        if (!dynamicCode.equals(redisCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
        }
        // 保存用户信息
        String id = IdUtil.getId(USER);
        user.setId(id);
        user.setCreateTime(DateUtil.getNowTimeStr());
        user.setPassword(MD5Util.md5(user.getPassword()));
        user.setUserStatus(USER_STATUS_USING.getId());
        mapper.mH5Regist(user);
        redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, user.getMobile(), user);
        redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, user.getLoginName(), user);
        return JsonUtil.getSuccessJson();
    }


    /**
     * @Description M端H5商城手机号注册
     * @Author taoye
     */
    @Override
    public JSONObject mH5MobileRegist(String json) throws Exception {
        UserMH5RegistVo user = JSONObject.parseObject(json, UserMH5RegistVo.class);
        String id = IdUtil.getId(USER);
        user.setId(id);
        user.setLoginName("MT" + id.substring(0, 8));
        user.setPassword(MD5Util.md5(user.getMobile()));
        user.setUserStatus(USER_STATUS_USING.getId());
        user.setCreateTime(DateUtil.getNowTimeStr());
        mapper.mH5Regist(user);
        redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, user.getMobile(), user);
        redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, user.getLoginName(), user);
        return JsonUtil.getSuccessJson();
    }


    /**
     * @Description 活动H5页面注册
     */
    @Override
    @Transactional
    public JSONObject activityH5Regist(String json) throws Exception {
        UserActivityH5RegistVo user = JSONObject.parseObject(json, UserActivityH5RegistVo.class);
        String id = IdUtil.getId(USER);
        user.setId(id);
        String loginName = "MT" + id.substring(0, 8);
        user.setLoginName(loginName);
        user.setCreateTime(DateUtil.getNowTimeStr());
        user.setUserStatus(USER_STATUS_USING.getId());
        user.setPassword(MD5Util.md5(user.getMobile()));
        mapper.activityH5Regist(user);
        // 保存用户收货地址
        UserAddressActivityH5RegistVo address = JSONObject.parseObject(json, UserAddressActivityH5RegistVo.class);
        address.setId(IdUtil.getId(USER_ADDRESS));
        address.setUserId(id);
        address.setReceiveName(user.getUserName());
        address.setReceivePhone(user.getMobile());
        address.setIsDefault(DEFAULT_ADDRESS.getId());
        address.setValidStatus(VALID_STATUS_YES.getId());
        address.setCreateTime(DateUtil.getNowTimeStr());
        mapper.activityH5RegistAddress(address);
        redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, user.getMobile(), user);
        redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, user.getLoginName(), user);
        return JsonUtil.getSuccessJson(user);
    }
}
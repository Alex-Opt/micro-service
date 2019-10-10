package com.ly.mt.user.server.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.basic.BasicFileVo;
import com.ly.mt.core.common.entity.buycar.BuyCar;
import com.ly.mt.core.common.entity.user.UserInfoVo;
import com.ly.mt.core.common.util.DateUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.MD5Util;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.user.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.user.server.user.async.UserInfoServiceAsync;
import com.ly.mt.user.server.user.mapper.UserInfoServiceMapper;
import com.ly.mt.user.server.user.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.common.constant.RedisEnum.*;
import static com.ly.mt.core.common.constant.ResultCodeEnum.*;
import static com.ly.mt.core.common.constant.SmsTemplateEnum.SMS_TEMPLATE_CODE_INFO;
import static com.ly.mt.core.common.constant.SmsTemplateEnum.SMS_TEMPLATE_CODE_PASSWORD;

/**
 * @Description 用户信息操作接口
 * @Author taoye
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl implements UserInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Resource
    UserInfoServiceMapper mapper;
    @Resource
    UserInfoServiceAsync async;

    /**
     * @Description 身份认证
     * @Author taoye
     */
    @Override
    public JSONObject authentication(String json) throws Exception {
        UserInfoVo user = JSONObject.parseObject(json, UserInfoVo.class);
        user.setId(getLoginUserId());
        mapper.authentication(user);
        return JsonUtil.getSuccessJson();
    }


    /**
     * @Description 头像修改
     * @Author taoye
     */
    @Override
    public JSONObject uploadAvatarPic(String json) throws Exception {
        BasicFileVo file = JSONObject.parseObject(json, BasicFileVo.class);
        file.setUserId(getLoginUserId());
        mapper.updateUserAvatarPic(file);
        return JsonUtil.getSuccessJson(file);
    }


    /**
     * @Description 绑定手机
     * @Author taoye
     */
    @Override
    public JSONObject bindMobile(String json) throws Exception {
        UserInfoVo user = JSONObject.parseObject(json, UserInfoVo.class);
        // 校验动态验证码
        String redisCode = redisServer.get(SMS_TEMPLATE_CODE_INFO, user.getMobile());
        if (StringUtil.isEmpty(redisCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
        }
        if (!redisCode.equals(user.getDynamicCode())) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
        }
        UserInfoVo vo = new UserInfoVo();
        vo.setId(getLoginUserId());
        vo = mapper.getUserById(vo);
        // 删除旧缓存
        redisServer.del(ENTITY_USER_MOBILE_REDIS, vo.getMobile());
        redisServer.del(ENTITY_USER_LOGIN_NAME_REDIS, vo.getLoginName());
        // 更新用户信息
        user.setId(getLoginUserId());
        mapper.updateUserMobile(user);
        vo = mapper.getUserById(vo);
        redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, vo.getMobile(), vo);
        redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, vo.getLoginName(), vo);
        return JsonUtil.getSuccessJson();
    }


    /**
     * @Description 修改信息
     * @Author taoye
     */
    @Override
    public JSONObject updateInfo(String json) throws Exception {
        UserInfoVo user = JSONObject.parseObject(json, UserInfoVo.class);
        user.setId(getLoginUserId());
        // 更新信息
        mapper.updateUserInfo(user);
        // 缓存信息
        async.refreshRedis(user);
        return JsonUtil.getSuccessJson();
    }


    /**
     * @Description 根据登录用户id获取用户信息
     * @Author taoye
     */
    @Override
    public JSONObject loadInfoByLoginId(String json) throws Exception {
        UserInfoVo user = new UserInfoVo();
        String id = getLoginUserId();
        LOGGER.info("id=", id);
        user.setId(id);
        user = mapper.getUserById(user);
        user.setProvinceName(getAreaMname(redisServer.get(REDIS_BASIC_AREA_ENTITY_M_ID, user.getProvinceCode())));
        user.setCityName(getAreaMname(redisServer.get(REDIS_BASIC_AREA_ENTITY_M_ID, user.getCityCode())));
        user.setDistrictName(getAreaMname(redisServer.get(REDIS_BASIC_AREA_ENTITY_M_ID, user.getDistrictCode())));
        // 不返回密码
        user.setPassword(null);
        user.setBirthday(StringUtil.isNotEmpty(user.getBirthday()) ? DateUtil.dateFormat(user.getBirthday()) : null);


        //登录用户购物车商品数量
        BuyCar buyCar = getCarFromRedis(id);
        user.setBuyCarSkuNum(buyCar == null ? "0" : String.valueOf(buyCar.getItems().size()));


        return JsonUtil.getSuccessJson(user);
    }

    /**
     * @Description 根据用户手机号获取用户信息
     * @Author taoye
     */
    @Override
    public JSONObject loadInfoByUserMobile(String json) throws Exception {
        UserInfoVo user = JSONObject.parseObject(json, UserInfoVo.class);
        List<UserInfoVo> users = mapper.getUserByMobile(user);
        if(users != null && users.size() >0){
            user.setProvinceName(getAreaMname(redisServer.get(REDIS_BASIC_AREA_ENTITY_M_ID, users.get(0).getProvinceCode())));
            user.setCityName(getAreaMname(redisServer.get(REDIS_BASIC_AREA_ENTITY_M_ID, users.get(0).getCityCode())));
            user.setDistrictName(getAreaMname(redisServer.get(REDIS_BASIC_AREA_ENTITY_M_ID, users.get(0).getDistrictCode())));
            // 不返回密码
            user.setPassword(null);
            return JsonUtil.getSuccessJson(user);
        }else{
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    /**
     * @Description 修改密码
     * @Author taoye
     */
    @Override
    public JSONObject modifyPassword(String json) throws Exception {
        UserInfoVo user = JSONObject.parseObject(json, UserInfoVo.class);
        // 校验动态验证码
        String mobile = user.getMobile();
        String redisCode = redisServer.get(SMS_TEMPLATE_CODE_PASSWORD, mobile);
        if (StringUtil.isEmpty(redisCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
        }
        if (!user.getDynamicCode().equals(redisCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
        }
        // 更新密码
        user.setPassword(MD5Util.md5(user.getPassword()));
        mapper.updatePassword(user);
        List<UserInfoVo> users = mapper.getUserByMobile(user);
        async.refreshRedis(users.get(0));

        return JsonUtil.getSuccessJson();
    }

    /**
     * @Description 补全用户信息
     * @Author taoye
     */
    @Override
    public JSONObject perfectInfo(String json) throws Exception {
        UserInfoVo user = JSONObject.parseObject(json, UserInfoVo.class);
        user.setId(getLoginUserId());
        user.setPassword(MD5Util.md5(user.getPassword()));
        // 更新用户信息
        mapper.perfectInfo(user);
        async.refreshRedis(user);
        return JsonUtil.getSuccessJson(user);
    }

    @Override
    public JSONObject updateLoginName(String json) throws Exception {
        UserInfoVo user = JSONObject.parseObject(json, UserInfoVo.class);
        user.setId(getLoginUserId());
        // 检验loginName是否已存在
        List list = mapper.queryUserByLoginName(user);
        if (null != list && !list.isEmpty()) {
            return JsonUtil.getErrorJson(RESULT_CODE_REGIST_LOGIN_NAME_ERROR);
        }
        //修改loginName
        mapper.updateLoginName(user);
        // 缓存信息
        async.refreshRedis(user);
        return JsonUtil.getSuccessJson();
    }

    @Override
    public JSONObject saveClientId(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        mapper.updateClientId(jsonObject.getLong("userId"), jsonObject.getString("clientId"));
        return JsonUtil.getSuccessJson();
    }


    /**
     * 从redis里获得购物车信息
     *
     * @param buyerId
     * @return
     * @throws Exception
     */
    private BuyCar getCarFromRedis(String buyerId) throws Exception {
        BuyCar car = null;
        if (StringUtil.isEmpty(buyerId)) {
            return null;
        } else {
            String jsonObject = redisServer.get(ENTITY_CAR_BUYER_ID_REDIS, buyerId);
            car = JSONObject.parseObject(jsonObject, BuyCar.class);
        }
        return car;
    }

    private String getAreaMname(String area) {
        try {
            return StringUtil.isEmpty(area) ? null : JSONObject.parseObject(area).get("mName").toString();
        } catch (Exception e) {
            LOGGER.error("处理用户地址数据异常，异常={}", e.getMessage(), e);
            return null;
        }
    }
}
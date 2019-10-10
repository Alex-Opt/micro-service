//package com.ly.mt.cabinet.c.user.service.lmpl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.gzg.core.common.entity.user.UserGzgRegisVo;
//import com.gzg.core.common.util.*;
//import com.gzg.server.base.service.impl.BaseServiceImpl;
//import com.gzg.server.user.mapper.UserServiceMapper;
//import com.gzg.server.user.service.GzgUserService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//import static com.gzg.core.common.constant.IdEnum.USER;
//import static com.gzg.core.common.constant.GzgRedisEnum.ENTITY_USER_MOBILE_REDIS;
//import static com.gzg.core.common.constant.ResultCodeEnum.*;
//import static com.gzg.core.common.constant.SmsTemplateEnum.SMS_TEMPLATE_CODE_BLUETOOTH_REGIST;
//import static com.gzg.core.common.dict.UserStatusEnum.USER_STATUS_USING;
//
//
///**
// * @Description 用户信息操作接口
// * @Author taoye
// */
//@Service
//public class GzgUserServiceImpl extends BaseServiceImpl implements GzgUserService {
//    @Resource
//    UserServiceMapper mapper;
//
//    /**
//     * @Description 校验账号是否已注册
//     * @Author taoye
//     */
//    @Override
//    public JSONObject checkLoginName(String json) throws Exception {
////        UserGzgRegisVo user = JSONObject.parseObject(json, UserGzgRegisVo.class);
////        String loginName = user.getLoginName();
////        String redisUser = redisServer.get(ENTITY_USER_LOGIN_NAME_REDIS, loginName);
////        if (StringUtil.isNotEmpty(redisUser)) {
////            return JsonUtil.getErrorJson(RESULT_CODE_REGIST_LOGIN_NAME_ERROR);
////        }
////        user = mapper.getUser(user);
////        if (null != user) {
////            redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, user.getMobile(), user);
////            redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, user.getLoginName(), user);
////            return JsonUtil.getErrorJson(RESULT_CODE_REGIST_LOGIN_NAME_ERROR);
////        }
//        return JsonUtil.getSuccessJson();
//    }
//
//    /**
//     * @Description 校验手机号是否已注册
//     * @Author taoye
//     */
//    @Override
//    public JSONObject checkUserMobile(String json) throws Exception {
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        String mobile = jsonObject.getString("mobile");
//        String redisUser = redisServer.get(ENTITY_USER_MOBILE_REDIS, mobile);
//        if (StringUtil.isNotEmpty(redisUser)) {
//            return JsonUtil.getErrorJson(RESULT_CODE_REGIST_MOBILE_ERROR);
//        }
//        UserGzgRegisVo user = new UserGzgRegisVo();
//        user.setMobile(mobile);
//        UserGzgRegisVo user1 = mapper.getUser(user);
//        if (null != user1) {
//            redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, user.getMobile(), user);
//            return JsonUtil.getErrorJson(RESULT_CODE_REGIST_MOBILE_ERROR);
//        }
//        return JsonUtil.getSuccessJson();
//    }
//
//    /**
//     * @Description 用户注册
//     * @Author taoye
//     */
//    @Override
//    public JSONObject gzgMobileRegist(String json) throws Exception {
//        UserGzgRegisVo user = JSONObject.parseObject(json, UserGzgRegisVo.class);
//        // 校验动态验证码
//        String mobile = user.getMobile();
//        String redisCode = redisServer.get(SMS_TEMPLATE_CODE_BLUETOOTH_REGIST, mobile);
//        if (StringUtil.isEmpty(redisCode)) {
//            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
//        }
//        String dynamicCode = user.getDynamicCode();
//        if (!dynamicCode.equals(redisCode)) {
//            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
//        }
//
////      保存用户信息
//        String id = IdUtil.getId(USER);
//        user.setId(id);
//        user.setCreateTime(DateUtil.getNowTimeStr());
//        user.setUserStatus(USER_STATUS_USING.getId());
//        user.setQuickType("5");
//
//        mapper.regist(user);
//        redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, user.getMobile(), user);
////        redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, user.getLoginName(), user);
//        return JsonUtil.getSuccessJson();
//    }
//
//
//}
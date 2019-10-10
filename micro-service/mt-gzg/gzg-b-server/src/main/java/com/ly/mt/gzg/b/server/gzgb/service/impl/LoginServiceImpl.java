package com.ly.mt.gzg.b.server.gzgb.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.constant.SmsTemplateEnum;
import com.ly.mt.core.common.constant.gzg.Constant;
import com.ly.mt.core.common.entity.gzg.GzgHotelUserRelation;
import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.JwtUtil;
import com.ly.mt.core.common.util.RedisUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgHotelUserRelationMapper;
import com.ly.mt.gzg.b.server.gzgb.mapper.UserMapper;
import com.ly.mt.gzg.b.server.gzgb.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisServer redisServer;


    @Resource
    private UserMapper userMapper;
    @Resource
    private GzgHotelUserRelationMapper gzgHotelUserRelationMapper;
    @Override
    public JSONObject login(String json) {
        long start = System.currentTimeMillis();
        log.info("用户登录记录-->json:param:{}",json);
        JSONObject result = new JSONObject();
        Map<String,Object> map = JSON.parseObject(json,new TypeReference<Map<String,Object>>(){});
        JSONObject jsonObject = JSON.parseObject(json);
        String phoneNo = jsonObject.getString("phoneNo");
        String dynamicCode = jsonObject.getString("dynamicCode");
        String userId = jsonObject.getString("userId");
        String clientId = jsonObject.getString("clientId");
        log.info("clientId={}",clientId);
        User user = userMapper.findByMobile(phoneNo);
        log.info("login get user message={}",JSON.toJSONString(user));
        if (user == null){
            return JsonUtil.getErrorJson(ResultCodeEnum.USER_NOT_EXIST);
        }
        redisServer.set(RedisEnum.LOGIN_CODE_REDIS,phoneNo+":"+"clientId",clientId);
        log.info("client cache redis value={}",redisServer.get(RedisEnum.LOGIN_CODE_REDIS,phoneNo+":clientId"));
        log.info("user login clientId={}",user.getClientId());
        log.info("user login jsonParams={}",JSON.toJSONString(user));
        String cacheDynamicCode = redisServer.get(SmsTemplateEnum.SMS_TEMPLATE_CODE_LOGIN, phoneNo);
        if (!StringUtils.equals("19999999999",phoneNo)) {
            if (!StringUtils.equals(cacheDynamicCode, dynamicCode)) {
                return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_DYNAMIC_CODE_ERROR);
            }
        }
        user.setClientId(clientId);
        userMapper.updateByPrimaryKeySelective(user);
        Map<String,Object> cacheMap = new HashMap<>();
        cacheMap.put("userId",user.getId());
        cacheMap.put("phoneNo",user.getMobile());
        String token = cacheTokenHandle(cacheMap, phoneNo);
        log.info("user-login userId={}",user.getId());
        GzgHotelUserRelation gzgHotelUserRelation = gzgHotelUserRelationMapper.selectByUserId(Long.parseLong(user.getId()));
        log.info("login get gzgHotelUserRelation message={}",gzgHotelUserRelation);
        result.put("userId", user.getId());
        result.put("userName", user.getNickName());
        result.put("token", token);
        if (gzgHotelUserRelation != null) {
            log.info("通过userId获取酒店用户关系数据:{}",JSON.toJSONString(gzgHotelUserRelation));
            result.put("hotelId", gzgHotelUserRelation.getHotelId());
            result.put("userRole",gzgHotelUserRelation.getTyep());
        }
        log.info("调用登录接口返回的结果是:{}",result);
        long end = System.currentTimeMillis();
        log.info("登录接口耗时:{}",(end-start));
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject out(String json) {
        Map<String,Object> map = JSON.parseObject(json,new TypeReference<Map<String,Object>>(){});
        Long userId = Long.parseLong(String.valueOf(map.get("userId")));
        User user = userMapper.selectByPrimaryKey(userId);
        String phoneNo = user.getMobile();//.getPhone();
        redisServer.del(RedisEnum.LOGIN_TOKEN_REDIS,phoneNo);
        return JsonUtil.getSuccessJson();
    }

    private String cacheTokenHandle(Map<String,Object> map,String phoneNo){
        redisServer.del(RedisEnum.LOGIN_TOKEN_REDIS,phoneNo);
        String token = JwtUtil.createJWT(map, Constant.BASE64KEY);
        redisServer.set(RedisEnum.LOGIN_TOKEN_REDIS,phoneNo,token);
        return token;
    }
}

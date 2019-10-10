package com.ly.mt.gzg.b.server.gzgb.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.common.constant.IdEnum;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.constant.SmsTemplateEnum;
import com.ly.mt.core.common.constant.gzg.Constant;
import com.ly.mt.core.common.dict.QuickTypeEnum;
import com.ly.mt.core.common.dict.UserStatusEnum;
import com.ly.mt.core.common.entity.gzg.GzgHotelUserRelation;
import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.common.util.IdUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.JwtUtil;
import com.ly.mt.core.common.util.RedisUtil;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgHotelUserRelationMapper;
import com.ly.mt.gzg.b.server.gzgb.mapper.UserMapper;
import com.ly.mt.gzg.b.server.gzgb.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisServer redisServer;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GzgHotelUserRelationMapper gzgHotelUserRelationMapper;

    @Override
    public JSONObject register(String json) {
        JSONObject object = JSON.parseObject(json);
        JSONArray array = object.getJSONArray("phones");
        List<Long> phones = JSON.parseObject(JSON.toJSONString(array),new TypeReference<List>(){});
        phones.forEach(x -> {
            User byMobile = userMapper.findByMobile(String.valueOf(x));
            if (byMobile == null) {
                User user = new User();
                user.setMobile(String.valueOf(x));//.setPhone(phoneNo);
                String id = IdUtil.getId(IdEnum.USER);
                user.setId(id);
                user.setClientId(null);
                user.setQuickType(QuickTypeEnum.GZG_B.getId());
                user.setUserStatus(UserStatusEnum.USER_STATUS_USING.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                user.setCreateTime(sdf.format(new Date()));
                userMapper.insertSelective(user);
            }
        });
        /*log.info("register jsonParameter={}",json);
        Map<String,Object> map = JSON.parseObject(json,new TypeReference<Map<String,Object>>(){});
        String phoneNo = (String) map.get("phoneNo");
        String dynamicCode = (String) map.get("dynamicCode");
        String clientId = (String) map.get("clientId");
        User user = userMapper.findByMobile(phoneNo);//.findByPhone(phoneNo);
        if (user != null){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_REGIST_MOBILE_ERROR);
        }
        //String registerDynamicCodeKey = Constant.USER_REGISTER_DYNAMIC_CODE_PRE+phoneNo;
        String registerDynamicCode = redisServer.get(SmsTemplateEnum.SMS_TEMPLATE_CODE_REGIST, phoneNo);
        //String registerDynamicCode = redisUtil.getCachByKey(registerDynamicCodeKey);
        if (!StringUtils.equals(dynamicCode,registerDynamicCode)){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_DYNAMIC_CODE_ERROR);
        }
        log.info("user register clientId={}",clientId);
        user = new User();
        user.setMobile(phoneNo);//.setPhone(phoneNo);
        String id = IdUtil.getId(IdEnum.USER);
        user.setId(id);
        user.setClientId(clientId);
        user.setQuickType(QuickTypeEnum.GZG_B.getId());
        user.setUserStatus(UserStatusEnum.USER_STATUS_USING.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateTime(sdf.format(new Date()));
        int i = userMapper.insertSelective(user);//.insertSelective(user);
        Map<String,Object> param = new HashMap<>();
        param.put("userId",user.getId());
        param.put("phoneNo",phoneNo);
        String cacheTokenKey = Constant.USER_LOGIN_TOKEN_CACHE_KEY_PRE+user.getId()+"_"+phoneNo;
        String token = cacheTokenHandle(param, cacheTokenKey);
        redisServer.set(RedisEnum.LOGIN_TOKEN_REDIS,phoneNo,token);
        GzgHotelUserRelation gzgHotelUserRelation = gzgHotelUserRelationMapper.selectByUserId(Long.valueOf(user.getId()));
        JSONObject result = new JSONObject();
        result.put("userId", user.getId());
        result.put("userName", user.getNickName());
        result.put("token", token);
        if (gzgHotelUserRelation != null) {
            result.put("hotelId", gzgHotelUserRelation.getHotelId());
            result.put("userRole",user.getUserType());
        }*/
        return JsonUtil.getSuccessJson();
    }

    private String cacheTokenHandle(Map<String,Object> map,String cacheKey){
        String token = JwtUtil.createJWT(map, Constant.BASE64KEY);
        return token;
    }
}


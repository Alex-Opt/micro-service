package com.ly.mt.activity.advertisement.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.advertisement.service.UserService;
import com.ly.mt.activity.advertisement.vo.UserLoginVo;
import com.ly.mt.activity.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.dict.ProjectType;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.*;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.redis.RedisKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_USER;
import static com.ly.mt.core.base.dict.UserStatus.USER_STATUS_USING;
import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.feign.DataCenterMethod.USER_GET_USER_BY_MOBILE;
import static com.ly.mt.core.feign.DataCenterMethod.USER_INSERT;
import static com.ly.mt.core.redis.RedisKey.REDIS_ACTIVITY_STRING_LOGIN_TOKEN_LOGIN_NAME;
import static com.ly.mt.core.redis.RedisKey.REDIS_USER_ENTITY_MOBILE;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * 活动注册业务类
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    //日志
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResponseJson activityH5Regist(JSONObject param, HttpServletRequest request) throws Exception {
        // 校验是否已注册
        redisService.set(RedisKey.LOGIN_CODE_REDIS, "测试是否会删除");
        JSONObject userJsonObject = new JSONObject();
        String mobile = param.getString("mobile");
        String userName = param.getString("userName");
        String quickType = param.getString("quickType");
        String userAddress = param.getString("userAddress");
        String provinceCode = param.getString("provinceCode");
        String provinceName = param.getString("provinceName");
        String cityCode = param.getString("cityCode");
        String cityName = param.getString("cityName");
        String districtName = param.getString("districtName");
        String districtCode = param.getString("districtCode");

        userJsonObject.put("mobile", mobile);
        String result = callDataCenter(DataCenterMethod.USER_GET_USER_BY_MOBILE_ACTIVITY, userJsonObject);
        if (null == result || "".equals(result)) {
            // 未注册时自动注册
            userJsonObject = new JSONObject();
            String userId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_USER);
            userJsonObject.put("id", userId);
            userJsonObject.put("user_name", userName);
            String loginName = "MT" + userId.substring(0, 8);
            userJsonObject.put("login_name", loginName);
            userJsonObject.put("password", MD5Util.md5(mobile));
            userJsonObject.put("mobile", mobile);
            userJsonObject.put("user_status", "10");
            userJsonObject.put("quick_type", quickType);
            userJsonObject.put("province_code", provinceCode);
            userJsonObject.put("project_type", ProjectType.PROJECT_TYPE_ADVERTISMENT.getId());
            userJsonObject.put("city_code", cityCode);
            userJsonObject.put("district_code", districtCode);
            userJsonObject.put("create_time", DateUtil.getNowTimeStr());
            // id, user_name, login_name, password, mobile, user_status, quick_type, create_time
            //插入用户信息
            callDataCenter(DataCenterMethod.USER_INSERT, userJsonObject);
            //插入用户地址
            //id, user_id, user_name, receive_name, receive_phone, user_address, is_default, valid_status, create_time
            JSONObject userAddressJson = new JSONObject();
            userAddressJson.put("id", SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_USER_ADDRESS));
            userAddressJson.put("user_id", userId);
            userAddressJson.put("receive_name", userName);
            userAddressJson.put("receive_phone", mobile);
            userAddressJson.put("user_address", userAddress);
            userAddressJson.put("is_default", "1");
            userAddressJson.put("valid_status", "1");
            userAddressJson.put("province_code", provinceCode);
            userAddressJson.put("province_name", provinceName);
            userAddressJson.put("city_code", cityCode);
            userAddressJson.put("city_name", cityName);
            userAddressJson.put("district_name", districtName);
            userAddressJson.put("district_code", districtCode);
            userAddressJson.put("create_time", DateUtil.getNowTimeStr());
            callDataCenter(DataCenterMethod.USERADDRESS_INSERT, userAddressJson);
            //保存redis
            redisService.setEntity(REDIS_USER_ENTITY_MOBILE, mobile, userJsonObject);
            redisService.setEntity(RedisKey.REDIS_USER_ENTITY_LOGIN_NAME, loginName, userJsonObject);
        }
        // 获取用户信息进行登录
        userJsonObject = new JSONObject();
        userJsonObject.put("mobile", mobile);
        String userJson = callDataCenter(DataCenterMethod.USER_GET_USER_BY_MOBILE, userJsonObject);
        //String getUserResultCode = userJsonObject.getString("code");
        // if (RESPONSE_CODE_ERROR.getCode().equals(getUserResultCode)) {
        if (null == userJson || "".equals(userJson)) {
            // 获取用户信息异常
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        UserLoginVo user = JSONObject.parseObject(userJson, UserLoginVo.class);
        if (null != user) {
            // 保存session
            String ip = saveSession(request, user);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, ip);
        }
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
    }


    /**
     * @Description 用户登录保存session
     * @Author taoye
     */
    private String saveSession(HttpServletRequest request, UserLoginVo user) {
        // 存储常用信息
        String token = UUID.randomUUID().toString();
        String ip = getIpAddress(request);
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        session.setAttribute("userId", user.getId());
        session.setAttribute("loginName", user.getLoginName());
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("mobile", user.getMobile());
        session.setAttribute("ipAddress", ip);
        LOGGER.info("保存session:userId=" + user.getId());
        LOGGER.info("UserServiceImpl.sessionId", session.getId());
        LOGGER.info("UserServiceImpl.saveSession", session.getAttribute("ipAddress"));
        // 刷新session有效时间30分钟
        session.setMaxInactiveInterval(1800);
        // 刷新redis有效时间30分钟
        redisService.set(REDIS_ACTIVITY_STRING_LOGIN_TOKEN_LOGIN_NAME, user.getLoginName(), 30, MINUTES);
        return ip;
    }

    /**
     * @Description 获取ip地址
     * @Author taoye
     */
    private String getIpAddress(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtil.isNotEmpty(ip)) {
            String[] ips = ip.split(",");
            return ips[0];
        }
        return ip;
    }

    /**
     * @Description 校验手机号是否已注册
     * @Author taoye
     */
    @Override
    public ResponseJson checkUserMobile(String mobile) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobile", mobile);
            String userJson = callDataCenter(USER_GET_USER_BY_MOBILE, jsonObject);
            if (StringUtil.isEmpty(userJson)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_NOT_REGIST, "帐号未注册!");
            } else {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "帐号已注册!");
            }
        } catch (Exception e) {
            LOGGER.error("校验手机号是否已注册出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson savePhoneNumber(String mobile, String quickType) {
        try {
            String userStr = redisService.get(REDIS_USER_ENTITY_MOBILE, mobile);
            if (StringUtil.isNotEmpty(userStr)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "手机号已经保存成功!");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobile", mobile);
            String userJson = callDataCenter(USER_GET_USER_BY_MOBILE, jsonObject);
            if (StringUtil.isEmpty(userJson)) {
                String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER);
                jsonObject.put("id", id);
                jsonObject.put("login_name", "MT" + id.substring(0, 8));
                jsonObject.put("password", MD5Util.md5(mobile));
                jsonObject.put("user_status", USER_STATUS_USING.getId());
                jsonObject.put("create_time", DateUtil.getNowTimeStr());
                jsonObject.put("quick_type", quickType);
                userJson = callDataCenter(USER_INSERT, jsonObject);
                redisService.setEntity(REDIS_USER_ENTITY_MOBILE, mobile, userJson);
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userJson);
            } else {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "手机号已经保存成功!");
            }
        } catch (Exception e) {
            LOGGER.error("保存手机号出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}

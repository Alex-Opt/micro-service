package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.user.entity.User;
import com.ly.mt.center.data.user.mapper.UserMapper;
import com.ly.mt.center.data.user.service.UserService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.redis.RedisKey.*;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    UserMapper mapper;

    /**
     * @Description 插入User
     * @Author taoye
     */
    @Override
    public ResponseJson insertUser(JSONObject jsonObject) {
        try {
            User user = JSONObject.toJavaObject(jsonObject, User.class);
            if (StringUtil.isEmpty(user.getId() + "" + "")) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            user.setValid_status("1");//默认1 不然插入用户报错
            mapper.insertUser(user);
            redisService.setEntity(REDIS_USER_ENTITY_ID, user.getId() + "" + "", user);
            redisService.setEntity(REDIS_USER_ENTITY_MOBILE, user.getMobile(), user);
            redisService.setEntity(REDIS_USER_ENTITY_LOGIN_NAME, user.getLogin_name(), user);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, jsonObject);
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl.insertUser出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 根据id删除User
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUser(JSONObject jsonObject) {
        try {
            User user = JSONObject.toJavaObject(jsonObject, User.class);
            if (StringUtil.isEmpty(user.getId() + "") && StringUtil.isEmpty(user.getMobile()) && StringUtil.isEmpty(user.getLogin_name())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUser(user);
            user = mapper.getUser(user);
            redisService.del(REDIS_USER_ENTITY_ID, user.getId() + "");
            redisService.del(REDIS_USER_ENTITY_MOBILE, user.getMobile());
            redisService.del(REDIS_USER_ENTITY_LOGIN_NAME, user.getLogin_name());
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl.deleteUserById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 根据id更新User
     * @Author taoye
     */
    @Override
    public ResponseJson updateUser(JSONObject jsonObject) {
        try {
            User user = JSONObject.toJavaObject(jsonObject, User.class);
            if (StringUtil.isEmpty(user.getId() + "") ) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新id不能为空!");
            }
            LOGGER.info("用户信息0：" + JSONObject.toJSONString(user));
            mapper.updateUser(user);
            LOGGER.info("用户信息1：" + JSONObject.toJSONString(user));
            user = mapper.getUser(user);
            LOGGER.info("用户信息2：" + JSONObject.toJSONString(user));
            redisService.del(REDIS_USER_ENTITY_ID, user.getId() + "");
            redisService.del(REDIS_USER_ENTITY_MOBILE, user.getMobile());
            redisService.del(REDIS_USER_ENTITY_LOGIN_NAME, user.getLogin_name());
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, user);
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl.updateUser出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id更新User
     * @Author WHL
     */
    @Override
    public ResponseJson updateUserById(JSONObject jsonObject) {
        try {
            User user = JSONObject.toJavaObject(jsonObject, User.class);
            if (StringUtil.isEmpty(user.getId() + "") && StringUtil.isEmpty(user.getMobile()) && StringUtil.isEmpty(user.getLogin_name())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空!");
            }
            mapper.updateUserById(user);
            user = mapper.getUserById(user);
            redisService.del(REDIS_USER_ENTITY_ID, user.getId() + "");
            redisService.del(REDIS_USER_ENTITY_MOBILE, user.getMobile());
            redisService.del(REDIS_USER_ENTITY_LOGIN_NAME, user.getLogin_name());
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, user);
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl.updateUserById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 根据id查询User
     * @Author taoye
     */
    @Override
    public ResponseJson getUserById(JSONObject jsonObject) {
        try {
            LOGGER.info("---------根据id查询User-------------入参信息：{}",jsonObject.toString());
            User user = JSONObject.toJavaObject(jsonObject, User.class);
            String id = user.getId() + "";
            String userJson = redisService.get(REDIS_USER_ENTITY_ID, id);
            if (StringUtil.isNotEmpty(userJson)) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userJson);
            }
            LOGGER.info("---------根据id查询User-------------查询的入参：{}",JSONObject.toJSONString(user));
            user = mapper.getUser(user);
            if (null != user) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, user);
            } else {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户信息不存在");
            }
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl.getUserById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 根据mobile查询User
     * @Author taoye
     */
    @Override
    public ResponseJson getUserByMobile(JSONObject jsonObject) {
        try {
            User user = JSONObject.toJavaObject(jsonObject, User.class);
            String mobile = user.getMobile();
            String userJson = redisService.get(REDIS_USER_ENTITY_MOBILE, mobile);
            if (StringUtil.isNotEmpty(userJson)) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userJson);
            }
            user = mapper.getUser(user);
            if (null != user) {
                redisService.setEntity(REDIS_USER_ENTITY_ID, user.getId() + "", user);
                redisService.setEntity(REDIS_USER_ENTITY_MOBILE, mobile, user);
                if(StringUtil.isNotEmpty(user.getLogin_name())) {
                    redisService.setEntity(REDIS_USER_ENTITY_LOGIN_NAME, user.getLogin_name(), user);
                }
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, user);
            } else {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, user);
            }
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl.getUserByMobile出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getUserByMobileForActivity(JSONObject jsonObject) {

        try {
            User user = JSONObject.toJavaObject(jsonObject, User.class);
            String mobile = user.getMobile();
            String userJson = redisService.get(REDIS_USER_ENTITY_MOBILE, mobile);
            if (StringUtil.isNotEmpty(userJson)) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userJson);
            }
            user = mapper.getUser(user);
            if (null != user) {
                redisService.setEntity(REDIS_USER_ENTITY_ID, user.getId() + "", user);
                redisService.setEntity(REDIS_USER_ENTITY_MOBILE, mobile, user);
                if(StringUtil.isNotEmpty(user.getLogin_name())) {
                    redisService.setEntity(REDIS_USER_ENTITY_LOGIN_NAME, user.getLogin_name(), user);
                }
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, user);
            } else {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "用户信息不存在");
            }
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl.getUserByMobile出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据用户手机号查询用户信息，查不到时返回code:0,result:null
     */
    @Override
    public ResponseJson getLoginUserByMobile(JSONObject jsonObject) {
        try {
            User user = JSONObject.toJavaObject(jsonObject, User.class);
            String mobile = user.getMobile();
            String userJson = redisService.get(REDIS_USER_ENTITY_MOBILE, mobile);
            if (StringUtil.isNotEmpty(userJson)) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userJson);
            }
            user = mapper.getUser(user);
            if (null != user) {
                redisService.setEntity(REDIS_USER_ENTITY_ID, user.getId() + "", user);
                redisService.setEntity(REDIS_USER_ENTITY_MOBILE, mobile, user);
                if(StringUtil.isNotEmpty(user.getLogin_name())) {
                    redisService.setEntity(REDIS_USER_ENTITY_LOGIN_NAME, user.getLogin_name(), user);
                }
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, user);
            } else {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, null);
            }
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl.getUserByMobile出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 根据login_name查询User
     * @Author taoye
     */
    @Override
    public ResponseJson getUserByLoginName(JSONObject jsonObject) {
        try {
            User user = JSONObject.toJavaObject(jsonObject, User.class);
            String login_name = user.getLogin_name();
            String userJson = redisService.get(REDIS_USER_ENTITY_LOGIN_NAME, login_name);
            if (StringUtil.isNotEmpty(userJson)) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userJson);
            }
            user = mapper.getUser(user);
            if (null != user) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, user);
            } else {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户信息不存在");
            }
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl.getUserByLoginName出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据wx_open_id查询User
     * @Author zhanglifeng
     */
    @Override
    public ResponseJson getUserByWxOpenId(JSONObject jsonObject) {
        try {
            User user = JSONObject.toJavaObject(jsonObject, User.class);
            String wx_open_id = user.getWx_open_id();
            String userJson = redisService.get(REDIS_USER_ENTITY_LOGIN_OPEN_ID, wx_open_id);
            if (StringUtil.isNotEmpty(userJson)) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userJson);
            }
            user = mapper.getUser(user);
            if (null != user) {
                redisService.setEntity(REDIS_USER_ENTITY_LOGIN_OPEN_ID, wx_open_id, user);
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, user);
            } else {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, null);
            }
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl.getUserByWxOpenId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询User
     * @Author whl
     */
    @Override
    public ResponseJson getUserByCondtions(JSONObject jsonObject) {
        try {
            Map<String, Object> result = new HashMap<>(16);
            User user = JSONObject.toJavaObject(jsonObject, User.class);
            List<User> userList = mapper.getUserByCondtions(user);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userList);
        } catch (Exception e) {
            LOGGER.error("UserServiceImpl.getUserByCondtions出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson setPassword(JSONObject jsonObject) {
        try{
            String userId = jsonObject.getString("user_id");
            String password = jsonObject.getString("password");
            if(StringUtil.isEmpty(userId) || StringUtil.isEmpty(password)){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"参数条件不能为空");
            }
            User user = new User();
            user.setPassword(password);
            user.setId(Long.parseLong(userId));
            mapper.setPassword(user);
            User mapperUser = mapper.getUser(user);
            LOGGER.info("user info:{}", mapperUser);
            redisService.setEntity(REDIS_USER_ENTITY_ID, mapperUser.getId() + "", mapperUser);
            redisService.setEntity(REDIS_USER_ENTITY_MOBILE, mapperUser.getMobile(), mapperUser);
            if(StringUtil.isNotEmpty(mapperUser.getLogin_name())){
                redisService.setEntity(REDIS_USER_ENTITY_LOGIN_NAME, mapperUser.getLogin_name(), mapperUser);
            }
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        }catch (Exception e){
            LOGGER.error("UserServiceImpl.setPassword出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }

    }
}
package com.ly.mt.mall.h5.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.*;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.buycar.vo.BuyCar;
import com.ly.mt.mall.h5.user.service.UserService;
import com.ly.mt.mall.h5.user.vo.UserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.ly.mt.core.base.dict.AlOssPath.AL_OSS_PATH_IMAGE_MALL_H5_AVATAR;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_USER;
import static com.ly.mt.core.base.dict.QuickType.QUICK_TYPE_MALL_H5;
import static com.ly.mt.core.base.dict.UserStatus.USER_STATUS_USING;
import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.redis.RedisKey.*;

/**
 * @Description 用户注册接口
 * @Author taoye
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * @Description 校验账号是否已注册
     * @Author taoye
     */
    @Override
    public ResponseJson checkLoginName(String loginName) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login_name", loginName);
            String userJson = callDataCenter(USER_GET_USER_BY_LOGIN_NAME, jsonObject);
            if (StringUtil.isEmpty(userJson)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_NOT_REGIST, "帐号未注册!");
            } else {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "帐号已注册!");
            }
        } catch (Exception e) {
            LOGGER.error("校验账号是否已注册出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
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


    /**
     * @Description 帐号注册
     * @Author taoye
     */
    @Override
    public ResponseJson regist(String loginName, String password, String mobile, String dynamicCode) {
        try {
            // 校验动态验证码
            String redisCode = redisService.get(REDIS_CODE_MALL_H5_REGIST, mobile);
            if (StringUtil.isEmpty(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码已过期!");
            }
            if (!dynamicCode.equals(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
            }
            // 保存用户信息
            String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER);
            LOGGER.info("---------------------92------------------------用户注册生成的id:"+id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("login_name", loginName);
            jsonObject.put("mobile", mobile);
            jsonObject.put("create_time", DateUtil.getNowTimeStr());
            jsonObject.put("password", MD5Util.md5(password));
            jsonObject.put("user_status", USER_STATUS_USING.getId());
            jsonObject.put("quick_type", QUICK_TYPE_MALL_H5.getId());
            callDataCenter(USER_INSERT, jsonObject);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,id);
           /* return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);*/
        } catch (Exception e) {
            LOGGER.error("注册出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 身份认证
     * @Author taoye
     */
    @Override
    public ResponseJson authentication(String userName, String idCard) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getLoginUserId());
            jsonObject.put("user_name", userName);
            jsonObject.put("id_card", idCard);
            callDataCenter(USER_UPDATE, jsonObject);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("身份认证出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 头像修改
     * @Author taoye
     */
    @Override
    public ResponseJson uploadAvatarPic(MultipartFile file) {
        try {
            String url = callThirdCenter(file, AL_OSS_PATH_IMAGE_MALL_H5_AVATAR.getPath());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getLoginUserId());
            jsonObject.put("avatar_pic_src", url);
            callDataCenter(USER_UPDATE, jsonObject);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, url);
        } catch (Exception e) {
            LOGGER.error("头像修改出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 绑定手机
     * @Author taoye
     */
    @Override
    public ResponseJson bindMobile(String mobile, String dynamicCode) {
        try {
            // 校验动态验证码
            String redisCode = redisService.get(REDIS_CODE_MALL_H5_BIND_MOBILE, mobile);
            if (StringUtil.isEmpty(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码已过期!");
            }
            if (!dynamicCode.equals(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getLoginUserId());
            jsonObject.put("mobile", mobile);
            callDataCenter(USER_UPDATE, jsonObject);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("绑定手机出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 修改信息
     * @Author taoye
     */
    @Override
    public ResponseJson updateInfo(String provinceCode, String cityCode, String districtCode, String birthday, String sex) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getLoginUserId());
            jsonObject.put("province_code", provinceCode);
            jsonObject.put("city_code", cityCode);
            jsonObject.put("district_code", districtCode);
            jsonObject.put("birthday", birthday);
            jsonObject.put("sex", sex);
            callDataCenter(USER_UPDATE, jsonObject);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("修改信息出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 根据登录用户id获取用户信息
     * @Author taoye
     */
    @Override
    public ResponseJson loadInfo() {
        try {
            // 查询用户信息
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getLoginUserId());
            String userJson = callDataCenter(USER_GET_USER_BY_ID, jsonObject);
            if (StringUtil.isEmpty(userJson)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户不存在!");
            }
            UserInfoVo user = JSONObject.toJavaObject(JSONObject.parseObject(userJson), UserInfoVo.class);
            if(StringUtil.isNotEmpty(user.getProvinceCode())) {
                user.setProvinceName(redisService.get(REDIS_BASIC_AREA_ENTITY_M_ID, user.getProvinceCode()));
            }
            if(StringUtil.isNotEmpty(user.getCityCode())) {
                user.setCityName(redisService.get(REDIS_BASIC_AREA_ENTITY_M_ID, user.getCityCode()));
            }
            if(StringUtil.isNotEmpty(user.getDistrictCode())) {
                user.setDistrictName(redisService.get(REDIS_BASIC_AREA_ENTITY_M_ID, user.getDistrictCode()));
            }
            user.setBirthday(StringUtil.isNotEmpty(user.getBirthday()) ? DateUtil.dateFormat(user.getBirthday()) : null);
            // 登录用户购物车商品数量
            String carJson = redisService.get(REDIS_ENTITY_CAR_ID, getLoginUserId());
            if (StringUtil.isEmpty(carJson)) {
                user.setBuyCarSkuNum("0");
            } else {
                BuyCar buyCar = JSONObject.toJavaObject(JSONObject.parseObject(carJson), BuyCar.class);
                user.setBuyCarSkuNum(String.valueOf(buyCar.getItems().size()));
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, user);
        } catch (Exception e) {
            LOGGER.error("获取用户信息出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 修改密码
     * @Author taoye
     */
    @Override
    public ResponseJson modifyPassword(String password, String mobile, String dynamicCode) {
        try {
            // 校验动态验证码
            String redisCode = redisService.get(REDIS_CODE_MALL_H5_PASSWORD, mobile);
            if (StringUtil.isEmpty(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码已过期!");
            }
            if (!dynamicCode.equals(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
            }
            // 更新密码
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("password", password);
            jsonObject.put("mobile", mobile);
            callDataCenter(USER_UPDATE, jsonObject);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("修改密码出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 修改默认帐号
     * @Author taoye
     */
    @Override
    public ResponseJson updateLoginName(String loginName) {
        try {
            // 检验loginName是否已存在
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login_name", loginName);
            String userJson = callDataCenter(USER_GET_USER_BY_LOGIN_NAME, jsonObject);
            if (StringUtil.isNotEmpty(userJson)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "帐号已存在!");
            }
            //修改loginName
            jsonObject.put("id", getLoginUserId());
            callDataCenter(USER_UPDATE, jsonObject);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("修改默认帐号出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
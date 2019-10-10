package com.ly.mt.home.tob.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.feign.ThirdCenterMethod;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.home.base.exception.MTException;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.shop.vo.ShopInfoVo;
import com.ly.mt.home.tob.shopuser.service.ShopUserService;
import com.ly.mt.home.tob.shopuser.service.TokenService;
import com.ly.mt.home.tob.wx.service.WxService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * wx
 *
 * @author: linan
 * @date: 2019/9/4
 */
@Service
public class WxServiceImpl extends BaseServiceImpl implements WxService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    TokenService tokenService;

    @Resource
    ShopUserService shopUserService;

    /**
     * 用户名登录
     *
     * @param mobile 手机号
     * @param password 密码
     * @param code wx授权码
     * @returnResponseJson
     */
    @Override
    public ResponseJson nameLogin(String mobile, String password, String code) {
        // login
        ShopInfoVo shopInfoVo = (ShopInfoVo) shopUserService.nameLogin(mobile, password).getResult();

        // auth
        JSONObject jsonObject = this.authorization(code);

        // bind wx openId
        String openId = jsonObject.getString("openid");
        bindUserWx(shopInfoVo.getUserId(), openId);

        JSONObject returnJson = new JSONObject();
        returnJson.putAll(jsonObject);
        returnJson.put("shop", shopInfoVo);
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, returnJson);
    }

    /**
     * 手机动态码登录
     *
     * @param mobile 手机号
     * @param dynamicCode 动态验证码
     * @param code wx授权码
     * @return ResponseJson
     */
    @Override
    public ResponseJson mobileLogin(String mobile, String dynamicCode, String code) {
        // login
        ShopInfoVo shopInfoVo = (ShopInfoVo) shopUserService.mobileLogin(dynamicCode, mobile).getResult();

        // auth
        JSONObject jsonObject = this.authorization(code);

        // bind wx openId
        String openId = jsonObject.getString("openid");
        bindUserWx(shopInfoVo.getUserId(), openId);

        JSONObject returnJson = new JSONObject();
        returnJson.putAll(jsonObject);
        returnJson.put("shop", shopInfoVo);
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, returnJson);
    }

    /**
     * wx授权
     *
     * @param code wx授权码
     * @return JSONObject
     */
    @Override
    public JSONObject authorization(String code) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        try {
            String result = callThirdCenter(ThirdCenterMethod.WX_APPLET_LOGIN_AUTH, jsonObject);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            logger.error("WxServiceImpl.authorization() error :{}", e);
            throw new MTException("微信授权异常");
        }
    }

    /**
     * 静默登录
     *
     * @param token wx access_token
     * @param refreshToken wx refresh_token
     * @param openId wx user openid
     * @return
     */
    @Override
    public ResponseJson loginByToken(String token, String refreshToken, String openId) {
        // query access_token
        String tokenCache = redisService.get(RedisKey.REDIS_WX_AUTH_TOKEN, token);

        // refresh token
        if(StringUtils.isEmpty(tokenCache)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("refresh_token", refreshToken);
            try {
                tokenCache = callThirdCenter(ThirdCenterMethod.WX_APPLET_LOGIN_AUTH, jsonObject);
            } catch (Exception e) {
                logger.error("wx access_token login failed:{}", e);
                throw new MTException("微信授权失败");
            }
        }

        // compare open_id
        JSONObject authJson = JSONObject.parseObject(tokenCache);
        if(!openId.equals(authJson.getString("openid"))){
            throw new MTException("微信授权失败");
        }

        // query user
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openId", openId);
        String userStr = callDataCenter(DataCenterMethod.USER_GET_USER_BY_WX_OPEN_ID, jsonObject);
        JSONObject userJson = JSONObject.parseObject(userStr);
        String userId = userJson.getString("id");

        // query shop_info
        jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        String shopStr = callDataCenter(DataCenterMethod.SHOP_INFO_GET_BY_USERID, jsonObject);
        ShopInfoVo shopInfoVo = JSONObject.parseObject(shopStr, ShopInfoVo.class);

        // jwt token
        String jwtToken = tokenService.generateToken(shopInfoVo.getId(), shopInfoVo.getUserId(), shopInfoVo.getMobile(), userJson.getString("login_name"), null);
        shopInfoVo.setToken(jwtToken);

        JSONObject returnJson = new JSONObject();
        returnJson.putAll(authJson);
        returnJson.put("shop", shopInfoVo);
        return null;
    }

    /**
     * 绑定用户openid
     * 
     */
    private void bindUserWx(String userId, String openId){
        JSONObject userJson = new JSONObject();
        userJson.put("wx_open_id", openId);
        userJson.put("id", userId);
        callDataCenter(DataCenterMethod.USER_UPDATE, userJson);
    }
}

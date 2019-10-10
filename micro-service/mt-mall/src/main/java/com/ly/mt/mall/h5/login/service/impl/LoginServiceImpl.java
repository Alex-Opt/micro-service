package com.ly.mt.mall.h5.login.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.dict.ProjectType;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.*;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.login.service.LoginService;
import com.ly.mt.mall.h5.login.vo.LoginByLoginNameVo;
import com.ly.mt.mall.h5.login.vo.LoginByMobileVo;
import com.ly.mt.mall.h5.login.vo.LoginPerfectInfoVo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_USER;
import static com.ly.mt.core.base.dict.ProjectType.PROJECT_TYPE_HOME_C;
import static com.ly.mt.core.base.dict.QuickType.QUICK_TYPE_MALL_WX_APPLET;
import static com.ly.mt.core.base.dict.UserStatus.USER_STATUS_USING;
import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.feign.ThirdCenterMethod.WX_APPLET_ACCESS_TOKEN_GET;
import static com.ly.mt.core.feign.ThirdCenterMethod.WX_APPLET_LOGIN_AUTH;
import static com.ly.mt.core.redis.RedisKey.*;

/**
 * @Description 用户登录
 * @Author taoye
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    /**
     * @Description 账号密码登录
     * @Author taoye
     */
    @Override
    public ResponseJson nameLogin(String loginName, String password, HttpServletRequest request) {
        try {
            // 获取用户信息
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login_name", loginName);
            String userJson = callDataCenter(USER_GET_USER_BY_LOGIN_NAME, jsonObject);
            if (StringUtil.isEmpty(userJson)) {
                jsonObject.put("mobile", loginName);
                userJson = callDataCenter(USER_GET_USER_BY_MOBILE, jsonObject);
                if (StringUtil.isEmpty(userJson)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "帐号或密码错误!");
                }
            }
            // 校验密码
            LoginByLoginNameVo loginVo = JSONObject.toJavaObject(JSONObject.parseObject(userJson), LoginByLoginNameVo.class);
            if (!MD5Util.md5(password).equals(loginVo.getPassword())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "帐号或密码错误!");
            }
            jsonObject.put("userId", loginVo.getId());
            String fiveTree = callDataCenter(LODE_RUNNER_USER_TREES_GETFIVE, jsonObject);
            saveSession(request, loginVo.getId(), loginName, loginVo.getUserName(), loginVo.getMobile(), fiveTree);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("帐号密码登录出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 手机号验证码登录
     * @Author taoye
     */
    @Override
    public ResponseJson mobileLogin(String mobile, String dynamicCode, String quickType, String data_source, String material, String channel, HttpServletRequest request) {
        try {
            // 校验是否已注册
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobile", mobile);
            String userJson = callDataCenter(USER_GET_LOGIN_USER_BY_MOBILE, jsonObject);
            if (StringUtil.isNotEmpty(userJson)) {
                // 校验验证码
                String redisCode = redisService.get(REDIS_CODE_MALL_H5_LOGIN, mobile);
                if (StringUtil.isEmpty(redisCode)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码失效,请重新获取!");
                }
                if (!redisCode.equals(dynamicCode)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
                }

            } else {
                // 校验动态验证码
                String redisCode = redisService.get(REDIS_CODE_MALL_H5_REGIST, mobile);
                if (StringUtil.isEmpty(redisCode)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码已过期!");
                }
                if (!dynamicCode.equals(redisCode)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
                }
                // 未注册直接注册
                String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER);
                jsonObject.put("id", id);
                jsonObject.put("login_name", "MT" + id.substring(0, 8));
                jsonObject.put("password", MD5Util.md5(mobile));
                jsonObject.put("user_status", USER_STATUS_USING.getId());
                jsonObject.put("create_time", DateUtil.getNowTimeStr());
                jsonObject.put("quick_type", quickType);
                jsonObject.put("project_type", PROJECT_TYPE_HOME_C.getId());
                if (StringUtil.isNotEmpty(data_source)) {
                    jsonObject.put("data_source", data_source);
                }
                if (StringUtil.isNotEmpty(material)) {
                    jsonObject.put("material", material);
                }
                if (StringUtil.isNotEmpty(channel)) {
                    jsonObject.put("channel", channel);
                }
                userJson = callDataCenter(USER_INSERT, jsonObject);
            }
            LoginByMobileVo loginVo = JSONObject.toJavaObject(JSONObject.parseObject(userJson), LoginByMobileVo.class);
            LOGGER.info("----------------------loginVo:" + JSONObject.toJSONString(loginVo));
            jsonObject.put("userId", loginVo.getId());
            String fiveTree = callDataCenter(LODE_RUNNER_USER_TREES_GETFIVE, jsonObject);
            saveSession(request, loginVo.getId(), loginVo.getLoginName(), loginVo.getUserName(), mobile, fiveTree);
            LOGGER.info("loginVo.getId():" + loginVo.getId());
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, loginVo.getId());
        } catch (Exception e) {
            LOGGER.error("手机号验证码登录出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 手机号验证码登录
     * @Author taoye
     */
    @Override
    public ResponseJson appletMobileLogin(String mobile, String dynamicCode, String quickType, HttpServletRequest request) {
        try {
            // 校验是否已注册
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobile", mobile);
            String userJson = callDataCenter(USER_GET_LOGIN_USER_BY_MOBILE, jsonObject);
            if (StringUtil.isNotEmpty(userJson)) {
                // 校验验证码
                String redisCode = redisService.get(REDIS_CODE_MALL_H5_LOGIN, mobile);
                if (StringUtil.isEmpty(redisCode)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码失效,请重新获取!");
                }
                if (!redisCode.equals(dynamicCode)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
                }
            } else {
                // 校验动态验证码
                String redisCode = redisService.get(REDIS_CODE_MALL_H5_REGIST, mobile);
                if (StringUtil.isEmpty(redisCode)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码已过期!");
                }
                if (!dynamicCode.equals(redisCode)) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
                }
                // 未注册直接注册
                //registStatus = "0";
                String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER);
                jsonObject.put("id", id);
                jsonObject.put("login_name", "MT" + id.substring(0, 8));
                jsonObject.put("password", MD5Util.md5(mobile));
                jsonObject.put("user_status", USER_STATUS_USING.getId());
                jsonObject.put("create_time", DateUtil.getNowTimeStr());
                jsonObject.put("quick_type", quickType);
                jsonObject.put("project_type", PROJECT_TYPE_HOME_C.getId());
                userJson = callDataCenter(USER_INSERT, jsonObject);
            }
            LoginByMobileVo loginVo = JSONObject.toJavaObject(JSONObject.parseObject(userJson), LoginByMobileVo.class);
            jsonObject.put("userId", loginVo.getId());
            String fiveTree = callDataCenter(LODE_RUNNER_USER_TREES_GETFIVE, jsonObject);
            LOGGER.info("loginVo.getId():" + loginVo.getId());
            //一个手机号对应的login_name是不变的。可以用它作为key缓存token.
            String token = UUID.randomUUID().toString();
            String tokenCache = redisService.get(REDIS_TOKEN_LOGIN_MALL_H5, loginVo.getLoginName());
            if (StringUtil.isEmpty(tokenCache)) {
                tokenCache = token;
            } else {
                token = tokenCache;
            }
            //获取通token后，用token作为key,保存用户信息。
            JSONObject tokenCacheJson = new JSONObject();
            tokenCacheJson.put("id", loginVo.getId());
            tokenCacheJson.put("user_name", loginVo.getUserName());
            tokenCacheJson.put("login_name", loginVo.getLoginName());
            tokenCacheJson.put("mobile", mobile);
            redisService.set(REDIS_WX_LOGIN_TOKEN, token, JSONObject.toJSONString(tokenCacheJson));
            redisService.set(REDIS_RUNNER_TREE_ID, loginVo.getId(), fiveTree, 10080L, TimeUnit.SECONDS);
            redisService.set(REDIS_TOKEN_LOGIN_MALL_H5, loginVo.getLoginName(), token, 10080L, TimeUnit.SECONDS);
            Map map = new HashMap<>(2);
            map.put("token", token);
            map.put("userId", loginVo.getId());
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, map);
        } catch (Exception e) {
            LOGGER.error("微信小程序的手机号验证码登录接口出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 用户信息补全
     * @Author taoye
     */
    @Override
    public ResponseJson perfectInfo(String loginName, String password, HttpServletRequest request) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", getLoginUserId());
            jsonObject.put("login_name", loginName);
            jsonObject.put("password", password);
            String userJson = callDataCenter(USER_UPDATE, jsonObject);
            LoginPerfectInfoVo loginVo = JSONObject.toJavaObject(JSONObject.parseObject(userJson), LoginPerfectInfoVo.class);
            jsonObject.put("userId", loginVo.getId());
            String fiveTree = callDataCenter(LODE_RUNNER_USER_TREES_GETFIVE, jsonObject);
            saveSession(request, loginVo.getId(), loginName, loginVo.getUserName(), loginVo.getMobile(), fiveTree);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("用户信息补全出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 保存session
     * @Author taoye
     */
    private void saveSession(HttpServletRequest request, String userId, String loginName, String userName, String mobile, String runnerTree) {
        // 存储常用信息
        String token = UUID.randomUUID().toString();
        String tokenCache = redisService.get(REDIS_TOKEN_LOGIN_MALL_H5, loginName);
        if (StringUtil.isEmpty(tokenCache)) {
            tokenCache = token;
        } else {
            token = tokenCache;
        }
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        session.setAttribute("userId", userId);
        session.setAttribute("loginName", loginName);
        session.setAttribute("userName", userName);
        session.setAttribute("mobile", mobile);
        session.setAttribute("ipAddress", RequestUtil.getIpAddress(request));
        session.setAttribute("runnerTree", runnerTree);
        // 刷新session有效时间30分钟
        session.setMaxInactiveInterval(604800);
        // 刷新redis有效时间30分钟
        redisService.set(REDIS_TOKEN_LOGIN_MALL_H5, loginName, token, 10080L, TimeUnit.SECONDS);
        redisService.set(REDIS_RUNNER_TREE_ID, userId, runnerTree, 10080L, TimeUnit.SECONDS);
    }

    /**
     * 根据code调用微信的三方接口,得到用户的微信授权信息open_id，session_key.并缓存起来。
     * 把缓存的key作为出参返回前台
     *
     * @param code
     * @param request
     * @return
     */
    @Override
    public ResponseJson weChatAppletLoginAuth(String code, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        try {
            String returnStr = callThirdCenter(WX_APPLET_LOGIN_AUTH, jsonObject);
            LOGGER.info("微信小程序登录凭证校验接口返回值：" + returnStr);
            JSONObject returnJson = JSONObject.parseObject(returnStr);
            String openId = returnJson.getString("openid");
            String sessionKey = returnJson.getString("session_key");
            //根据token缓存用户登录凭证校验返回值,有效期为七天
            JSONObject weChatJson = new JSONObject();
            weChatJson.remove("wx_open_id");
            weChatJson.put("openId", openId);
            weChatJson.put("sessionKey", sessionKey);
            String userWxInfoCacheKey = UUID.randomUUID().toString();
            redisService.set(REDIS_WX_LOGIN_CODE_AUTH, userWxInfoCacheKey, JSONObject.toJSONString(weChatJson), 7L, TimeUnit.DAYS);
            jsonObject.put("userWxInfoCacheKey", userWxInfoCacheKey);
        } catch (Exception e) {
            LOGGER.info("调用center-third的微信小程序登录凭证校验接口发生异常：" + e);
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "微信小程序登录凭证校验接口异常");
        }
        jsonObject.remove("code");
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, jsonObject);
    }


    @Override
    public ResponseJson weChatAppletMobileLogin(String mobile, String userWxInfoCacheKey) {
        boolean newUserFlag = false;
        String loginName = null;
        String token = UUID.randomUUID().toString();
        JSONObject jsonObject = new JSONObject();
        String wxLoginAuthTemp = redisService.get(REDIS_WX_LOGIN_CODE_AUTH, userWxInfoCacheKey);
        LOGGER.info("--------------------------------------微信小程序用户授权使用手机号登陆获取登陆凭证的个人信息：" + wxLoginAuthTemp);
        JSONObject wxLoginAuthJson = JSONObject.parseObject(wxLoginAuthTemp);
        String openId = wxLoginAuthJson.getString("openId");
        //先通过手机号获取缓存中是否已经存在用户信息。
        String userInfoStr = redisService.get(REDIS_USER_ENTITY_MOBILE, mobile);
        if (StringUtil.isEmpty(userInfoStr)) {
            jsonObject.put("mobile", mobile);
            //根据手机号查询当前用户是否已经存在。
            String userJsonStr = callDataCenter(USER_GET_LOGIN_USER_BY_MOBILE, jsonObject);
            if (StringUtil.isEmpty(userJsonStr)) {
                //说明查询不到用户，则返回是否为新用户的标记为true
                newUserFlag = true;
                //插入一条用户数据。
                String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER);
                jsonObject.put("id", id);
                loginName = "MT" + id.substring(0, 8);
                jsonObject.put("login_name", loginName);
                jsonObject.put("wx_open_id", openId);
                try {
                    jsonObject.put("password", MD5Util.md5(mobile));
                } catch (Exception e) {
                    LOGGER.error("通过md5算法获取password异常！" + e);
                }
                jsonObject.put("user_status", USER_STATUS_USING.getId());
                jsonObject.put("create_time", DateUtil.getNowTimeStr());
                jsonObject.put("quick_type", QUICK_TYPE_MALL_WX_APPLET.getId());
                jsonObject.put("project_type", PROJECT_TYPE_HOME_C.getId());
                callDataCenter(USER_INSERT, jsonObject);
            } else {
                jsonObject = JSONObject.parseObject(userJsonStr);
                String wx_open_id = jsonObject.getString("wx_open_id");
                loginName = jsonObject.getString("login_name");
                if (StringUtil.isEmpty(wx_open_id) || !openId.equals(wx_open_id)) {
                    //如果用户信息没有openId，则要更新进去
                    jsonObject.put("wx_open_id", openId);
                    jsonObject.put("update_time", DateUtil.getNowTimeStr());
                    callDataCenter(USER_UPDATE, jsonObject);
                }
            }
        } else {
            jsonObject = JSONObject.parseObject(userInfoStr);
            String wx_open_id = jsonObject.getString("wx_open_id");
            loginName = jsonObject.getString("login_name");
            if (StringUtil.isEmpty(wx_open_id) || !openId.equals(wx_open_id)) {
                //如果用户信息没有openId，则要更新进去
                jsonObject.put("wx_open_id", openId);
                jsonObject.put("update_time", DateUtil.getNowTimeStr());
                callDataCenter(USER_UPDATE, jsonObject);
            }
        }
        JSONObject param = new JSONObject(1);
        param.put("userId", jsonObject.get("id"));
        LOGGER.info("userId:" + jsonObject.getString("id"));
        String fiveTree = callDataCenter(LODE_RUNNER_USER_TREES_GETFIVE, param);
        redisService.set(REDIS_RUNNER_TREE_ID, jsonObject.getString("id"), fiveTree, 10080L, TimeUnit.SECONDS);
        //得到用户的信息，根据token写入缓存
        LOGGER.info("写入缓存前的用户信息打印jsonObject：" + jsonObject);
        String tokenCache = redisService.get(REDIS_TOKEN_LOGIN_MALL_H5, loginName);
        if (StringUtil.isEmpty(tokenCache)) {
            tokenCache = token;
        } else {
            token = tokenCache;
        }
        //保存token值为7天
        redisService.set(REDIS_TOKEN_LOGIN_MALL_H5, loginName, token, 7L, TimeUnit.DAYS);
        redisService.set(REDIS_WX_LOGIN_TOKEN, token, JSONObject.toJSONString(jsonObject));
        JSONObject returnJson = new JSONObject();
        returnJson.put("newUserFlag", newUserFlag);
        returnJson.put("userId", jsonObject.getString("id"));
        returnJson.put("token", token);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, returnJson);
    }

    /**
     * 微信小程序：获取用户绑定微信的手机号
     *
     * @param encryptedData
     * @param iv
     * @param userWxInfoCacheKey
     * @return
     */
    @Override
    public ResponseJson wxAppletGetBindMobileNumber(String encryptedData, String iv, String userWxInfoCacheKey) {
        String wxLoginAuthTemp = redisService.get(REDIS_WX_LOGIN_CODE_AUTH, userWxInfoCacheKey);
        LOGGER.info("获取第一步授权接口保存的个人微信的openId,sessionKey信息：" + wxLoginAuthTemp);
        JSONObject wxLoginAuthJson = JSONObject.parseObject(wxLoginAuthTemp);
        String sessionKey = wxLoginAuthJson.getString("sessionKey");
        if (sessionKey.length() != 24) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        LOGGER.info("encryptedData:" + encryptedData + ",iv:" + iv + ",sessionKey：" + sessionKey);
        String result;
        try {
            result = decryptNew(encryptedData, sessionKey, iv);
            LOGGER.info("------------------------result:" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String phoneNumber = jsonObject.getString("phoneNumber");
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, phoneNumber);
        } catch (Exception e) {
            LOGGER.error("解密微信绑定的手机号异常：{}", e);
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_GET_MOBILE_EXCEPTION, "获取微信绑定手机号异常，请重试!");
        }
    }

    private String decryptNew(String encryptedData, String sessionKey, String iv) throws Exception {
        String result = "";
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                result = new String(resultByte, "UTF-8");
            }
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (InvalidParameterSpecException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (BadPaddingException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (NoSuchProviderException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 到家C微信小程序的access_token
     *
     * @return
     */
    @Override
    public ResponseJson wxAppletAccessToken() {
        String access_token = redisService.get(REDIS_WX_APPLET_ACCESS_TOKEN);
        if (StringUtil.isEmpty(access_token)) {
            try {
                String accessToken = callThirdCenter(WX_APPLET_ACCESS_TOKEN_GET, new JSONObject());
                redisService.set(REDIS_WX_APPLET_ACCESS_TOKEN, accessToken, 30L, TimeUnit.MINUTES);
            } catch (Exception e) {
                LOGGER.error("到家C微信小程序的access_token异常：{}", e);
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    /**
     * 保存form_id的机制：固定长度为15的集合。每次有数据进来就将最新的刷进去，老的删掉。防止过期
     *
     * @param formIds
     * @param openId
     * @return
     */
    @Override
    public ResponseJson saveFormId(String formIds, String openId) {
        LOGGER.info("===================================保存formId的方法入参----formIds：{},openId:{}", formIds, openId);
        //解析出最新的form_id集合：setFromStr
        List<String> setFromStr = getSetFromStr(formIds);
        //获取老的缓存中的form_id
        String formIdCacheOld = redisService.get(REDIS_WX_APPLET_FORM_ID, openId);
        if (StringUtil.isNotEmpty(formIdCacheOld)) {
            List<String> formIdList = JSONObject.parseArray(formIdCacheOld, String.class);
            //存取的formId够用就行。将旧的form_id添加到新的集合setFromStr后面。addAll方法加入的列表元素的顺序是按照原来的顺序向后加入的
            setFromStr.addAll(formIdList);
        }
        int i = 1;
        List<String> formList = new ArrayList<>();
        for (String formId : setFromStr) {
            if (i > 15) {
                break;
            }
            formList.add(formId);
            i++;
        }
        redisService.set(REDIS_WX_APPLET_FORM_ID, openId, JSONObject.toJSONString(formList), 7L, TimeUnit.DAYS);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    /**
     * 将传过来的用逗号分割多个字符串转换成set集合
     *
     * @param formIds
     * @return
     */
    private List<String> getSetFromStr(String formIds) {
        String[] formIdArray = formIds.split(",");
        Set<String> set = new HashSet();
        for (String form_id : formIdArray) {
            set.add(form_id);
        }
        List li = new ArrayList();
        li.addAll(set);
        return li;
    }


}
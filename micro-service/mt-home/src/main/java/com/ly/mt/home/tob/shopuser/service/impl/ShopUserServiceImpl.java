package com.ly.mt.home.tob.shopuser.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.dict.QuickType;
import com.ly.mt.core.base.dict.ShopInfoStatus;
import com.ly.mt.core.base.dict.UserStatus;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.entity.shop.LodeRunnerBo;
import com.ly.mt.core.base.entity.shop.LodeRunnerConfigs;
import com.ly.mt.core.base.util.*;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.home.base.exception.MTException;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.coupon.service.ShopCouponService;
import com.ly.mt.home.tob.coupon.vo.CouponInfoVo;
import com.ly.mt.home.tob.shop.vo.ShopInfoVo;
import com.ly.mt.home.tob.shop.vo.UserVo;
import com.ly.mt.home.tob.shopuser.controller.ShopUserController;
import com.ly.mt.home.tob.shopuser.service.ShopUserService;
import com.ly.mt.home.tob.shopuser.vo.LodeRunnerCodesVo;
import com.ly.mt.home.tob.shopuser.vo.LodeRunnerTreesVo;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.home.base.constant.ShopConstant.SHOP_ACTIVITY_COUPON_ID;

/**
 * b端用户业务操作类
 *
 * @Author wanglong
 */
@Service
public class ShopUserServiceImpl extends BaseServiceImpl implements ShopUserService {
    /**
     * 日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopUserController.class);

    @Resource
    ShopCouponService shopCouponService;


    @Override
    public ResponseJson bShopUserCheckMobile(String mobile) {
        JSONObject resultOuter = new JSONObject();
        JSONObject result = new JSONObject();
        //判断在B端有没有注册过
        JSONObject param = new JSONObject();
        param.put("mobile", mobile);
        String shopUserStr = callDataCenter(DataCenterMethod.SHOP_INFO_GET_BY_MOBILE, param);
        // 0 false 1 true
        if (StringUtil.isNotEmpty(shopUserStr)) {
            ShopInfoVo shopInfo = JSONObject.toJavaObject(JSONObject.parseObject(shopUserStr), ShopInfoVo.class);
            String userStr = callDataCenter(DataCenterMethod.USER_GET_USER_BY_MOBILE, param);
            if (StringUtil.isNotEmpty(userStr)) {
                UserVo user = JSONObject.toJavaObject(JSONObject.parseObject(userStr), UserVo.class);
                result.put("userId", user.getId());
                result.put("shopId", shopInfo.getId());
                //是否设置过登陆密码
                if (StringUtil.isNotEmpty(user.getPassword())) {
                    //返回未设置登陆密码
                    resultOuter.put("isRegister", 1);
                    resultOuter.put("isSetPassword", 1);
                    resultOuter.put("isMotiDaojia", 1);
                    return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, resultOuter);
                } else {
                    resultOuter.put("isRegister", 1);
                    resultOuter.put("isSetPassword", 0);
                    resultOuter.put("isMotiDaojia", 1);
                    resultOuter.put("info", result);
                    return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, resultOuter);
                }
            } else {
                result.put("isRegister", 1);
                result.put("isSetPassword", 0);
                result.put("isMotiDaojia", 0);
                return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, resultOuter);
            }
        }
        result.put("isRegister", 0);
        result.put("isSetPassword", 0);
        result.put("isMotiDaojia", 0);
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseJson bShopUserRegist(String inviteCode, String mobile, String sourceFlag, String quickType, String dynamicCode) {
        //校验动态验证码
        String redisCode = redisService.get(RedisKey.REDIS_CODE_HOME_B_USER_REGIST, mobile);
        if (StringUtil.isEmpty(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "动态验证码失效");
        }

        if (!dynamicCode.equals(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "动态验证码错误");
        }
        try {
            String shopId = "";
            String userId = "";
            String treesId = SnowflakeUtil.getPrimaryKey(PrimaryKey.LODE_RUNNER_TREES);
            JSONObject jsonTrees = new JSONObject();
            jsonTrees.put("id", treesId);
            jsonTrees.put("user_source", "".equals(sourceFlag) ? "4" : sourceFlag);
            jsonTrees.put("create_time", DateUtil.getNowTimeStr());
            if (StringUtil.isEmpty(inviteCode)) {
                //插入用户表
                userId = bRegistUser(mobile, QuickType.QUICK_TYPE_HOME_B_H5.getId());
                jsonTrees.put("user_id", userId);
                //注册时没有邀请码
                shopId = insertShopInfo(mobile, userId, "");
                jsonTrees.put("top_user_id", userId);
                jsonTrees.put("father_id", "-1");
                jsonTrees.put("user_level", "1");
                callDataCenter(DataCenterMethod.LODE_RUNNER_TREES_INSERT, jsonTrees);
            } else {
                //注册时带有邀请码
                JSONObject lodeRunnerCodeJson = new JSONObject();
                lodeRunnerCodeJson.put("code", inviteCode);
                String lodeRunnerCodeStr = callDataCenter(DataCenterMethod.LODE_RUNNER_CODES_GET_BY_INVITECODE, lodeRunnerCodeJson);
                if (StringUtil.isNotEmpty(lodeRunnerCodeStr)) {
                    LodeRunnerCodesVo lodeRunnerCodes = JSONObject.toJavaObject(JSONObject.parseObject(lodeRunnerCodeStr), LodeRunnerCodesVo.class);
                    //插入用户表
                    userId = bRegistUser(mobile, QuickType.QUICK_TYPE_HOME_B_H5.getId());
                    jsonTrees.put("user_id", userId);
                    shopId = insertShopInfo(mobile, userId, inviteCode);

                    String fatherId = lodeRunnerCodes.getUser_id();
                    String fatherShopId = lodeRunnerCodes.getShop_id();
                    jsonTrees.put("father_id", fatherId);
                    //根据fatherId查询淘金人树
                    JSONObject searchJson = new JSONObject();
                    searchJson.put("user_id", fatherId);
                    String resultJsonStr = callDataCenter(DataCenterMethod.LODE_RUNNER_TREES_GET_BY_USERID, searchJson);
                    if (StringUtil.isNotEmpty(resultJsonStr)) {
                        LodeRunnerTreesVo father = JSONObject.toJavaObject(JSONObject.parseObject(resultJsonStr), LodeRunnerTreesVo.class);
                        jsonTrees.put("user_level", String.valueOf(Integer.parseInt(father.getUserLevel()) + 1));
                        Integer levelMin = (Integer.parseInt(father.getUserLevel()) - 4 > 0) ?
                                (Integer.parseInt(father.getUserLevel()) - 4)
                                : 0;
                        for (int i = Integer.parseInt(father.getUserLevel()); i > levelMin; i--) {
                            //向上找
                            //如果不是负一 一直往上找
                            if (!"-1".equals(father.getFatherId())) {
                                searchJson.put("user_id", father.getFatherId());
                                String retStr = callDataCenter(DataCenterMethod.LODE_RUNNER_TREES_GET_BY_USERID, searchJson);
                                if (StringUtil.isNotEmpty(retStr)) {
                                    LodeRunnerTreesVo runnerTrees = JSONObject.toJavaObject(JSONObject.parseObject(resultJsonStr), LodeRunnerTreesVo.class);
                                    father = runnerTrees;
                                }
                            } else {
                                jsonTrees.put("top_user_id", father.getUserId());
                            }
                        }
                        jsonTrees.put("top_user_id", father.getUserId());
                    }
                    JSONObject updateLodeRunnerCodesJson = new JSONObject();
                    updateLodeRunnerCodesJson.put("user_id", fatherId);
                    updateLodeRunnerCodesJson.put("shop_id", fatherShopId);
                    callDataCenter(DataCenterMethod.LODE_RUNNER_TREES_ADD_INVITE_NUM, updateLodeRunnerCodesJson);
                    callDataCenter(DataCenterMethod.LODE_RUNNER_TREES_INSERT, jsonTrees);
                } else {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "注册邀请码无效");
                }
            }
            Map<String, String> resultMap = new HashMap<>();
            JSONObject resultJson = new JSONObject();

            resultJson.put("userId", userId);
            resultJson.put("shopId", shopId);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, resultJson);
        } catch (Exception e) {
            LOGGER.error("b端用户注册异常", e.getMessage());
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    private String insertShopInfo(String mobile, String userId, String inviteCode) {
        //判断该手机号在b里面是否存在 如果存在，
        //ShopInfo info = new ShopInfo();
        JSONObject shopInfo = new JSONObject();
        shopInfo.put("mobile", mobile);
        String shopInfoObjStr = callDataCenter(DataCenterMethod.SHOP_INFO_GET_BY_MOBILE, shopInfo);
        if (null == shopInfoObjStr || "".equals(shopInfoObjStr)) {
            JSONObject shopInfoJson = new JSONObject();
            String shopId = SnowflakeUtil.getPrimaryKey(PrimaryKey.SHOP_USER);
            shopInfoJson.put("id", shopId);
            shopInfoJson.put("mobile", mobile);
            shopInfoJson.put("invite_code", ("".equals(inviteCode)) ? null : inviteCode);
            shopInfoJson.put("user_id", userId);
            shopInfoJson.put("status", ShopInfoStatus.USER_STATUS_USING.getId());
            shopInfoJson.put("create_time", DateUtil.getNowTimeStr());
            //写入缓存
            //  redisServer.setEntity(ENTITY_SHOP_USER_MOBILE_REDIS, shopInfo.getMobile(), shopInfo);
            callDataCenter(DataCenterMethod.SHOP_INFO_INSERT, shopInfoJson);
            return shopId;
        }
        ShopInfoVo shopInfoVo = JSONObject.toJavaObject(JSONObject.parseObject(shopInfoObjStr), ShopInfoVo.class);     //  }
        return shopInfoVo.getId();
    }

    private String insertShopFirst(String mobile, String userId) {
        //判断该手机号在b里面是否存在 如果存在，
        JSONObject shopInfo = new JSONObject();
        shopInfo.put("mobile", mobile);
        String shopInfoObjStr = callDataCenter(DataCenterMethod.SHOP_INFO_GET_BY_MOBILE, shopInfo);
        if (StringUtil.isNotEmpty(shopInfoObjStr)) {
            //插入一条信息
            JSONObject shopInfoJson = new JSONObject();
            String shopId = SnowflakeUtil.getPrimaryKey(PrimaryKey.SHOP_USER);
            shopInfoJson.put("id", shopId);
            shopInfoJson.put("mobile", mobile);
            shopInfoJson.put("user_id", userId);
            shopInfoJson.put("status", "0");
            shopInfoJson.put("create_time", DateUtil.getNowTimeStr());
            //写入缓存
            //  redisServer.setEntity(ENTITY_SHOP_USER_MOBILE_REDIS, shopInfo.getMobile(), shopInfo);
            callDataCenter(DataCenterMethod.SHOP_INFO_INSERT, shopInfoJson);
            return shopId;
        }
        ShopInfoVo shopInfoVo = JSONObject.toJavaObject(JSONObject.parseObject(shopInfoObjStr), ShopInfoVo.class);
        return shopInfoVo.getId();
    }

    private String bRegistUser(String mobile, String quickType) {
        JSONObject userJson = new JSONObject();
        userJson.put("mobile", mobile);
        String userStr = callDataCenter(DataCenterMethod.USER_GET_USER_BY_MOBILE_ACTIVITY, userJson);
        if (StringUtil.isEmpty(userStr)) {
            JSONObject insertUserJson = new JSONObject();
            String userId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_USER);
            insertUserJson.put("id", userId);
            insertUserJson.put("login_name", "MT" + userId.substring(0, 8));
            insertUserJson.put("mobile", mobile);
            insertUserJson.put("create_time", DateUtil.getNowTimeStr());
            insertUserJson.put("user_status", UserStatus.USER_STATUS_USING.getId());
            insertUserJson.put("quick_type", quickType);
            callDataCenter(DataCenterMethod.USER_INSERT, insertUserJson);
            return userId;
        } else {
            UserVo vo = JSONObject.toJavaObject(JSONObject.parseObject(userStr), UserVo.class);
            return vo.getId();
        }
    }

    @Override
    public ResponseJson bShopUserSetPassword(String password, String userId) {
        try {
            //更新密码
            JSONObject userJson = new JSONObject();
            userJson.put("password", MD5Util.md5(password));
            userJson.put("user_id", userId);
            callDataCenter(DataCenterMethod.USER_SET_PASSWORD, userJson);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson nameLogin(String mobile, String password) {
        //获取参数信息
        //取出 手机号，验证码
        //检测手机号是否在b端注册过
        JSONObject userJson = new JSONObject();
        userJson.put("mobile", mobile);
        String userStr = callDataCenter(DataCenterMethod.USER_GET_USER_BY_MOBILE_ACTIVITY, userJson);

        try {
            if (StringUtil.isEmpty(userStr)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "手机号未入住MOTO到家");
            } else {
                //检查密码是否正确 并且返回用户数据
                UserVo dbUser = JSONObject.toJavaObject(JSONObject.parseObject(userStr), UserVo.class);
                String dbPwd = MD5Util.md5(password);
                if (dbPwd.equals(dbUser.getPassword())) {
                    return getShopUserMap(mobile);
                }
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "账号或密码错误");
            }
        } catch (Exception e) {
            LOGGER.error("用户名登陆异常:{}", e.getMessage());
            throw new MTException("用户名登陆异常");
        }
    }


    @Override
    public ResponseJson mobileLogin(String dynamicCode, String mobile) {
        //取出 手机号，验证码
        //判断手机号是否在b端注册
        JSONObject userJson = new JSONObject();
        userJson.put("mobile", mobile);
        String userStr = callDataCenter(DataCenterMethod.USER_GET_USER_BY_MOBILE_ACTIVITY, userJson);
        if (StringUtil.isEmpty(userStr)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "手机号未入住MOTO到家");
        } else {
            //校验动态验证码
            String redisCode = redisService.get(RedisKey.REDIS_CODE_HOME_B_USER_LONGIN, mobile);
            if (StringUtil.isEmpty(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "动态验证码失效");
            }

            if (!dynamicCode.equals(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "动态验证码错误");
            }
            return getShopUserMap(mobile);
        }

    }

    @Override
    public ResponseJson resetPassword(String password, String mobile, String dynamicCode) {
        //校验动态验证码
        String redisCode = redisService.get(RedisKey.REDIS_CODE_HOME_B_USER_PASSWORD, mobile);
        if (StringUtil.isEmpty(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "动态验证码失效");
        }

        if (!dynamicCode.equals(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "动态验证码错误");
        }
        JSONObject userJson = new JSONObject();
        userJson.put("mobile", mobile);
        String userStr = callDataCenter(DataCenterMethod.USER_GET_USER_BY_MOBILE_ACTIVITY, userJson);
        if (StringUtil.isNotEmpty(userStr)) {
            return bShopUserSetPassword(password, JSONObject.toJavaObject(JSONObject.parseObject(userStr), UserVo.class).getId());
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
    }


    @Override
    public void loginOut() {
        String tokenHeader = getToken();
        Claims claims = tokenService.getClaimsFromToken(tokenHeader);
        String mobile = claims.get("mobile").toString();
        String shopId = claims.get("shopId").toString();
        String userId = claims.get("userId").toString();
        String userName = claims.get("userName").toString();
        tokenService.generateToken(shopId, userId, mobile, userName, 0L);

    }

    @Override
    public ResponseJson activityShopUserRegist(String mobile, String quickType, String dynamicCode) {
        // 到家b新人活动自动登录领券
        if (!quickType.equals(QuickType.QUICK_TYPE_HOME_B_ACTIVITY.getId())) {
            throw new MTException("参数异常");
        }
        String redisCode = null;
        JSONObject param = new JSONObject();
        param.put("mobile", mobile);
        String shopStr = callDataCenter(DataCenterMethod.SHOP_INFO_GET_BY_MOBILE, param);
        String userStr = callDataCenter(DataCenterMethod.USER_GET_USER_BY_MOBILE, param);
        // true:regist false:check login
        if(StringUtil.isEmpty(userStr) || StringUtil.isEmpty(shopStr)){
            bShopUserRegist(null, mobile, null, quickType, dynamicCode);
            shopStr = callDataCenter(DataCenterMethod.SHOP_INFO_GET_BY_MOBILE, param);
            userStr = callDataCenter(DataCenterMethod.USER_GET_USER_BY_MOBILE_ACTIVITY, param);
        } else {
            redisCode = redisService.get(RedisKey.REDIS_CODE_HOME_B_USER_LONGIN, mobile);

            if (StringUtil.isEmpty(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "动态验证码失效");
            }

            if (!dynamicCode.equals(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "动态验证码错误");
            }
        }

        ShopInfoVo shopInfoVo = JSONObject.parseObject(shopStr, ShopInfoVo.class);
        UserVo userVo = JSONObject.parseObject(userStr, UserVo.class);
        CouponInfoVo couponInfoVo = shopCouponService.getCoupon(SHOP_ACTIVITY_COUPON_ID, userVo.getId());

        boolean isReceiveCoupon = false;
        if(null == couponInfoVo){
            isReceiveCoupon = true;
            // 到家b新人活动自动领券
            shopCouponService.sendActivityCoupon(userVo.getId());

            couponInfoVo = shopCouponService.getCoupon(SHOP_ACTIVITY_COUPON_ID, userVo.getId());
        }
        String token = tokenService.generateToken(shopInfoVo.getId(), shopInfoVo.getUserId(), mobile, userVo.getLoginName(), null);
        shopInfoVo.setToken(token);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shopInfo", shopInfoVo);
        jsonObject.put("coupon", couponInfoVo);
        jsonObject.put("isReceive", isReceiveCoupon);
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, jsonObject);
    }

    private ResponseJson getShopUserMap(String mobile) {
        JSONObject shopJson = new JSONObject();
        shopJson.put("mobile", mobile);
        String shopStr = callDataCenter(DataCenterMethod.SHOP_INFO_GET_BY_MOBILE, shopJson);
        String userStr = callDataCenter(DataCenterMethod.USER_GET_USER_BY_MOBILE_ACTIVITY, shopJson);
        if (StringUtil.isNotEmpty(shopStr)) {
            ShopInfoVo shopInfoVo = JSONObject.toJavaObject(JSONObject.parseObject(shopStr), ShopInfoVo.class);
            UserVo userVo = JSONObject.toJavaObject(JSONObject.parseObject(userStr), UserVo.class);
            List<LodeRunnerBo> lodeRunnerLevel = getLodeRunnerLevel(shopInfoVo.getId(), shopInfoVo.getUserId());
            String token = tokenService.generateToken(shopInfoVo.getId(), shopInfoVo.getUserId(), mobile, userVo.getLoginName(), null);
            shopInfoVo.setToken(token);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, shopInfoVo);
        } else {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        //查询店铺发货地址
        //ShopAddress searchAddress = new ShopAddress();
        //searchAddress.setShopId(resultShop.getId());
        // ShopAddress resultAddress = mapper.getShopAddress(searchAddress);
        //  final List<LodeRunnerBo> lodeRunnerLevel = getLodeRunnerLevel(resultShop.getId(), resultuser.getId());
        //resultMap.put("user", resultuser);
        //resultMap.put("shop",resultShop);
        //   resultMap.put("defaultShopAddress",resultAddress);
        //   resultMap.put("shopUserLevel",lodeRunnerLevel);

        //查询出和该用户有关系的等级信息

        // return resultMap;
    }

    /**
     * 根据shopId和userId 查询 用户 等级  和 返利 fatherId
     *
     * @return
     */
    private List<LodeRunnerBo> getLodeRunnerLevel(String shopId, String userId) {
        List<LodeRunnerBo> retList = new LinkedList<>();
        ShopInfoVo info = new ShopInfoVo();
        JSONObject shopJson = new JSONObject();
        shopJson.put("id", shopId);
        String shopStr = callDataCenter(DataCenterMethod.SHOP_INFO_GET_BY_ID, shopJson);
        if (StringUtil.isNotEmpty(shopStr)) {
            info = JSONObject.toJavaObject(JSONObject.parseObject(shopStr), ShopInfoVo.class);
            //info = shopInfoOptional.get();
            //查询等级表
            JSONObject object = new JSONObject();
            object.put("user_id", info.getUserId());
            String lodeRunnerTreesStr = callDataCenter(DataCenterMethod.LODE_RUNNER_TREES_GET_BY_USERID, object);
            if (StringUtil.isNotEmpty(lodeRunnerTreesStr)) {
                LodeRunnerTreesVo lode = JSONObject.toJavaObject(JSONObject.parseObject(lodeRunnerTreesStr), LodeRunnerTreesVo.class);
                int minLevel = (Integer.parseInt(lode.getUserLevel()) - 5 > 0) ? Integer.parseInt(lode.getUserLevel()) - 5 : 0;
                int level = Integer.parseInt(lode.getUserLevel());
                for (int i = minLevel; i < level; i++) {
                    lode = getLodeRunnerTrees(retList, lode, level - i);
                }
                //查返现比例list
                Map<String, String> map = getPromotionMap();
                for (LodeRunnerBo bo : retList) {
                    if (map != null && !map.isEmpty())
                        bo.setProportion(map.get(bo.getLevel()));
                }
            }
        }
        return retList;
    }

    private Map<String, String> getPromotionMap() {
        Map<String, String> map = new HashMap<>();
        List<LodeRunnerConfigs> configslist = new ArrayList<>();
        String codesList = callDataCenter(DataCenterMethod.LODE_RUNNER_CONFIGS_GET_ALL, new JSONObject());
        if (StringUtil.isNotEmpty(codesList)) {
            configslist = JSONObject.parseArray(codesList, LodeRunnerConfigs.class);
            for (LodeRunnerConfigs configs : configslist) {
                map.put(configs.getLevel(), configs.getProportion());
            }
        }
        return map;
    }

    private LodeRunnerTreesVo getLodeRunnerTrees(List<LodeRunnerBo> retList, LodeRunnerTreesVo lode, int level) {
        LodeRunnerBo bo = new LodeRunnerBo();
        bo.setFatherId(lode.getFatherId());
        bo.setLevel(String.valueOf(level));
        bo.setUserId(lode.getUserId());
       /* ShopInfo search = new ShopInfo();
        search.setUserId(lode.getUserId());*/
        JSONObject object = new JSONObject();
        object.put("user_id", lode.getFatherId());
        String shopStr = callDataCenter(DataCenterMethod.SHOP_INFO_GET_BY_USERID, object);
        if (StringUtil.isNotEmpty(shopStr)) {
            ShopInfoVo info = JSONObject.toJavaObject(JSONObject.parseObject(shopStr), ShopInfoVo.class);
            bo.setShopId(info.getId());
        }
        bo.setTopUserId(lode.getTopUserId());
        String lodeRunnerTreesStr = callDataCenter(DataCenterMethod.LODE_RUNNER_TREES_GET_BY_USERID, object);
        if (StringUtil.isNotEmpty(lodeRunnerTreesStr)) {
            LodeRunnerTreesVo lodeRunnerTreesVo = JSONObject.toJavaObject(JSONObject.parseObject(lodeRunnerTreesStr), LodeRunnerTreesVo.class);
            retList.add(bo);
            lode = lodeRunnerTreesVo;
        }
        return lode;
    }

}

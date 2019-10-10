package com.ly.mt.user.server.shopuser.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.common.constant.IdEnum;
import com.ly.mt.core.common.constant.ImageVerifyCodeTypeEnum;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.entity.shop.*;
import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.entity.user.UserLoginVo;
import com.ly.mt.core.common.util.*;
import com.ly.mt.user.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.user.server.shopuser.mapper.ShopInfoServiceMapper;
import com.ly.mt.user.server.shopuser.service.LoginShopUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.ly.mt.core.common.constant.RedisEnum.*;
import static com.ly.mt.core.common.constant.ResultCodeEnum.*;
import static com.ly.mt.core.common.constant.SmsTemplateEnum.SMS_TEMPLATE_CODE_LOGIN;
import static com.ly.mt.core.common.dict.UserStatusEnum.USER_STATUS_USING;

@Service
public class LoginShopUserServiceImpl extends BaseServiceImpl implements LoginShopUserService {


    @Resource
    private ShopInfoServiceMapper mapper;

    /**
     * B端用户名/手机号，密码登录
     * @param json
     * @return
     */
    @Override
    public JSONObject nameLogin(String json) {
        //获取参数信息
        //取出 手机号，验证码
        UserLoginVo user = JSONObject.parseObject(json, UserLoginVo.class);
        String mobile =  user.getMobile();
        String password = user.getPassword();
        //检测手机号是否在b端注册过
        String redisShopUser = redisServer.get(ENTITY_SHOP_USER_MOBILE_REDIS,mobile);
        if(StringUtil.isEmpty(redisShopUser)){
            ShopInfo search = new ShopInfo();
            search.setMobile(mobile);
            Optional<ShopInfo> shopInfoOptional = Optional.ofNullable( mapper.getShopUser(search));
           /* if(shopInfoOptional.isPresent()){
               ShopInfo dbShopInfo =  shopInfoOptional.get();
                //是否完成商家认证
                if(ShopUserStatusEnum.SHOP_USER_STATUS_USING.getId().equals(dbShopInfo.getStatus())){
                    //更新缓存
                    redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, dbShopInfo.getMobile(),dbShopInfo);
                    //检查密码
                    return checkPassword(mobile, password);
                }
                //返回未认证
                return JsonUtil.getErrorJson(RESULT_CODE_REGIST_NOT_AUTHENTICATION);
            }else{
                //用户未在b端注册
                return JsonUtil.getErrorJson(RESULT_CODE_REGIST_NOT_REGIST);
            }*/
           if(!shopInfoOptional.isPresent()){
               //用户未在b端注册
               return JsonUtil.getErrorJson(RESULT_CODE_REGIST_NOT_REGIST);
           }else{
               //检查密码是否正确 并且返回用户数据
               return checkPassword(mobile, password);
           }
        }else{
            //检查密码是否正确 并且返回用户数据
            return checkPassword(mobile, password);
        }

    }

    /**
     * 检查密码是否正确 并且返回用户数据
     * @param mobile
     * @param password
     * @return
     */
    private JSONObject checkPassword(String mobile, String password) {
        try {

            UserLoginVo search = new UserLoginVo();
            search.setMobile(mobile);
            User dbUser = mapper.getUser(search);
            if(!dbUser.getPassword().equals(MD5Util.md5(password))){
                return JsonUtil.getErrorJson(RESULT_CODE_LOGIN_ERROR);
            }else{
                Map<String, Object> resultMap = getShopUserMap(mobile);
                return JsonUtil.getSuccessJson(resultMap);
           }
        } catch (Exception e) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }


    /**
     * B端手机号，验证码登录
     * @param json
     * @return
     */
    @Override
    public JSONObject mobileLogin(String json) {
        //取出 手机号，验证码
        UserLoginVo user = JSONObject.parseObject(json, UserLoginVo.class);
        String dynamicCode = user.getDynamicCode();
        String mobile = user.getMobile();
        //判断手机号是否在b端注册
        String redisShopUser = redisServer.get(ENTITY_SHOP_USER_MOBILE_REDIS,mobile);
        if(StringUtil.isNotEmpty(redisShopUser)){
            //验证验证码
            String redisCode = redisServer.get(SMS_TEMPLATE_CODE_LOGIN, user.getMobile());
            if (StringUtil.isEmpty(redisCode)) {
                return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
            }
            if (!redisCode.equals(dynamicCode)) {
                return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
            }
            //返回认证成功
            Map<String, Object> resultMap = getShopUserMap(mobile);
            return JsonUtil.getSuccessJson(resultMap);
        }else{
            ShopInfo search = new ShopInfo();
            search.setMobile(mobile);
            Optional<ShopInfo> shopInfoOptional = Optional.ofNullable(mapper.getShopUser(search));
           /* if(shopInfoOptional.isPresent()){
                ShopInfo shop = shopInfoOptional.get();
                //是否完成商家认证
                if(ShopUserStatusEnum.SHOP_USER_STATUS_USING.getId().equals(shop.getStatus())){
                    //验证验证码
                    String redisCode = redisServer.get(SMS_TEMPLATE_CODE_LOGIN, user.getMobile());
                    if (StringUtil.isEmpty(redisCode)) {
                        return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
                    }
                    if (!redisCode.equals(dynamicCode)) {
                        return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
                    }
                    //返回认证成功
                    Map<String, Object> resultMap = getShopUserMap(mobile);
                    return JsonUtil.getSuccessJson(resultMap);
                }
                //返回未认证
                return JsonUtil.getErrorJson(RESULT_CODE_REGIST_NOT_AUTHENTICATION);

            }else{
                return JsonUtil.getErrorJson(RESULT_CODE_REGIST_NOT_REGIST);
            }*/

            if(!shopInfoOptional.isPresent()){
                //用户未在b端注册
                return JsonUtil.getErrorJson(RESULT_CODE_REGIST_NOT_REGIST);
            }else{
                //验证验证码
                String redisCode = redisServer.get(SMS_TEMPLATE_CODE_LOGIN, user.getMobile());
                if (StringUtil.isEmpty(redisCode)) {
                    return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
                }
                if (!redisCode.equals(dynamicCode)) {
                    return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
                }
                UserLoginVo searchuser = new UserLoginVo();
                searchuser.setMobile(mobile);
                User dbUser = mapper.getUser(searchuser);
                if(dbUser != null){
                    Map<String, Object> resultMap = getShopUserMap(mobile);
                    return JsonUtil.getSuccessJson(resultMap);
                }else{
                    return JsonUtil.getErrorJson(RESULT_CODE_REGIST_NOT_REGIST);
                }
            }
        }
    }

    private Map<String, Object> getShopUserMap(String mobile) {
        //根据电话号码查询c端用户表
        Map<String,Object> resultMap = new ConcurrentHashMap<>(3);
        UserLoginVo search = new UserLoginVo();
        search.setMobile(mobile);
        User resultuser = mapper.getUser(search);
        //根据电话号码查询b端用户表
        ShopInfo searchshop = new ShopInfo();
        searchshop.setMobile(mobile);
        ShopInfo resultShop = mapper.getShopUser(searchshop);
        //查询店铺发货地址
        ShopAddress searchAddress = new ShopAddress();
        searchAddress.setShopId(resultShop.getId());
       // ShopAddress resultAddress = mapper.getShopAddress(searchAddress);
      //  final List<LodeRunnerBo> lodeRunnerLevel = getLodeRunnerLevel(resultShop.getId(), resultuser.getId());
        resultMap.put("user", resultuser);
        resultMap.put("shop",resultShop);
     //   resultMap.put("defaultShopAddress",resultAddress);
     //   resultMap.put("shopUserLevel",lodeRunnerLevel);

        //查询出和该用户有关系的等级信息

        return resultMap;
    }

    /**
     * 根据shopId和userId 查询 用户 等级  和 返利 fatherId
     * @return
     */
    private List<LodeRunnerBo> getLodeRunnerLevel(String shopId, String userId){
        List<LodeRunnerBo> retList = new LinkedList<>();
        ShopInfo info = new ShopInfo();
        info.setId(shopId);
        info.setUserId(userId);
        Optional<ShopInfo> shopInfoOptional = Optional.ofNullable(mapper.getShopUser(info));
        if(shopInfoOptional.isPresent()){
            info = shopInfoOptional.get();
            //查询等级表
            Optional<LodeRunnerTrees> lodeRunnerTrees = Optional.ofNullable(mapper.selectLoadRunnerTreeByUserId(Long.parseLong(info.getUserId())));
            if(lodeRunnerTrees.isPresent()){
                LodeRunnerTrees lode = lodeRunnerTrees.get();
                int minLevel = (Integer.parseInt(lode.getUserLevel())-5 >0)?Integer.parseInt(lode.getUserLevel())-5:0;
                int level = Integer.parseInt(lode.getUserLevel());
                for(int i = minLevel;i <level;i++){
                    lode = getLodeRunnerTrees(retList, lode,level-i);
                }
                //查返现比例list
                Map<String,String> map = getPromotionMap();
                for (LodeRunnerBo bo: retList) {
                    bo.setProportion(map.get(bo.getLevel()));
                }
            }
        }
        return retList;
    }

    private Map<String,String> getPromotionMap() {
        Map<String,String> map = new HashMap<>();
        List<LodeRunnerConfigs> configslist = mapper.selectLodeRunnerConFigs();
        for (LodeRunnerConfigs configs:configslist) {
            map.put(configs.getLevel(),configs.getProportion());
        }
        return map;
    }

    private LodeRunnerTrees getLodeRunnerTrees(List<LodeRunnerBo> retList, LodeRunnerTrees lode,int level) {
        LodeRunnerBo bo = new LodeRunnerBo();
        bo.setFatherId(lode.getFatherId());
        bo.setLevel(String.valueOf(level));
        bo.setUserId(lode.getUserId());
        ShopInfo search = new ShopInfo();
        search.setUserId(lode.getUserId());
        bo.setShopId(mapper.getShopUser(search).getId());
        bo.setTopUserId(lode.getTopUserId());
        Optional<LodeRunnerTrees> lodeRunnerTrees = Optional.ofNullable(mapper.selectLoadRunnerTreeByUserId(Long.parseLong(lode.getFatherId())));
        if(lodeRunnerTrees.isPresent()){
            retList.add(bo);
            lode = lodeRunnerTrees.get();
        }
        return lode;
    }


    /**
     * @Description 查询用户信息
     * @Author taoye
     */
    private User getUser(UserLoginVo user) {
        // 查询用户手机缓存
        String userJson = redisServer.get(ENTITY_USER_MOBILE_REDIS, user.getMobile());
        if (StringUtil.isEmpty(userJson)) {
            // 查询用户账号缓存
            userJson = redisServer.get(ENTITY_USER_LOGIN_NAME_REDIS, user.getLoginName());
            if (StringUtil.isEmpty(userJson)) {
                // 查询数据库
                User sysUser = mapper.getUser(user);
                if (null != sysUser) {
                    redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, sysUser.getMobile(), sysUser);
                    redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, sysUser.getLoginName(), sysUser);
                    return sysUser;
                } else {
                    return null;
                }
            }
        }
        return JSONObject.parseObject(userJson, User.class);
    }


    @Override
    public JSONObject h5mobileLogin(String json) throws Exception{
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        String mobile = map.get("mobile");
        String dynamicCode = map.get("dynamicCode");
        String verifyCode = map.get("verifyCode");

        //验证图片验证码
        String redisVerifyCode = redisServer.get(RedisEnum.IMAGE_VERIFY_CODE_LOGIN,
                ImageVerifyCodeTypeEnum.IMAGE_VERIFY_CODE_LOGIN.getCode());
        if(StringUtil.isEmpty(redisVerifyCode) )  {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
        }
        if (!redisVerifyCode.equalsIgnoreCase(verifyCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
        }
        //校验动态码
        String redisCode = redisServer.get(SMS_TEMPLATE_CODE_LOGIN,mobile);
        if (StringUtil.isEmpty(redisCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
        }
        if (!redisCode.equals(dynamicCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
        }
        //查询在b端有无上线
        ShopInfo search = new ShopInfo();
        search.setMobile(mobile);
        Optional<ShopInfo> shopUser = Optional.ofNullable(mapper.getShopUser(search));
        if(shopUser.isPresent()){
            //b端有上线 提示手机号已注册,保存session 登录
            ShopInfo shopInfo = shopUser.get();
            ShopAddress searchShopAddress = new ShopAddress();
            searchShopAddress.setShopId(shopInfo.getId());
            Optional<ShopAddress> addressOptional = Optional.ofNullable(mapper.getShopAddress(searchShopAddress));
            if(shopInfo.getShopLon() == null && shopInfo.getShopLat() == null && !addressOptional.isPresent()){
                return JsonUtil.getSuccessJson(JSON.toJSON(shopInfo));
            }
            return JsonUtil.getSuccessJson(JSON.toJSON(shopInfo));
        }else{
            //b端没有上线 查询是否存在用户
            UserLoginVo searchUser = new UserLoginVo();searchUser.setMobile(mobile);
            Optional<User> userOptional = Optional.ofNullable(mapper.getUser(searchUser));
            if(userOptional.isPresent()){
                //c端有上线 往shop_info插入信息
                User user = userOptional.get();
                return JsonUtil.getSuccessJson(shopUserActivityRegist(mobile, user));
            }else{
                //插入user表
                User insertUser = new User();
                insertUser.setMobile(mobile);
                insertUser.setQuickType("4");
                String userId = IdUtil.getId(IdEnum.USER);
                String loginName = "MT" + userId.substring(0, 8);
                insertUser.setLoginName(loginName);
                insertUser.setId(userId);
                insertUser.setCreateTime(DateUtil.getNowTimeStr());
                insertUser.setUserStatus(USER_STATUS_USING.getId());
                //insertUser.setPassword(MD5Util.md5(mobile));
                // id, login_name, mobile, create_time, quick_type, user_status
                //c端有上线 往shop_info插入信息
                mapper.bRegisterUser(insertUser);
                return JsonUtil.getSuccessJson(JSON.toJSON(shopUserActivityRegist(mobile,insertUser)));
            }
        }
    }

    private ShopInfo shopUserActivityRegist(String mobile, User user) {
        ShopInfo insertShopInfo = new ShopInfo();
        String shopId = IdUtil.getId(IdEnum.SHOP_USER);
        insertShopInfo.setId(shopId);
        insertShopInfo.setMobile(mobile);
        insertShopInfo.setUserId(user.getId());
        insertShopInfo.setStatus("0");//活动注册默认有效
        insertShopInfo.setInviteCode("");//活动注册无邀请码
        insertShopInfo.setCreateTime(DateUtil.getNowTimeStr());
        mapper.insert(insertShopInfo);
        //设置为顶层淘金者
        LodeRunnerTrees lodeRunnerTrees = new LodeRunnerTrees();
        lodeRunnerTrees.setFatherId("-1");
        lodeRunnerTrees.setId(IdUtil.getId(IdEnum.LODE_RUNNER_TREES));
        lodeRunnerTrees.setUserId(user.getId());
        lodeRunnerTrees.setTopUserId(user.getId());
        lodeRunnerTrees.setUserLevel("1");//等级 1
        lodeRunnerTrees.setUserSource("5");//用户来源  活动注册
        lodeRunnerTrees.setCreateTime(DateUtil.getNowTimeStr());
        mapper.insertLodeRunnerTree(lodeRunnerTrees);
        return insertShopInfo;
    }


}

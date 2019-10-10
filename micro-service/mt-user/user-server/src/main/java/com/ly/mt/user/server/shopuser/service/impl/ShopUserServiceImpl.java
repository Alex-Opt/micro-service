package com.ly.mt.user.server.shopuser.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.IdEnum;
import com.ly.mt.core.common.constant.ImageVerifyCodeTypeEnum;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.SmsTemplateEnum;
import com.ly.mt.core.common.dict.ShopUserStatusEnum;
import com.ly.mt.core.common.entity.shop.LodeRunnerTrees;
import com.ly.mt.core.common.entity.shop.LoderRunnerCodes;
import com.ly.mt.core.common.entity.shop.ShopInfo;
import com.ly.mt.core.common.entity.shop.ShopUserRegistVo;
import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.entity.user.UserInfoVo;
import com.ly.mt.core.common.entity.user.UserLoginVo;
import com.ly.mt.core.common.util.*;
import com.ly.mt.user.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.user.server.shopuser.mapper.ShopInfoServiceMapper;
import com.ly.mt.user.server.shopuser.service.ShopUserService;
import com.ly.mt.user.server.user.async.UserInfoServiceAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.ly.mt.core.common.constant.ImageVerifyCodeTypeEnum.IMAGE_VERIFY_CODE_REGIST;
import static com.ly.mt.core.common.constant.RedisEnum.*;
import static com.ly.mt.core.common.constant.ResultCodeEnum.*;
import static com.ly.mt.core.common.dict.UserStatusEnum.USER_STATUS_USING;

@Service
public class ShopUserServiceImpl extends BaseServiceImpl implements ShopUserService {
    //日志
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopUserServiceImpl.class);

    @Resource
    ShopInfoServiceMapper mapper;
    @Resource
    UserInfoServiceAsync async;

    /**
     * b端注册验证手机号
     * @param json
     * @return
     */
    @Override
    public JSONObject bShopUserCheckMobile(String json) {
        UserLoginVo user = JSONObject.parseObject(json, UserLoginVo.class);
        String mobile = user.getMobile();

        Map<String,String> resultMap = new HashMap<>();
        //判断在B端有没有注册过
        ShopInfo search = new ShopInfo();
        search.setMobile(mobile);
        Optional<ShopInfo> shopInfoOptional = Optional.ofNullable(mapper.getShopUser(search));
        if(shopInfoOptional.isPresent()){
            Optional<User> dbUserOptional  = Optional.ofNullable(mapper.getUser(user));
            if(dbUserOptional.isPresent()){
                resultMap.put("userId",dbUserOptional.get().getId());
                resultMap.put("shopId",shopInfoOptional.get().getId());
                //是否设置过登陆密码
                if(dbUserOptional.get().getPassword() == null || "".equals(dbUserOptional.get().getPassword())){
                    //返回未设置登陆密码
                    return JsonUtil.getErrorJson(RESULT_CODE_REGIST_NOTSET_PAWWSORD,resultMap);
                }

                return JsonUtil.getErrorJson(RESULT_CODE_REGIST_MOBILE_ERROR,resultMap);
            }
        }
        /*String redisUser = redisServer.get(ENTITY_USER_MOBILE_REDIS, mobile);
        if (StringUtil.isNotEmpty(redisUser)) {
            User userRedis = JSONObject.parseObject(redisUser, User.class);
            String shopId = insertShopFirst(mobile, userRedis.getId());
            resultMap.put("userId",userRedis.getId());
            resultMap.put("shopId",shopId);
            if(userRedis.getPassword() == null || "".equals(userRedis.getPassword())){
                //返回未设置登陆密码
                return JsonUtil.getErrorJson(RESULT_CODE_REGIST_NOTSET_PAWWSORD,resultMap);
            }

            return JsonUtil.getErrorJson(RESULT_CODE_REGIST_MOBILE_ERROR,resultMap);
        }
        if (dbUserOptional.isPresent()) {
            if(dbUserOptional.get().getPassword() == null || "".equals(dbUserOptional.get().getPassword())){
                //返回未设置登陆密码
                return JsonUtil.getErrorJson(RESULT_CODE_REGIST_NOTSET_PAWWSORD);
            }
            redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, user.getMobile(), user);
            redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, user.getLoginName(), user);
            String shopId = insertShopFirst(mobile,dbUserOptional.get().getId());
            resultMap.put("userId",dbUserOptional.get().getId());
            resultMap.put("shopId",shopId);
            return JsonUtil.getErrorJson(RESULT_CODE_REGIST_MOBILE_ERROR,resultMap);
        }*/
        return JsonUtil.getSuccessJson();
    }



    @Override
    public JSONObject bShopUserRegist(String json) {

        ShopUserRegistVo shopUserRegistVo = JSONObject.parseObject(json, ShopUserRegistVo.class);
        String dynamicCode = shopUserRegistVo.getDynamicCode();
        String mobile = shopUserRegistVo.getMobile();
        String inviteCode = shopUserRegistVo.getInviteCode();
        String sourceFlag = shopUserRegistVo.getSourceFlag();
        String quickType = shopUserRegistVo.getQuickType();
        String verifyCode = shopUserRegistVo.getVerifyCode();
       //校验图片验证码
        String redisVerifyCode = redisServer.get(RedisEnum.MAGE_VERIFY_CODE_REGIST,
                                                IMAGE_VERIFY_CODE_REGIST.getCode());
        if (StringUtil.isEmpty(redisVerifyCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_VERIFY_CODE_INVALID);
        }

        if (!verifyCode.equalsIgnoreCase(redisVerifyCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_VERIFY_CODE_ERROR);
        }
        //校验动态验证码
       String redisCode = redisServer.get(SmsTemplateEnum.SMS_TEMPLATE_CODE_REGIST, mobile);
        if (StringUtil.isEmpty(redisCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
        }

        if (!dynamicCode.equals(redisCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
        }

        try{

            String shopId = "";
            String userId = "";
            String treesId = IdUtil.getId(IdEnum.LODE_RUNNER_TREES);
            LodeRunnerTrees trees = new LodeRunnerTrees();
            trees.setId(treesId);
            trees.setUserSource("".equals(sourceFlag)?"4":sourceFlag);
            trees.setCreateTime(DateUtil.getNowTimeStr());

            if(null == inviteCode||"".equals(inviteCode)){
                //插入用户表
                 userId =  bRegistUser(mobile,quickType);
                trees.setUserId(userId);
                //注册时没有邀请码
                shopId = insertShopInfo(mobile,userId,"");
                trees.setTopUserId(userId);
                trees.setFatherId("-1");
                trees.setUserLevel("1");
                mapper.insertLodeRunnerTree(trees);
            }else{
                //注册时带有邀请码
                LoderRunnerCodes search = new LoderRunnerCodes();
                search.setCode(inviteCode);
                Optional<LoderRunnerCodes> codesOptional = Optional.ofNullable(mapper.selectLodeRunnerCodesByCode(search));
                if(codesOptional.isPresent()){
                    //插入用户表
                    userId =  bRegistUser(mobile,quickType);
                    trees.setUserId(userId);
                    shopId = insertShopInfo(mobile,userId,inviteCode);
                    String fatherId = codesOptional.get().getUserId();
                    String fatherShopId = codesOptional.get().getShopId();
                    trees.setFatherId(fatherId);
                    //根据fatherId查询淘金人树
                    Optional<LodeRunnerTrees> optional = Optional.ofNullable(mapper.selectLoadRunnerTreeByUserId(Long.valueOf(fatherId)));
                    if(optional.isPresent()){
                        LodeRunnerTrees father = optional.get();
                        trees.setUserLevel(String.valueOf(Integer.parseInt(father.getUserLevel())+1));

                       Integer levelMin = (Integer.parseInt(father.getUserLevel())-4 >0)?
                                           (Integer.parseInt(father.getUserLevel())-4)
                                            :0;
                        for(int i = Integer.parseInt(father.getUserLevel()); i > levelMin ;i--){
                            //向上找
                               //如果不是负一 一直往上找
                                if(!"-1".equals(father.getFatherId())){
                                    LodeRunnerTrees runnerTrees =  mapper.selectLoadRunnerTreeByUserId(Long.parseLong(father.getFatherId()));
                                    if(runnerTrees != null){
                                        father = runnerTrees;
                                    }
                                }else {
                                    trees.setTopUserId(father.getUserId());
                                }
                        }
                        trees.setTopUserId(father.getUserId());
                    }
                    //将该淘金者的成功邀请数量加1
                    LoderRunnerCodes updateLoderRunnerCodes = new LoderRunnerCodes();
                    updateLoderRunnerCodes.setUserId(fatherId);
                    updateLoderRunnerCodes.setShopId(fatherShopId);
                    mapper.beZeroNumsIfNull(updateLoderRunnerCodes);
                    mapper.addInviteNum(updateLoderRunnerCodes);
                    mapper.insertLodeRunnerTree(trees);
                }
                return JsonUtil.getErrorJson(RESULT_CODE_INVITE_CODE_INVALID);
            }
            Map<String,String> resultMap = new HashMap<>();
            resultMap.put("userId",userId);
            resultMap.put("shopId",shopId);
            return JsonUtil.getSuccessJson(resultMap);
        }catch (Exception e){
            LOGGER.error("b端用户注册异常",e.getMessage());
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }

    }

    private LodeRunnerTrees calcLevelAndTopLevel(String fatherId) {
        LodeRunnerTrees retTree = new LodeRunnerTrees();
        while (true){
            Optional<LodeRunnerTrees> optional = Optional.ofNullable(mapper.selectLoadRunnerTreeByUserId(Long.parseLong(fatherId)));
            if(optional.isPresent()){
                LodeRunnerTrees fatherTree=optional.get();
                long levelSum = Long.parseLong(fatherTree.getUserLevel());

                if("-1".equals(fatherTree.getFatherId())){
                    retTree.setUserLevel(String.valueOf(levelSum));
                    retTree.setTopUserId(fatherTree.getUserId());
                    return retTree;
                }else{
                    levelSum += Long.parseLong(fatherTree.getUserLevel());
                    return calcLevelAndTopLevel(fatherTree.getFatherId());
                }
            }
        }
    }

    /**
     * b端用户设置密码
     * @param json
     * @return
     */
    public JSONObject bShopUserSetPassword(String json){
        try{
            //更新密码
            Map<String,String> param = JSONObject.parseObject(json, Map.class);
            String passwrod = param.get("password");
            String userId = param.get("userId");
            mapper.setPassword(userId, MD5Util.md5(passwrod));
            UserInfoVo user = new UserInfoVo();
            user.setId(userId);
            async.refreshRedis(user);
        }catch (Exception e){
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        return  JsonUtil.getSuccessJson();
    }

    public JSONObject bShopUserModifyPassword(String json){
        //更新密码
        Map<String,String> param = JSONObject.parseObject(json, Map.class);
        String mobile = param.get("mobile");
        String password= param.get("password");
        String dynamicCode = param.get("dynamicCode");
        String verifyCode = param.get("verifyCode");
        //检验图片验证码
        String redisVerifyCode = redisServer.get(IMAGE_VERIFY_CODE_RESET_PASSWORD,
                ImageVerifyCodeTypeEnum.IMAGE_VERIFY_CODE_RESET_PASSWORD.getCode());
        if (StringUtil.isEmpty(redisVerifyCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_VERIFY_CODE_INVALID);
        }
        if (!verifyCode.equalsIgnoreCase(redisVerifyCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_VERIFY_CODE_ERROR);
        }
        //检验动态验证码
       String redisCode = redisServer.get(SmsTemplateEnum.SMS_TEMPLATE_CODE_PASSWORD, mobile);
        if (StringUtil.isEmpty(redisCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_INVALID);
        }
        if (!dynamicCode.equals(redisCode)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_ERROR);
        }
        //设置密码
        try {
            //检查手机号是否 未注册 如果未注册  提示去注册
            ShopInfo search = new ShopInfo();
            search.setMobile(mobile);
            Optional<ShopInfo> shopInfoOptional = Optional.ofNullable(mapper.getShopUser(search));
            if(!shopInfoOptional.isPresent()){
                return JsonUtil.getErrorJson(RESULT_CODE_REGIST_NOT_REGIST);
            }
            String md5Password = MD5Util.md5(password);
            mapper.setPassword(shopInfoOptional.get().getUserId(),md5Password);
            UserInfoVo user = new UserInfoVo();
            user.setId(shopInfoOptional.get().getUserId());
            async.refreshRedis(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  JsonUtil.getSuccessJson();
    }

    private String bRegistUser(String mobile,String quickType) {
        UserLoginVo vo = new UserLoginVo();
        vo.setMobile(mobile);
        Optional<User> userOptional = Optional.ofNullable(mapper.getUser(vo));
        if(!userOptional.isPresent()){
            String userId = IdUtil.getId(IdEnum.USER);
            User userRegist = new User();
            userRegist.setId(userId);
            userRegist.setLoginName("MT" + userId.substring(0, 8));
            userRegist.setMobile(mobile);
            userRegist.setCreateTime(DateUtil.getNowTimeStr());
            userRegist.setUserStatus(USER_STATUS_USING.getId());
            userRegist.setQuickType(quickType);
            mapper.bRegisterUser(userRegist);
            //将用户信息存入缓存 分别以手机号和登录名为键
            redisServer.setEntity(ENTITY_USER_MOBILE_REDIS, userRegist.getMobile(), userRegist);
            redisServer.setEntity(ENTITY_USER_LOGIN_NAME_REDIS, userRegist.getLoginName(), userRegist);
            return userId;
        }else{
            return userOptional.get().getId();
        }

    }
    private String  insertShopInfo(String mobile,String userId,String inviteCode) {
        //判断该手机号在b里面是否存在 如果存在，
        ShopInfo info = new ShopInfo();
        info.setMobile(mobile);
        Optional<ShopInfo> optionalShopInfo = Optional.ofNullable(mapper.getShopUser(info));
        if(!optionalShopInfo.isPresent()){
            //插入一条信息
            ShopInfo shopInfo = new ShopInfo();
            String shopId = IdUtil.getId(IdEnum.SHOP_USER);
            shopInfo.setId(shopId);
            shopInfo.setMobile(mobile);
            shopInfo.setInviteCode(("".equals(inviteCode))?null:inviteCode);
            shopInfo.setUserId(userId);
            shopInfo.setStatus(ShopUserStatusEnum.SHOP_USER_STATUS_STOP_USING.getId());
            shopInfo.setCreateTime(DateUtil.getNowTimeStr());
            //写入缓存
          //  redisServer.setEntity(ENTITY_SHOP_USER_MOBILE_REDIS, shopInfo.getMobile(), shopInfo);
            mapper.insert(shopInfo);
            return shopId;
        }
        return optionalShopInfo.get().getId();
    }
    private String  insertShopFirst(String mobile,String userId) {
        //判断该手机号在b里面是否存在 如果存在，
        Optional<ShopInfo> optionalShopInfo = Optional.ofNullable(mapper.selectByMobile(mobile));
        if(!optionalShopInfo.isPresent()){
            //插入一条信息
            ShopInfo shopInfo = new ShopInfo();
            String shopId = IdUtil.getId(IdEnum.SHOP_USER);
            shopInfo.setId(shopId);
            shopInfo.setMobile(mobile);
            shopInfo.setUserId(userId);
            shopInfo.setStatus(ShopUserStatusEnum.SHOP_USER_STATUS_STOP_USING.getId());
            shopInfo.setCreateTime(DateUtil.getNowTimeStr());
            //写入缓存
            //redisServer.setEntity(ENTITY_SHOP_USER_MOBILE_REDIS, shopInfo.getMobile(), shopInfo);
            mapper.insert(shopInfo);
            return shopId;
        }
        return optionalShopInfo.get().getId();
    }

}

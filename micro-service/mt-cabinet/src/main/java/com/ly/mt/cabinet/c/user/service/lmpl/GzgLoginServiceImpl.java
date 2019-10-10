package com.ly.mt.cabinet.c.user.service.lmpl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.coupon.entity.CouponCode;
import com.ly.mt.cabinet.c.coupon.entity.CouponInfo;
import com.ly.mt.cabinet.c.coupon.entity.GzgCouponTypeEnum;
import com.ly.mt.cabinet.c.coupon.service.GzgCouponCodeService;
import com.ly.mt.cabinet.c.coupon.entity.GzgCouponCode;
import com.ly.mt.cabinet.c.user.entity.GzgUserLoginVo;
import com.ly.mt.cabinet.c.user.entity.GzgUserRegisterVo;
import com.ly.mt.cabinet.c.user.entity.GzgUserVo;
import com.ly.mt.cabinet.c.user.service.GzgLoginService;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.dict.ProjectType;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.RedisKey;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_USER_ENTITY_MOBILE;

/**
 * @Description 用户信息操作接口
 * @Author taoye
 */
@Service
public class GzgLoginServiceImpl extends BaseServiceImpl implements GzgLoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GzgLoginServiceImpl.class);

    @Resource
    GzgCouponCodeService couponCodeService;


    /**
     * @Description 手机号登录
     * @Author taoye
     */
    @Override
    public ResponseJson mobileLogin(GzgUserLoginVo paramUser) throws Exception {
        // 校验动态验证码
        String redisCode = redisService.get(RedisKey.REDIS_CODE_MALL_H5_LOGIN, paramUser.getMobile());
        if (StringUtil.isEmpty(redisCode)) {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_VERIFY_CODE_INVALID, "验证码已过期");
        }
       if (!redisCode.equals(paramUser.getDynamicCode())) {
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_VERIFY_CODE_ERROR, "验证码错误");
        }
        // 校验用户是否存在
        GzgUserRegisterVo gzgUser = getUser(paramUser);
        // 第一次登录直接注册账号
        if (null == gzgUser) {
            gzgUser = new GzgUserRegisterVo();
            String id = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_USER);
            gzgUser.setId(id);
            gzgUser.setLogin_name("MT"+id.substring(0,8));
            gzgUser.setMobile(paramUser.getMobile());
            gzgUser.setCreate_time(DateUtil.getNowTimeStr());
            gzgUser.setUser_type("1");//1：普通用户，2：企业用户，3：卖家用户，4：平台用户
            gzgUser.setUser_status("10");//10：正常状态，20：停用状态)

            gzgUser.setQuick_type("5");//注册来源：1：注册用户，2：微信，3：QQ，4：活动注册,5:格子柜-c,6:格子柜-b

            gzgUser.setProject_type(ProjectType.PROJECT_TYPE_GZG_C.getId());//注册来源：1：注册用户，2：微信，3：QQ，4：活动注册,5:格子柜-c,6:格子柜-b
            gzgUser.setCreate_time(DateUtil.getNowTimeStr());
            callDataCenter(DataCenterMethod.USER_INSERT_USER, JSONObject.parseObject(JSONObject.toJSONString(gzgUser)));
           // addNewUserInfoandReceiveCoupon(gzgUser, GzgCouponTypeEnum.GZG_COUPON_NEW_PEOPLE_DISCOUNT);//持久化用户信息并领取新人优惠券
        }
        GzgUserVo gzgUserVo = new GzgUserVo();
        gzgUserVo.setId(gzgUser.getId());
        gzgUserVo.setCreateTime(gzgUser.getCreate_time());
        gzgUserVo.setUserStatus(gzgUser.getUser_status());
        gzgUserVo.setMobile(gzgUser.getMobile());
        gzgUserVo.setToken("");
        Boolean isCouponReceived = couponCodeService.isReceiveGzgCouponInfo(GzgCouponTypeEnum.GZG_COUPON_NEW_PEOPLE_DISCOUNT,gzgUser.getId());
        if(!isCouponReceived){
            sendCoupon(GzgCouponTypeEnum.GZG_COUPON_NEW_PEOPLE_DISCOUNT.getId(),gzgUser);
            sendCoupon(GzgCouponTypeEnum.DAOJIA_COUPON_NEW_PEOPLE_DISCOUNT_1.getId(),gzgUser);
            sendCoupon(GzgCouponTypeEnum.DAOJIA_COUPON_NEW_PEOPLE_DISCOUNT_2.getId(),gzgUser);
            sendCoupon(GzgCouponTypeEnum.DAOJIA_COUPON_NEW_PEOPLE_DISCOUNT_3.getId(),gzgUser);
            sendCoupon(GzgCouponTypeEnum.DAOJIA_COUPON_NEW_PEOPLE_DISCOUNT_4.getId(),gzgUser);
            gzgUserVo.setCouponReceived(false);
        }else{
            gzgUserVo.setCouponReceived(true);
        }
        List<GzgCouponCode> gzgCouponCodeVoList = couponCodeService.selectNotUsedCouponByUserId(gzgUser.getId());
        gzgUserVo.setGzgCouponCodeVoList(gzgCouponCodeVoList);
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,gzgUserVo);
    }


    /**
     * 发放新人优惠券
     * @param couponId
     * @param gzgUser
     * @throws Exception
     */
    private void sendCoupon(String couponId,GzgUserRegisterVo gzgUser) throws Exception{
        CouponInfo couponInfo= couponCodeService.getGzgCouponInfoById(couponId);
        String id = SnowflakeUtil.getPrimaryKey(PrimaryKey.COUPON_CODE);
        CouponCode couponCode = new CouponCode();
        couponCode.setId(id);
        couponCode.setCoupon_id(couponId);
        couponCode.setUser_id(gzgUser.getId());
        couponCode.setPull_status("2");//领取状态 1：未领取，2：已领取
        couponCode.setPull_time(DateUtil.getNowTimeStr());//领取时间
        if (couponInfo.getValidity_day() != null) {
            Date date = DateUtil.offsetDate(new Date(), Integer.parseInt(couponInfo.getValidity_day()));
            couponCode.setInvalid_time(DateUtil.date2TimeStr(date));//优惠券失效时间
        } else {
            couponCode.setInvalid_time(couponInfo.getEnd_time());//优惠券失效时间
        }
        couponCode.setUse_status("1");//使用状态 1：未使用，2：已使用
        couponCode.setCreate_time(DateUtil.getNowTimeStr());
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(couponCode));

        String xid = TxcContext.getCurrentXid();
        jsonObject.put("xid", xid);
        callDataCenter(DataCenterMethod.GZG_COUPON_CODE_INSERT, jsonObject);

    }

    /**
     * @Description 查询用户信息
     * @Author taoye
     */
    private GzgUserRegisterVo getUser(GzgUserLoginVo gzgUserLoginVo) {
        String userJson = redisService.get(REDIS_USER_ENTITY_MOBILE, gzgUserLoginVo.getMobile());
        GzgUserRegisterVo user = null;
        if (StringUtil.isEmpty(userJson)) {
            JSONObject jsonUser = new JSONObject();
            jsonUser.put("mobile", gzgUserLoginVo.getMobile());
            String resultUser = null;
            try {
                resultUser = callDataCenter(DataCenterMethod.USER_GET_USER_BY_MOBILE, jsonUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
            user = JSONObject.toJavaObject(JSONObject.parseObject(resultUser), GzgUserRegisterVo.class);
        } else {
            user = JSONObject.toJavaObject(JSONObject.parseObject(userJson), GzgUserRegisterVo.class);
        }
        return user;
    }

    /**
     * 持久化用户信息并领取新人优惠券
     *
     * @param user
     * @param gzgCouponTypeEnum
     * @throws Exception
     */
    private void addNewUserInfoandReceiveCoupon(GzgUserRegisterVo user, GzgCouponTypeEnum gzgCouponTypeEnum) throws Exception {
        couponCodeService.saveGzgCouponCode(GzgCouponTypeEnum.GZG_COUPON_NEW_PEOPLE_DISCOUNT, user);
    }


}
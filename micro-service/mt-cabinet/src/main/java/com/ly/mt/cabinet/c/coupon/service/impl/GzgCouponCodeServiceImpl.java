package com.ly.mt.cabinet.c.coupon.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.coupon.entity.*;
import com.ly.mt.cabinet.c.coupon.service.GzgCouponCodeService;
import com.ly.mt.cabinet.c.tools.RemoveSlash;
import com.ly.mt.cabinet.c.user.entity.GzgUserRegisterVo;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author gongjy
 * @description 优惠券码表服务层
 * @date 2019-05-24
 */
@Service
public class GzgCouponCodeServiceImpl extends BaseServiceImpl implements GzgCouponCodeService {

    private final static Logger LOGGER = LoggerFactory.getLogger(GzgCouponCodeServiceImpl.class);

    private CouponInfo getGzgCouponInfoById(GzgCouponTypeEnum gzgCouponTypeEnum) throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", gzgCouponTypeEnum.getId());
        String resultGzgCoupon = callDataCenter(DataCenterMethod.GZG_COUPON_INFO_GET, json);
        CouponInfo couponInfo = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgCoupon), CouponInfo.class);
        return couponInfo;
    }

    @Override
    public CouponInfo getGzgCouponInfoById(String  couponId) throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", couponId);
        String resultGzgCoupon = callDataCenter(DataCenterMethod.GZG_COUPON_INFO_GET, json);
        CouponInfo couponInfo = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgCoupon), CouponInfo.class);
        return couponInfo;
    }

    /**
     * 将新用户插入user表
     * 将新用户领取的优惠券插入coupon_code表，此处不判断用户是否已领取
     *
     * @param gzgCouponTypeEnum
     * @param user
     * @throws Exception
     */
    @Override
    @TxcTransaction(appName = "gzg")
    public void saveGzgCouponCode(GzgCouponTypeEnum gzgCouponTypeEnum, GzgUserRegisterVo user) throws Exception {
        CouponInfo couponInfo = getGzgCouponInfoById(gzgCouponTypeEnum);
        CouponCode couponCode = new CouponCode();
        String id = SnowflakeUtil.getPrimaryKey(PrimaryKey.COUPON_CODE);
        couponCode.setId(id);
        couponCode.setCoupon_id(gzgCouponTypeEnum.getId());
        couponCode.setUser_id(user.getId());
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
        callDataCenter(DataCenterMethod.USER_INSERT_USER, JSONObject.parseObject(JSONObject.toJSONString(user)));
        callDataCenter(DataCenterMethod.GZG_COUPON_CODE_INSERT, jsonObject);
    }


    /**
     * 判断用户是否已领取某个类型的优惠券
     *
     * @param gzgCouponTypeEnum
     * @param userId
     * @throws Exception
     */
    @Override
    public Boolean isReceiveGzgCouponInfo(GzgCouponTypeEnum gzgCouponTypeEnum, String userId) throws Exception {
        Boolean isReceiveGzgCouponInfo = true;
        GzgCouponCode gzgCouponCodeVo = new GzgCouponCode();
        gzgCouponCodeVo.setCoupon_id(gzgCouponTypeEnum.getId());
        gzgCouponCodeVo.setUser_id(userId);
        String resultGzgCouponCode = callDataCenter(DataCenterMethod.GZG_COUPON_CODE_GET, JSONObject.parseObject(JSONObject.toJSONString(gzgCouponCodeVo)));
        GzgCouponCode gzgCouponCodeVo1 = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgCouponCode), GzgCouponCode.class);
        if (gzgCouponCodeVo1 == null) {
            isReceiveGzgCouponInfo = false;
        }
        return isReceiveGzgCouponInfo;
    }

    /**
     * 通过用户id获取用户未使用的优惠券
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<GzgCouponCode> selectNotUsedCouponByUserId(String userId) throws Exception {
        JSONObject jsonGzgCouponCode = new JSONObject();
        jsonGzgCouponCode.put("user_id", userId);
        String resultGzgCouponCode = callDataCenter(DataCenterMethod.GZG_COUPON_CODE_GET_NOT_USED, jsonGzgCouponCode);
        List<GzgCouponCode> gzgCouponCodeVoList = JSONArray.parseArray(resultGzgCouponCode, GzgCouponCode.class);
        return gzgCouponCodeVoList;
    }

    @Override
    public String isUsedCouponByUserIdAndCouponId(String userId, String CouponId) throws Exception {
        JSONObject jsonCouponCode = new JSONObject();
        jsonCouponCode.put("coupon_id",CouponId);
        jsonCouponCode.put("user_id",userId);
        String resultCouponCode = callDataCenter(DataCenterMethod.GZG_COUPON_CODE_GET_NOT_USED, jsonCouponCode);

        if(StringUtil.isNotEmpty(resultCouponCode)){
           return resultCouponCode;
        }
        return "";
    }

    @Override
    public ResponseJson selectAllCouponByUserId(String userId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id",userId);
        String resultGzgCouponCode = callDataCenter(DataCenterMethod.GZG_COUPON_CODE_GET_BY_USERID, jsonObject);
        System.out.println(resultGzgCouponCode);
        List<CouponCode> couponCodeList = JSONArray.parseArray(resultGzgCouponCode, CouponCode.class);
        List<CouponCodeVo> couponCodeVoList = new ArrayList<CouponCodeVo>();
        for (int i = 0; i < couponCodeList.size(); i++) {
            CouponInfo gzgCouponInfo = getGzgCouponInfoById(couponCodeList.get(i).getCoupon_id());

            CouponCodeVo couponCodeVo = new CouponCodeVo();
            couponCodeVo.setId(couponCodeList.get(i).getId());
            couponCodeVo.setCoupon_id(couponCodeList.get(i).getCoupon_id());
            couponCodeVo.setUser_id(couponCodeList.get(i).getUser_id());
            couponCodeVo.setCoupon_name(gzgCouponInfo.getCoupon_name());
            couponCodeVo.setCoupon_use_system(gzgCouponInfo.getCoupon_use_system());
            couponCodeVo.setDenomination(gzgCouponInfo.getDenomination());//优惠金额

            couponCodeVo.setPull_time(couponCodeList.get(i).getPull_time());
            couponCodeVo.setInvalid_time(couponCodeList.get(i).getInvalid_time());

            if(GzgCouponUseStateEnum.NOT_USED.getId().equals(couponCodeList.get(i).getUse_status())){
                int i1 = DateUtil.compareDateTime(couponCodeList.get(i).getInvalid_time(), DateUtil.getNowTimeStr());
                if(i1==1){
                couponCodeVo.setUse_status(couponCodeList.get(i).getUse_status());
                }else {
                couponCodeVo.setUse_status(GzgCouponUseStateEnum.IS_INVALID.getId());
                }
            }else {
                couponCodeVo.setUse_status(couponCodeList.get(i).getUse_status());
            }

            couponCodeVo.setUse_time(couponCodeList.get(i).getUse_time());

            couponCodeVoList.add(couponCodeVo);
        }
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, couponCodeVoList);
    }
}

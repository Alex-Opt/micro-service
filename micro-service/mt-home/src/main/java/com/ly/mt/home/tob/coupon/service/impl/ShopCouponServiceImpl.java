package com.ly.mt.home.tob.coupon.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.dict.CouponCodePull;
import com.ly.mt.core.base.dict.CouponUseType;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.home.base.constant.ShopConstant;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.coupon.service.ShopCouponService;
import com.ly.mt.home.tob.coupon.vo.CouponCodeVo;
import com.ly.mt.home.tob.coupon.vo.CouponInfoVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * 商家优惠券实现类
 *
 * @author: linan
 * @date: 2019/7/17
 */
@Service
public class ShopCouponServiceImpl extends BaseServiceImpl implements ShopCouponService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public CouponInfoVo getCoupon(String id) {
        return getCoupon(id, null, getLoginUserId());
    }

    @Override
    public CouponInfoVo getCoupon(String couponId, String userId) {
        return getCoupon(null, couponId, userId);
    }

    @Override
    public List<CouponInfoVo> shopCouponList(String status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", getLoginUserId());
        jsonObject.put("useStatus", StringUtils.isEmpty(status) ? ShopConstant.ShopCouponStatus.NO_USE.getValue() : status);
        String result = callDataCenter(COUPON_INFO_FOR_SHOP, jsonObject);
        return JSONObject.parseArray(result, CouponInfoVo.class);
    }

    @Override
    public void updateCouponStatus(String id, String status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("useStatus", status);
        jsonObject.put("useTime", DateUtil.getNowTimeStr());
        callDataCenter(COUPON_CODE_UPDATE, jsonObject);
    }

    @Override
    public void sendActivityCoupon(String userId) {
        CouponCodeVo couponCodeVo = new CouponCodeVo.Builder()
                .id(SnowflakeUtil.getPrimaryKey(PrimaryKey.COUPON_CODE))
                .couponId(ShopConstant.SHOP_ACTIVITY_COUPON_ID)
                .userId(userId)
                .createTime(DateUtil.getNowTimeStr())
                .useStatus(CouponUseType.COUPON_NOT_USED.getId())
                .InvalidTime(DateUtil.date2TimeStr(DateUtil.offsetDate(new Date(), ShopConstant.SHOP_ACTIVITY_COUPON_VALIDITY_DAY)))
                .pullStatus(CouponCodePull.COUPON_CODE_PULL_STATUS_PULLED.getCode())
                .pullTime(DateUtil.getNowTimeStr()).build();
        callDataCenter(COUPON_CODE_INSERT, (JSONObject) JSON.toJSON(couponCodeVo));
    }

    private CouponInfoVo getCoupon(String id, String couponId, String userId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("couponId", couponId);
        jsonObject.put("userId", userId);
        String result = callDataCenter(COUPON_CODE_GET_FOR_SHOP, jsonObject);
        if(StringUtils.isNotEmpty(result)){
            return JSONObject.parseObject(result, CouponInfoVo.class);
        }
        return null;
    }
}

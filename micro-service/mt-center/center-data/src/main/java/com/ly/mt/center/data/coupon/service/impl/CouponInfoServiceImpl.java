package com.ly.mt.center.data.coupon.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.coupon.entity.CouponModel;
import com.ly.mt.center.data.coupon.mapper.CouponInfoMapper;
import com.ly.mt.center.data.coupon.service.CouponInfoService;
import com.ly.mt.center.data.coupon.entity.CouponInfo;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class CouponInfoServiceImpl extends BaseServiceImpl implements CouponInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CouponInfoServiceImpl.class);
    @Resource
    CouponInfoMapper mapper;

    /**
     * @Description 保存CouponInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertCouponInfo(JSONObject jsonObject) {
        try {
            CouponInfo couponInfo = JSONObject.toJavaObject(jsonObject, CouponInfo.class);
            if (StringUtil.isEmpty(couponInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertCouponInfo(couponInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CouponInfoServiceImpl.insertCouponInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除CouponInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteCouponInfo(JSONObject jsonObject) {
        try {
            CouponInfo couponInfo = JSONObject.toJavaObject(jsonObject, CouponInfo.class);
            if (StringUtil.isEmpty(couponInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteCouponInfo(couponInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CouponInfoServiceImpl.deleteCouponInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新CouponInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateCouponInfo(JSONObject jsonObject) {
        try {
            CouponInfo couponInfo = JSONObject.toJavaObject(jsonObject, CouponInfo.class);
            if (StringUtil.isEmpty(couponInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateCouponInfo(couponInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CouponInfoServiceImpl.updateCouponInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询CouponInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getCouponInfo(JSONObject jsonObject) {
        try {
            CouponInfo couponInfo = JSONObject.toJavaObject(jsonObject, CouponInfo.class);
            if (StringUtil.isEmpty(couponInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            couponInfo = mapper.getCouponInfo(couponInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, couponInfo);
        } catch (Exception e) {
            LOGGER.error("CouponInfoServiceImpl.getCouponInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 根据spuId查询优惠券CouponInfo数据
     *  到家C使用
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson getCouponInfoBySpuId(JSONObject jsonObject) {
        try {
            String spuId = jsonObject.getString("spu_id");
            if (StringUtil.isEmpty(spuId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getCouponInfoBySpuId(spuId));
        } catch (Exception e) {
            LOGGER.error("CouponInfoServiceImpl.getCouponInfoBySpuId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 根据userId查询优惠券CouponInfo数据
     *
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson getCouponInfoByUserId(JSONObject jsonObject) {
        try {
            String userId = jsonObject.getString("user_id");
            if (StringUtil.isEmpty(userId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            String nowTime = DateUtil.getNowTimeStr();
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getCouponInfoByUserId(userId, nowTime));
        } catch (Exception e) {
            LOGGER.error("CouponInfoServiceImpl.getCouponInfoByUserId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getNewUserCoupons(JSONObject jsonObject) {
        try {
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getNewUserCoupons(DateUtil.getNowTimeStr()));
        } catch (Exception e) {
            LOGGER.error("CouponInfoServiceImpl.getNewUserCoupons 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }

    }

    @Override
    public ResponseJson getNewUserCouponsSpree(JSONObject jsonObject) {
        try {
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getNewUserCouponsSpree(DateUtil.getNowTimeStr()));
        } catch (Exception e) {
            LOGGER.error("CouponInfoServiceImpl.getNewUserCouponsSpree 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getCouponInfoByUserIdForShop(JSONObject jsonObject) {
        try {
            CouponModel couponModel = JSONObject.toJavaObject(jsonObject, CouponModel.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getCouponInfoByUserIdForShop(couponModel));
        } catch (Exception e) {
            LOGGER.error("CouponInfoServiceImpl.getCouponInfoByUserIdForShop 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
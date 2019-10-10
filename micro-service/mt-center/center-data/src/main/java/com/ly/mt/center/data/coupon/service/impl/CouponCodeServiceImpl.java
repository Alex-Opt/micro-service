package com.ly.mt.center.data.coupon.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.coupon.entity.CouponCode;
import com.ly.mt.center.data.coupon.entity.CouponInfo;
import com.ly.mt.center.data.coupon.mapper.CouponCodeMapper;
import com.ly.mt.center.data.coupon.service.CouponCodeService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_COUPON_CODE;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class CouponCodeServiceImpl extends BaseServiceImpl implements CouponCodeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CouponCodeServiceImpl.class);
    @Resource
    CouponCodeMapper mapper;

    /**
     * @Description 保存CouponCode
     * @Author taoye
     */
    @Override
    public ResponseJson insertCouponCode(JSONObject jsonObject) {
        try {
            CouponCode couponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            if (StringUtil.isEmpty(couponCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertCouponCode(couponCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CouponCodeServiceImpl.insertCouponCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除CouponCode
     * @Author taoye
     */
    @Override
    public ResponseJson deleteCouponCode(JSONObject jsonObject) {
        try {
            CouponCode couponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            if (StringUtil.isEmpty(couponCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteCouponCode(couponCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CouponCodeServiceImpl.deleteCouponCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新CouponCode
     * @Author taoye
     */
    @Override
    public ResponseJson updateCouponCode(JSONObject jsonObject) {
        try {
            CouponCode couponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            if (StringUtil.isEmpty(couponCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateCouponCode(couponCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CouponCodeServiceImpl.updateCouponCodeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询CouponCode
     * @Author taoye
     */
    @Override
    public ResponseJson getCouponCode(JSONObject jsonObject) {
        try {
            CouponCode couponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            if (StringUtil.isEmpty(couponCode.getCoupon_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            couponCode = mapper.getCouponCode(couponCode);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, couponCode);
        } catch (Exception e) {
            LOGGER.error("CouponCodeServiceImpl.getCouponCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 用户兑换 兑换码
     *
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson updateCouponCodeByCode(JSONObject jsonObject) {
        try {
            CouponCode couponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.updateConponCodeByCode(couponCode));
        } catch (Exception e) {
            LOGGER.error("CouponCodeServiceImpl.updateConponCodeByCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 根据用户id,优惠券id查询用户是否领取该优惠券
     *
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson getUserCouponByUserIdAndCouponIdList(JSONObject jsonObject) {
        try {
            Long userId = jsonObject.getLong("userId");
            String couponIdList = jsonObject.getString("couponIdList");
            List<Long> couponIds = JSONObject.parseArray(couponIdList, Long.class);
            List<CouponCode> couponCodes = mapper.queryCouponListByUserIdAndCouponIds(userId, couponIds);
            if (couponCodes != null && couponCodes.size() > 0) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, "true");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, "false");
        } catch (Exception e) {
            LOGGER.error("CouponCodeServiceImpl.getUserCouponByUserIdAndCouponIdList:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 更新用户领取优惠券大礼包的数据信息到coupon_code
     *
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson updateCouponCodeUserInfo(JSONObject jsonObject) {
        try {
            String userId = jsonObject.getString("userId");
            String couponListStr = jsonObject.getString("couponListStr");
            List<CouponInfo> couponInfoList = JSONObject.parseArray(couponListStr, CouponInfo.class);
            CouponCode cc = null;
            for (CouponInfo couponInfo : couponInfoList) {
                cc = new CouponCode();
                cc.setId(SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_COUPON_CODE));
                cc.setCoupon_id(couponInfo.getId());
                cc.setUser_id(userId);
                cc.setPull_status("2");
                cc.setPull_time(DateUtil.getNowTimeStr());
                String validity_day = couponInfo.getValidity_day();
                LOGGER.info("---------------------------------获取到的过期时间值：{}",validity_day);
                if(StringUtil.isNotEmpty(validity_day)){
                    Date date = DateUtil.offsetDate(new Date(), Integer.valueOf(validity_day));
                    cc.setInvalid_time(DateUtil.date2TimeStr(date));
                }else{
                    cc.setInvalid_time(couponInfo.getEnd_time());
                }
                cc.setUse_status("1");
                cc.setCreate_time(DateUtil.getNowTimeStr());
                mapper.insertCouponCode(cc);
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, "false");
        } catch (Exception e) {
            LOGGER.error("CouponCodeServiceImpl.updateCouponCodeUserInfo:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson batchQueryCouponCode(JSONObject jsonObject) {
        String couponIds = jsonObject.getString("couponIds");
        Long userId = jsonObject.getLong("userId");
        String[] couponIdsArr = couponIds.split(",");
        List<Long> couponList = new ArrayList<>(couponIdsArr.length);
        for (String str : couponIdsArr) {
            couponList.add(Long.parseLong(str));
        }
        try {
            List<Map<String, String>> maps = mapper.countCouponCodeByUserIdAndCouponIds(userId, couponList);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, maps);
        } catch (Exception e) {
            LOGGER.error("CouponCodeServiceImpl.batchQueryCouponCode:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }

    }

    @Override
    public ResponseJson getUserCouponByUserIdAndCouponCode(JSONObject jsonObject) {
        Long userId = jsonObject.getLong("user_id");
        String couponCode = jsonObject.getString("coupon_code");
        try {
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getCouponCodeInfoByUserIdAndCouponCode(userId, couponCode));
        } catch (Exception e) {
            LOGGER.error("CouponCodeServiceImpl.getUserCouponByUserIdAndCouponCode:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getOneRepeatCouponCode(JSONObject jsonObject) {
        String code = jsonObject.getString("coupon_code");
        try {
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getOneCouponCodeByCode(code));
        } catch (Exception e) {
            LOGGER.error("CouponCodeServiceImpl.getOneRepeatCouponCode:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }

    }

    /**
     * @Description 查询CouponCode
     * @Author taoye
     */
    @Override
    public ResponseJson getCouponCodeForShop(JSONObject jsonObject) {
        try {
            CouponCode couponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getCouponCodeForShop(couponCode));
        } catch (Exception e) {
            LOGGER.error("CouponCodeServiceImpl.getCouponCodeForShop出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
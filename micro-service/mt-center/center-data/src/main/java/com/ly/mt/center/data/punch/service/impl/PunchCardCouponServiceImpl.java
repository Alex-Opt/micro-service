package com.ly.mt.center.data.punch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.punch.mapper.PunchCardCouponMapper;
import com.ly.mt.center.data.punch.service.PunchCardCouponService;
import com.ly.mt.center.data.punch.entity.PunchCardCoupon;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class PunchCardCouponServiceImpl extends BaseServiceImpl implements PunchCardCouponService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PunchCardCouponServiceImpl.class);
    @Resource
    PunchCardCouponMapper mapper;

    /**
     * @Description 插入PunchCardCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson insertPunchCardCoupon(JSONObject jsonObject) {
        try {
            PunchCardCoupon punchCardCoupon = JSONObject.toJavaObject(jsonObject, PunchCardCoupon.class);
            mapper.insertPunchCardCoupon(punchCardCoupon);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PunchCardCouponServiceImpl.insertPunchCardCoupon出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除PunchCardCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson deletePunchCardCouponById(JSONObject jsonObject) {
        try {
            PunchCardCoupon punchCardCoupon = JSONObject.toJavaObject(jsonObject, PunchCardCoupon.class);
            mapper.deletePunchCardCouponById(punchCardCoupon);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PunchCardCouponServiceImpl.deletePunchCardCouponById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新PunchCardCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson updatePunchCardCouponById(JSONObject jsonObject) {
        try {
            PunchCardCoupon punchCardCoupon = JSONObject.toJavaObject(jsonObject, PunchCardCoupon.class);
            mapper.updatePunchCardCouponById(punchCardCoupon);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PunchCardCouponServiceImpl.updatePunchCardCouponById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询PunchCardCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson getPunchCardCoupon(JSONObject jsonObject) {
        try {
            PunchCardCoupon punchCardCoupon = JSONObject.toJavaObject(jsonObject, PunchCardCoupon.class);
            mapper.getPunchCardCoupon(punchCardCoupon);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PunchCardCouponServiceImpl.getPunchCardCoupon出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询PunchCardCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson getPunchCardCouponById(JSONObject jsonObject) {
        try {
            PunchCardCoupon punchCardCoupon = JSONObject.toJavaObject(jsonObject, PunchCardCoupon.class);
            mapper.getPunchCardCouponById(punchCardCoupon);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PunchCardCouponServiceImpl.getPunchCardCouponById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getPunchCardCouponByStatus(JSONObject jsonObject) {
        try {
            PunchCardCoupon punchCardCoupon = JSONObject.toJavaObject(jsonObject, PunchCardCoupon.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getPunchCardCouponByStatus(punchCardCoupon));
        } catch (Exception e) {
            LOGGER.error("PunchCardCouponServiceImpl.getPunchCardCouponByStatus出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.coupon.entity.CouponInfo;
import com.ly.mt.center.data.gzg.mapper.GzgCouponInfoMapper;
import com.ly.mt.center.data.gzg.service.GzgCouponInfoService;
import com.ly.mt.center.data.gzg.entity.GzgCouponInfo;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class GzgCouponInfoServiceImpl extends BaseServiceImpl implements GzgCouponInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgCouponInfoServiceImpl.class);
    @Resource
    GzgCouponInfoMapper mapper;

    /**
     * @Description 保存GzgCouponInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgCouponInfo(JSONObject jsonObject) {
        try {
            CouponInfo gzgCouponInfo = JSONObject.toJavaObject(jsonObject, CouponInfo.class);
            if (StringUtil.isEmpty(gzgCouponInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgCouponInfo(gzgCouponInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCouponInfoServiceImpl.insertGzgCouponInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgCouponInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgCouponInfo(JSONObject jsonObject) {
        try {
            CouponInfo gzgCouponInfo = JSONObject.toJavaObject(jsonObject, CouponInfo.class);
            if (StringUtil.isEmpty(gzgCouponInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgCouponInfo(gzgCouponInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCouponInfoServiceImpl.deleteGzgCouponInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgCouponInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgCouponInfo(JSONObject jsonObject) {
        try {
            CouponInfo gzgCouponInfo = JSONObject.toJavaObject(jsonObject, CouponInfo.class);
            if (StringUtil.isEmpty(gzgCouponInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgCouponInfo(gzgCouponInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCouponInfoServiceImpl.updateGzgCouponInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgCouponInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgCouponInfo(JSONObject jsonObject) {
        try {
            CouponInfo couponInfo = JSONObject.toJavaObject(jsonObject, CouponInfo.class);
            if (StringUtil.isEmpty(couponInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            CouponInfo gzgCouponInfo = mapper.getGzgCouponInfo(couponInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgCouponInfo);
        } catch (Exception e) {
            LOGGER.error("GzgCouponInfoServiceImpl.getGzgCouponInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
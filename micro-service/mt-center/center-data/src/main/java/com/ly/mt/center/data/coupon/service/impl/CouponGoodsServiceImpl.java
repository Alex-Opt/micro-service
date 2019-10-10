package com.ly.mt.center.data.coupon.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.coupon.mapper.CouponGoodsMapper;
import com.ly.mt.center.data.coupon.service.CouponGoodsService;
import com.ly.mt.center.data.coupon.entity.CouponGoods;
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
public class CouponGoodsServiceImpl extends BaseServiceImpl implements CouponGoodsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CouponGoodsServiceImpl.class);
    @Resource
    CouponGoodsMapper mapper;

    /**
     * @Description 保存CouponGoods
     * @Author taoye
     */
    @Override
    public ResponseJson insertCouponGoods(JSONObject jsonObject) {
        try {
            CouponGoods couponGoods = JSONObject.toJavaObject(jsonObject, CouponGoods.class);
            if (StringUtil.isEmpty(couponGoods.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertCouponGoods(couponGoods);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CouponGoodsServiceImpl.insertCouponGoods出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除CouponGoods
     * @Author taoye
     */
    @Override
    public ResponseJson deleteCouponGoods(JSONObject jsonObject) {
        try {
            CouponGoods couponGoods = JSONObject.toJavaObject(jsonObject, CouponGoods.class);
            if (StringUtil.isEmpty(couponGoods.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteCouponGoods(couponGoods);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CouponGoodsServiceImpl.deleteCouponGoods出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新CouponGoods
     * @Author taoye
     */
    @Override
    public ResponseJson updateCouponGoods(JSONObject jsonObject) {
        try {
            CouponGoods couponGoods = JSONObject.toJavaObject(jsonObject, CouponGoods.class);
            if (StringUtil.isEmpty(couponGoods.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateCouponGoods(couponGoods);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CouponGoodsServiceImpl.updateCouponGoodsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询CouponGoods
     * @Author taoye
     */
    @Override
    public ResponseJson getCouponGoods(JSONObject jsonObject) {
        try {
            CouponGoods couponGoods = JSONObject.toJavaObject(jsonObject, CouponGoods.class);
            if (StringUtil.isEmpty(couponGoods.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            couponGoods = mapper.getCouponGoods(couponGoods);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, couponGoods);
        } catch (Exception e) {
            LOGGER.error("CouponGoodsServiceImpl.getCouponGoods出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
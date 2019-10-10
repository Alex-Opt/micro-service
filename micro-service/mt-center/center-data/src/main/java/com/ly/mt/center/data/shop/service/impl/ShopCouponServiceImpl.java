package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopCouponMapper;
import com.ly.mt.center.data.shop.service.ShopCouponService;
import com.ly.mt.center.data.shop.entity.ShopCoupon;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class ShopCouponServiceImpl extends BaseServiceImpl implements ShopCouponService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopCouponServiceImpl.class);
    @Resource
    ShopCouponMapper mapper;

    /**
     * @Description 保存ShopCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopCoupon(JSONObject jsonObject) {
        try {
            ShopCoupon shopCoupon = JSONObject.toJavaObject(jsonObject, ShopCoupon.class);
            if (StringUtil.isEmpty(shopCoupon.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopCoupon(shopCoupon);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopCouponServiceImpl.insertShopCoupon出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopCoupon(JSONObject jsonObject) {
        try {
            ShopCoupon shopCoupon = JSONObject.toJavaObject(jsonObject, ShopCoupon.class);
            if (StringUtil.isEmpty(shopCoupon.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopCoupon(shopCoupon);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopCouponServiceImpl.deleteShopCoupon出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopCoupon(JSONObject jsonObject) {
        try {
            ShopCoupon shopCoupon = JSONObject.toJavaObject(jsonObject, ShopCoupon.class);
            if (StringUtil.isEmpty(shopCoupon.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopCoupon(shopCoupon);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopCouponServiceImpl.updateShopCouponById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopCoupon
     * @Author taoye
     */
    @Override
    public ResponseJson getShopCoupon(JSONObject jsonObject) {
        try {
            ShopCoupon shopCoupon = JSONObject.toJavaObject(jsonObject, ShopCoupon.class);
            if (StringUtil.isEmpty(shopCoupon.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopCoupon = mapper.getShopCoupon(shopCoupon);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopCoupon);
        } catch (Exception e) {
            LOGGER.error("ShopCouponServiceImpl.getShopCoupon出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getShopCouponList(JSONObject jsonObject) {
        try {
            ShopCoupon shopCoupon = JSONObject.toJavaObject(jsonObject, ShopCoupon.class);
            if (StringUtil.isEmpty(shopCoupon.getShop_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            List<ShopCoupon> couponList = mapper.getShopCouponList(shopCoupon);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, couponList);
        } catch (Exception e) {
            LOGGER.error("ShopCouponServiceImpl.getShopCoupon出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

}
package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopPurchasesRefundMapper;
import com.ly.mt.center.data.shop.service.ShopPurchasesRefundService;
import com.ly.mt.center.data.shop.entity.ShopPurchasesRefund;
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
public class ShopPurchasesRefundServiceImpl extends BaseServiceImpl implements ShopPurchasesRefundService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopPurchasesRefundServiceImpl.class);
    @Resource
    ShopPurchasesRefundMapper mapper;

    /**
     * @Description 保存ShopPurchasesRefund
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopPurchasesRefund(JSONObject jsonObject) {
        try {
            ShopPurchasesRefund shopPurchasesRefund = JSONObject.toJavaObject(jsonObject, ShopPurchasesRefund.class);
            if (StringUtil.isEmpty(shopPurchasesRefund.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopPurchasesRefund(shopPurchasesRefund);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesRefundServiceImpl.insertShopPurchasesRefund出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopPurchasesRefund
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopPurchasesRefund(JSONObject jsonObject) {
        try {
            ShopPurchasesRefund shopPurchasesRefund = JSONObject.toJavaObject(jsonObject, ShopPurchasesRefund.class);
            if (StringUtil.isEmpty(shopPurchasesRefund.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopPurchasesRefund(shopPurchasesRefund);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesRefundServiceImpl.deleteShopPurchasesRefund出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopPurchasesRefund
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopPurchasesRefund(JSONObject jsonObject) {
        try {
            ShopPurchasesRefund shopPurchasesRefund = JSONObject.toJavaObject(jsonObject, ShopPurchasesRefund.class);
            if (StringUtil.isEmpty(shopPurchasesRefund.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopPurchasesRefund(shopPurchasesRefund);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesRefundServiceImpl.updateShopPurchasesRefundById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopPurchasesRefund
     * @Author taoye
     */
    @Override
    public ResponseJson getShopPurchasesRefund(JSONObject jsonObject) {
        try {
            ShopPurchasesRefund shopPurchasesRefund = JSONObject.toJavaObject(jsonObject, ShopPurchasesRefund.class);
            if (StringUtil.isEmpty(shopPurchasesRefund.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopPurchasesRefund = mapper.getShopPurchasesRefund(shopPurchasesRefund);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopPurchasesRefund);
        } catch (Exception e) {
            LOGGER.error("ShopPurchasesRefundServiceImpl.getShopPurchasesRefund出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
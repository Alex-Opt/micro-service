package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopShareLogMapper;
import com.ly.mt.center.data.shop.service.ShopShareLogService;
import com.ly.mt.center.data.shop.entity.ShopShareLog;
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
public class ShopShareLogServiceImpl extends BaseServiceImpl implements ShopShareLogService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopShareLogServiceImpl.class);
    @Resource
    ShopShareLogMapper mapper;

    /**
     * @Description 保存ShopShareLog
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopShareLog(JSONObject jsonObject) {
        try {
            ShopShareLog shopShareLog = JSONObject.toJavaObject(jsonObject, ShopShareLog.class);
            if (StringUtil.isEmpty(shopShareLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopShareLog(shopShareLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopShareLogServiceImpl.insertShopShareLog出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopShareLog
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopShareLog(JSONObject jsonObject) {
        try {
            ShopShareLog shopShareLog = JSONObject.toJavaObject(jsonObject, ShopShareLog.class);
            if (StringUtil.isEmpty(shopShareLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopShareLog(shopShareLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopShareLogServiceImpl.deleteShopShareLog出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopShareLog
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopShareLog(JSONObject jsonObject) {
        try {
            ShopShareLog shopShareLog = JSONObject.toJavaObject(jsonObject, ShopShareLog.class);
            if (StringUtil.isEmpty(shopShareLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopShareLog(shopShareLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopShareLogServiceImpl.updateShopShareLogById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopShareLog
     * @Author taoye
     */
    @Override
    public ResponseJson getShopShareLog(JSONObject jsonObject) {
        try {
            ShopShareLog shopShareLog = JSONObject.toJavaObject(jsonObject, ShopShareLog.class);
            if (StringUtil.isEmpty(shopShareLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopShareLog = mapper.getShopShareLog(shopShareLog);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopShareLog);
        } catch (Exception e) {
            LOGGER.error("ShopShareLogServiceImpl.getShopShareLog出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
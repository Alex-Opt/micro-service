package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopProfitLogsMapper;
import com.ly.mt.center.data.shop.service.ShopProfitLogsService;
import com.ly.mt.center.data.shop.entity.ShopProfitLogs;
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
public class ShopProfitLogsServiceImpl extends BaseServiceImpl implements ShopProfitLogsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopProfitLogsServiceImpl.class);
    @Resource
    ShopProfitLogsMapper mapper;

    /**
     * @Description 保存ShopProfitLogs
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopProfitLogs(JSONObject jsonObject) {
        try {
            ShopProfitLogs shopProfitLogs = JSONObject.toJavaObject(jsonObject, ShopProfitLogs.class);
            if (StringUtil.isEmpty(shopProfitLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopProfitLogs(shopProfitLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopProfitLogsServiceImpl.insertShopProfitLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopProfitLogs
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopProfitLogs(JSONObject jsonObject) {
        try {
            ShopProfitLogs shopProfitLogs = JSONObject.toJavaObject(jsonObject, ShopProfitLogs.class);
            if (StringUtil.isEmpty(shopProfitLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopProfitLogs(shopProfitLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopProfitLogsServiceImpl.deleteShopProfitLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopProfitLogs
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopProfitLogs(JSONObject jsonObject) {
        try {
            ShopProfitLogs shopProfitLogs = JSONObject.toJavaObject(jsonObject, ShopProfitLogs.class);
            if (StringUtil.isEmpty(shopProfitLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopProfitLogs(shopProfitLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopProfitLogsServiceImpl.updateShopProfitLogsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopProfitLogs
     * @Author taoye
     */
    @Override
    public ResponseJson getShopProfitLogs(JSONObject jsonObject) {
        try {
            ShopProfitLogs shopProfitLogs = JSONObject.toJavaObject(jsonObject, ShopProfitLogs.class);
            if (StringUtil.isEmpty(shopProfitLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopProfitLogs = mapper.getShopProfitLogs(shopProfitLogs);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopProfitLogs);
        } catch (Exception e) {
            LOGGER.error("ShopProfitLogsServiceImpl.getShopProfitLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
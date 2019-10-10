package com.ly.mt.center.data.warehouse.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.warehouse.mapper.WarehouseInfoMapper;
import com.ly.mt.center.data.warehouse.service.WarehouseInfoService;
import com.ly.mt.center.data.warehouse.entity.WarehouseInfo;
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
public class WarehouseInfoServiceImpl extends BaseServiceImpl implements WarehouseInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WarehouseInfoServiceImpl.class);
    @Resource
    WarehouseInfoMapper mapper;

    /**
     * @Description 保存WarehouseInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertWarehouseInfo(JSONObject jsonObject) {
        try {
            WarehouseInfo warehouseInfo = JSONObject.toJavaObject(jsonObject, WarehouseInfo.class);
            if (StringUtil.isEmpty(warehouseInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertWarehouseInfo(warehouseInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("WarehouseInfoServiceImpl.insertWarehouseInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除WarehouseInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteWarehouseInfo(JSONObject jsonObject) {
        try {
            WarehouseInfo warehouseInfo = JSONObject.toJavaObject(jsonObject, WarehouseInfo.class);
            if (StringUtil.isEmpty(warehouseInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteWarehouseInfo(warehouseInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("WarehouseInfoServiceImpl.deleteWarehouseInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新WarehouseInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateWarehouseInfo(JSONObject jsonObject) {
        try {
            WarehouseInfo warehouseInfo = JSONObject.toJavaObject(jsonObject, WarehouseInfo.class);
            if (StringUtil.isEmpty(warehouseInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateWarehouseInfo(warehouseInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("WarehouseInfoServiceImpl.updateWarehouseInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询WarehouseInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getWarehouseInfo(JSONObject jsonObject) {
        try {
            WarehouseInfo warehouseInfo = JSONObject.toJavaObject(jsonObject, WarehouseInfo.class);
            if (StringUtil.isEmpty(warehouseInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            warehouseInfo = mapper.getWarehouseInfo(warehouseInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, warehouseInfo);
        } catch (Exception e) {
            LOGGER.error("WarehouseInfoServiceImpl.getWarehouseInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
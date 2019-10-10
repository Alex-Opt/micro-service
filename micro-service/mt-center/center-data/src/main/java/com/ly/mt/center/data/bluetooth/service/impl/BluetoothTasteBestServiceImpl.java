package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.bluetooth.entity.BluetoothTasteBest;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothTasteBestMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothTasteBestService;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class BluetoothTasteBestServiceImpl extends BaseServiceImpl implements BluetoothTasteBestService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothTasteBestServiceImpl.class);
    @Resource
    BluetoothTasteBestMapper mapper;

    /**
     * @Description 插入BluetoothTasteBest
     * @Author wanghongliang
     */
    @Override
    public ResponseJson insertBluetoothTasteBest(JSONObject jsonObject) {
        try {
            BluetoothTasteBest bluetoothTasteBest = JSONObject.toJavaObject(jsonObject, BluetoothTasteBest.class);
            mapper.insertBluetoothTasteBest(bluetoothTasteBest);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothTasteBestServiceImpl.insertBluetoothTasteBest出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除BluetoothTasteBest
     * @Author wanghongliang
     */
    @Override
    public ResponseJson deleteBluetoothTasteBestById(JSONObject jsonObject) {
        try {
            BluetoothTasteBest bluetoothTasteBest = JSONObject.toJavaObject(jsonObject, BluetoothTasteBest.class);
            mapper.deleteBluetoothTasteBestById(bluetoothTasteBest);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothTasteBestServiceImpl.deleteBluetoothTasteBestById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新BluetoothTasteBest
     * @Author wanghongliang
     */
    @Override
    public ResponseJson updateBluetoothTasteBestById(JSONObject jsonObject) {
        try {
            BluetoothTasteBest bluetoothTasteBest = JSONObject.toJavaObject(jsonObject, BluetoothTasteBest.class);
            mapper.updateBluetoothTasteBestById(bluetoothTasteBest);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothTasteBestServiceImpl.updateBluetoothTasteBestById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询BluetoothTasteBest
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothTasteBest(JSONObject jsonObject) {
        try {
            BluetoothTasteBest bluetoothTasteBest = JSONObject.toJavaObject(jsonObject, BluetoothTasteBest.class);
            mapper.getBluetoothTasteBest(bluetoothTasteBest);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothTasteBestServiceImpl.getBluetoothTasteBest出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询BluetoothTasteBest
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothTasteBestById(JSONObject jsonObject) {
        try {
            BluetoothTasteBest bluetoothTasteBest = JSONObject.toJavaObject(jsonObject, BluetoothTasteBest.class);
            mapper.getBluetoothTasteBestById(bluetoothTasteBest);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothTasteBestServiceImpl.getBluetoothTasteBestById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /*
     * @Description 根据烟弹id 获取烟弹最佳口感
     * @Author WHL
     */
    @Override
    public ResponseJson getBluetoothTasteBestByTastId(JSONObject jsonObject) {
        try {
            BluetoothTasteBest bluetoothTasteBest = JSONObject.toJavaObject(jsonObject,BluetoothTasteBest.class);
            Map<String, Object> result = new HashMap<>(16);
            List<BluetoothTasteBest> tasteList = mapper.getBluetoothTasteBestByTastId(bluetoothTasteBest);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tasteList);
        } catch (Exception e) {
            LOGGER.error("BluetoothTasteBestServiceImpl.getBluetoothTasteBestByTastId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
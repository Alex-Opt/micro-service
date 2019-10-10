package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.bluetooth.entity.BluetoothIndexLog;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothIndexLogMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothIndexLogService;
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
public class BluetoothIndexLogServiceImpl extends BaseServiceImpl implements BluetoothIndexLogService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothIndexLogServiceImpl.class);
    @Resource
    BluetoothIndexLogMapper mapper;

    /**
     * @Description 插入BluetoothIndexLog
     * @Author wanghongliang
     */
    @Override
    public ResponseJson insertBluetoothIndexLog(JSONObject jsonObject) {
        try {
            BluetoothIndexLog bluetoothIndexLog = JSONObject.toJavaObject(jsonObject, BluetoothIndexLog.class);
            mapper.insertBluetoothIndexLog(bluetoothIndexLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothIndexLogServiceImpl.insertBluetoothIndexLog出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除BluetoothIndexLog
     * @Author wanghongliang
     */
    @Override
    public ResponseJson deleteBluetoothIndexLogById(JSONObject jsonObject) {
        try {
            BluetoothIndexLog bluetoothIndexLog = JSONObject.toJavaObject(jsonObject, BluetoothIndexLog.class);
            mapper.deleteBluetoothIndexLogById(bluetoothIndexLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothIndexLogServiceImpl.deleteBluetoothIndexLogById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新BluetoothIndexLog
     * @Author wanghongliang
     */
    @Override
    public ResponseJson updateBluetoothIndexLogById(JSONObject jsonObject) {
        try {
            BluetoothIndexLog bluetoothIndexLog = JSONObject.toJavaObject(jsonObject, BluetoothIndexLog.class);
            mapper.updateBluetoothIndexLogById(bluetoothIndexLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothIndexLogServiceImpl.updateBluetoothIndexLogById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询BluetoothIndexLog
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothIndexLog(JSONObject jsonObject) {
        try {
            BluetoothIndexLog bluetoothIndexLog = JSONObject.toJavaObject(jsonObject, BluetoothIndexLog.class);
            mapper.getBluetoothIndexLog(bluetoothIndexLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothIndexLogServiceImpl.getBluetoothIndexLog出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询BluetoothIndexLog
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothIndexLogById(JSONObject jsonObject) {
        try {
            BluetoothIndexLog bluetoothIndexLog = JSONObject.toJavaObject(jsonObject, BluetoothIndexLog.class);
            mapper.getBluetoothIndexLogById(bluetoothIndexLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothIndexLogServiceImpl.getBluetoothIndexLogById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
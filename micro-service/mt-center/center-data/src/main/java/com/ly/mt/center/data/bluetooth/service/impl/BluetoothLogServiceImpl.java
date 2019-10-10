package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.bluetooth.entity.BluetoothCountLog;
import com.ly.mt.center.data.bluetooth.entity.BluetoothLog;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothLogMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothLogService;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class BluetoothLogServiceImpl extends BaseServiceImpl implements BluetoothLogService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothLogServiceImpl.class);
    @Resource
    BluetoothLogMapper mapper;

    /**
     * @Description 插入BluetoothLog
     * @Author wanghongliang
     */
    @Override
    public ResponseJson insertBluetoothLog(JSONObject jsonObject) {
        try {
            List<BluetoothLog> bluetoothLogList =(List<BluetoothLog>) jsonObject.get("bluetoothLogList");
            int i =mapper.insertBluetoothLog(bluetoothLogList);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothLogServiceImpl.insertBluetoothLog出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson countBluetoothLogSmokingDataHour(JSONObject jsonObject) {
        try {
            BluetoothLog bluetoothLog = JSONObject.toJavaObject(jsonObject, BluetoothLog.class);
            List<BluetoothCountLog> list = mapper.countBluetoothLogSmokingDataHour(bluetoothLog);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
        } catch (Exception e) {
            LOGGER.error("BluetoothLogServiceImpl.countBluetoothLogSmokingDataHour:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson countBluetoothLogSmokingDataDay(JSONObject jsonObject) {
        try {
            BluetoothLog bluetoothLog = JSONObject.toJavaObject(jsonObject, BluetoothLog.class);
            List<BluetoothCountLog> list = mapper.countBluetoothLogSmokingDataDay(bluetoothLog);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
        } catch (Exception e) {
            LOGGER.error("BluetoothLogServiceImpl.countBluetoothLogSmokingDataDay:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 统计有抽烟数据的天数-不连续
     * @Author
     */
    @Override
    public ResponseJson countBluetoothLogSmokingHaveDataDay(JSONObject jsonObject) {
        try {
            BluetoothLog bluetoothLog = JSONObject.toJavaObject(jsonObject, BluetoothLog.class);
            List<BluetoothCountLog> list = mapper.countBluetoothLogSmokingHaveDataDay(bluetoothLog);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
        } catch (Exception e) {
            LOGGER.error("BluetoothLogServiceImpl.countBluetoothLogSmokingHaveDataDay:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 根据条件统计抽烟的口数
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson totalBluetoothLogSmokingMonthNumber(JSONObject jsonObject) {
        try {
            BluetoothLog bluetoothLog = JSONObject.toJavaObject(jsonObject, BluetoothLog.class);
            int i = mapper.totalBluetoothLogSmokingMonthNumber(bluetoothLog);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, i);
        } catch (Exception e) {
            LOGGER.error("BluetoothLogServiceImpl.totalBluetoothLogSmokingMonthNumber:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
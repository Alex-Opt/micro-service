package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.bluetooth.entity.BluetoothToken;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothTokenMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothTokenService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 操作token Service
 */
@Service
public class BluetoothTokenServiceImpl implements BluetoothTokenService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothTokenServiceImpl.class);
    @Resource
    BluetoothTokenMapper mapper;

    @Override
    public ResponseJson insertBluetoothToken(JSONObject jsonObject) {
        try {
            BluetoothToken bluetoothToken = JSONObject.toJavaObject(jsonObject, BluetoothToken.class);
            int  i = mapper.insert(bluetoothToken);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("BluetoothTokenServiceImpl.insertBluetoothToken出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getBluetoothToken(JSONObject jsonObject) {
        try {
            BluetoothToken bluetoothToken = JSONObject.toJavaObject(jsonObject, BluetoothToken.class);
            String token = mapper.getToken(bluetoothToken);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, token);
        } catch (Exception e) {
            LOGGER.error("BluetoothTokenServiceImpl.getToken出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson updateBluetoothToken(JSONObject jsonObject) {
        try {
            BluetoothToken bluetoothToken = JSONObject.toJavaObject(jsonObject, BluetoothToken.class);
            int i = mapper.updateToken(bluetoothToken);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("BluetoothTokenServiceImpl.updateBluetoothToken:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}

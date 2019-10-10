package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.bluetooth.entity.BluetoothUserTasteInfo;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothTasteInfoMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothTasteInfoService;
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
public class BluetoothTasteInfoServiceImpl extends BaseServiceImpl implements BluetoothTasteInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothTasteInfoServiceImpl.class);
    @Resource
    BluetoothTasteInfoMapper mapper;

    @Override
    public ResponseJson getBluetoothTasteList(JSONObject jsonObject) {
        try {
            BluetoothUserTasteInfo bluetoothUserTasteInfo = JSONObject.toJavaObject(jsonObject,BluetoothUserTasteInfo.class);
            List<BluetoothUserTasteInfo> tasteList = mapper.getBluetoothTasteList(bluetoothUserTasteInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tasteList);
        } catch (Exception e) {
            LOGGER.error("BluetoothTasteInfoServiceImpl.getBluetoothTasteList出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
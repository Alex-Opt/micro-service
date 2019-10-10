package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.bluetooth.entity.BluetoothFirmwareSetting;
import com.ly.mt.center.data.bluetooth.entity.BluetoothIndexLog;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothFirmwareSettingMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothFirmwareSettingService;
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
public class BluetoothFirmwareSettingServiceImpl extends BaseServiceImpl implements BluetoothFirmwareSettingService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothFirmwareSettingServiceImpl.class);
    @Resource
    BluetoothFirmwareSettingMapper mapper;

    /**
     * @Description 根据条件查询BluetoothFirmwareSetting
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothFirmwareSetting(JSONObject jsonObject) {
        try {
            BluetoothFirmwareSetting bluetoothFirmwareSettingParam = JSONObject.toJavaObject(jsonObject, BluetoothFirmwareSetting.class);
            BluetoothFirmwareSetting bluetoothFirmwareSetting = mapper.getBluetoothFirmwareSetting(bluetoothFirmwareSettingParam);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,bluetoothFirmwareSetting);
        } catch (Exception e) {
            LOGGER.error("BluetoothFirmwareSettingServiceImpl.getBluetoothFirmwareSetting出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
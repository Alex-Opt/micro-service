package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.bluetooth.entity.BluetoothUserTaste;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothUserTasteMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothUserTasteService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_BLUETOOTH_USER_TASTE;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class BluetoothUserTasteServiceImpl extends BaseServiceImpl implements BluetoothUserTasteService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothUserTasteServiceImpl.class);
    @Resource
    BluetoothUserTasteMapper mapper;

    /**
     * @Description 插入BluetoothUserTaste
     * @Author wanghongliang
     */
    @Override
    public ResponseJson insertBluetoothUserTaste(JSONObject jsonObject) {
        try {
            BluetoothUserTaste bluetoothUserTaste = JSONObject.toJavaObject(jsonObject, BluetoothUserTaste.class);
            String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_BLUETOOTH_USER_TASTE);
            bluetoothUserTaste.setId(id);
            bluetoothUserTaste.setCreate_time(DateUtil.getNowTimeStr());
            mapper.insertBluetoothUserTaste(bluetoothUserTaste);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserTasteServiceImpl.insertBluetoothUserTaste出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除BluetoothUserTaste
     * @Author wanghongliang
     */
    @Override
    public ResponseJson deleteBluetoothUserTasteById(JSONObject jsonObject) {
        try {
            BluetoothUserTaste bluetoothUserTaste = JSONObject.toJavaObject(jsonObject, BluetoothUserTaste.class);
            mapper.deleteBluetoothUserTasteById(bluetoothUserTaste);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserTasteServiceImpl.deleteBluetoothUserTasteById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新BluetoothUserTaste
     * @Author wanghongliang
     */
    @Override
    public ResponseJson updateBluetoothUserTasteById(JSONObject jsonObject) {
        try {
            BluetoothUserTaste bluetoothUserTaste = JSONObject.toJavaObject(jsonObject, BluetoothUserTaste.class);
            mapper.updateBluetoothUserTasteById(bluetoothUserTaste);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserTasteServiceImpl.updateBluetoothUserTasteById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询BluetoothUserTaste
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothUserTaste(JSONObject jsonObject) {
        try {
            BluetoothUserTaste bluetoothUserTaste = JSONObject.toJavaObject(jsonObject, BluetoothUserTaste.class);
            BluetoothUserTaste bluetoothUserTasteNew = mapper.getBluetoothUserTaste(bluetoothUserTaste);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,bluetoothUserTasteNew);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserTasteServiceImpl.getBluetoothUserTaste出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询BluetoothUserTaste
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothUserTasteById(JSONObject jsonObject) {
        try {
            BluetoothUserTaste bluetoothUserTaste = JSONObject.toJavaObject(jsonObject, BluetoothUserTaste.class);
            mapper.getBluetoothUserTasteById(bluetoothUserTaste);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserTasteServiceImpl.getBluetoothUserTasteById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
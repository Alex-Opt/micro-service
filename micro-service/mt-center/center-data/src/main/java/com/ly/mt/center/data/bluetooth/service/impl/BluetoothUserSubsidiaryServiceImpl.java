package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.bluetooth.entity.BluetoothUserSubsidiary;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothUserSubsidiaryMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothUserSubsidiaryService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_BLUETOOTH_USER_SUBSIDIARY;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class BluetoothUserSubsidiaryServiceImpl extends BaseServiceImpl implements BluetoothUserSubsidiaryService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothUserSubsidiaryServiceImpl.class);
    @Resource
    BluetoothUserSubsidiaryMapper mapper;

    /**
     * @Description 插入BluetoothUserSubsidiary
     * @Author whl
     */
    @Override
    public ResponseJson insertBluetoothUserSubsidiary(JSONObject jsonObject) {
        try {
            BluetoothUserSubsidiary bluetoothUserSubsidiary = JSONObject.toJavaObject(jsonObject, BluetoothUserSubsidiary.class);
            String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_BLUETOOTH_USER_SUBSIDIARY);
            bluetoothUserSubsidiary.setId(id);
            bluetoothUserSubsidiary.setCreate_time(DateUtil.getNowTimeStr());
            bluetoothUserSubsidiary.setModify_time(DateUtil.getNowTimeStr());
            mapper.insertBluetoothUserSubsidiary(bluetoothUserSubsidiary);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserSubsidiaryServiceImpl.insertBluetoothUserSubsidiary出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 统计BluetoothUserSubsidiary
     * @Author whl
     */
    @Override
    public ResponseJson countBluetoothUserSubsidiary(JSONObject jsonObject){
        try {
            BluetoothUserSubsidiary bluetoothUserSubsidiary = JSONObject.toJavaObject(jsonObject, BluetoothUserSubsidiary.class);
            int i = mapper.countBluetoothUserSubsidiary(bluetoothUserSubsidiary);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserSubsidiaryServiceImpl.countBluetoothUserSubsidiary:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 更新BluetoothUserSubsidiary
     * @Author whl
     */
    @Override
    public ResponseJson updateBluetoothUserSubsidiary(JSONObject jsonObject){
        try {
            BluetoothUserSubsidiary bluetoothUserSubsidiary = JSONObject.toJavaObject(jsonObject, BluetoothUserSubsidiary.class);
            bluetoothUserSubsidiary.setModify_time(DateUtil.getNowTimeStr());
            int i = mapper.updateBluetoothUserSubsidiary(bluetoothUserSubsidiary);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserSubsidiaryServiceImpl.updateBluetoothUserSubsidiary:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询BluetoothUserSubsidiary
     * @Author whl
     */
    @Override
    public ResponseJson getBluetoothUserSubsidiary(JSONObject jsonObject) {
        try {
            BluetoothUserSubsidiary bluetoothUserSubsidiary = JSONObject.toJavaObject(jsonObject, BluetoothUserSubsidiary.class);
            BluetoothUserSubsidiary subsidiary = mapper.getBluetoothUserSubsidiary(bluetoothUserSubsidiary);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,subsidiary);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserSubsidiaryServiceImpl.getBluetoothUserSubsidiary出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

}
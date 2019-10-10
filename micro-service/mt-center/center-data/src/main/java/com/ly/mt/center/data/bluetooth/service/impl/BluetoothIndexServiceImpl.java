package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.bluetooth.entity.BluetoothIndex;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothIndexMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothIndexService;
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
public class BluetoothIndexServiceImpl extends BaseServiceImpl implements BluetoothIndexService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothIndexServiceImpl.class);
    @Resource
    BluetoothIndexMapper mapper;

    /**
     * @Description 插入BluetoothIndex
     * @Author wanghongliang
     */
    @Override
    public ResponseJson insertBluetoothIndex(JSONObject jsonObject) {
        try {
            List<BluetoothIndex> bluetoothIndexList =(List<BluetoothIndex>) jsonObject.get("bluetoothIndexVoList");
            int i =mapper.insertBluetoothIndex(bluetoothIndexList);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("BluetoothIndexServiceImpl.insertBluetoothIndex出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除BluetoothIndex
     * @Author wanghongliang
     */
    @Override
    public ResponseJson deleteBluetoothIndexByCondtion(JSONObject jsonObject) {
        try {
            BluetoothIndex bluetoothIndex = JSONObject.toJavaObject(jsonObject, BluetoothIndex.class);
            int i = mapper.deleteBluetoothIndexByCondtion(bluetoothIndex);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("BluetoothIndexServiceImpl.deleteBluetoothIndexByCondtion出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新BluetoothIndex
     * @Author wanghongliang
     */
    @Override
    public ResponseJson updateBluetoothIndexById(JSONObject jsonObject) {
        try {
            BluetoothIndex bluetoothIndex = JSONObject.toJavaObject(jsonObject, BluetoothIndex.class);
            mapper.updateBluetoothIndexById(bluetoothIndex);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothIndexServiceImpl.updateBluetoothIndexById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询BluetoothIndex
     * @Author WHL
     */
    @Override
    public ResponseJson getBluetoothIndex(JSONObject jsonObject) {
        try {
            BluetoothIndex bluetoothIndex = JSONObject.toJavaObject(jsonObject, BluetoothIndex.class);
            List<BluetoothIndex> bluetoothIndexList = mapper.getBluetoothIndex(bluetoothIndex);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,bluetoothIndexList);
        } catch (Exception e) {
            LOGGER.error("BluetoothIndexServiceImpl.getBluetoothIndex出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询BluetoothIndex
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothIndexById(JSONObject jsonObject) {
        try {
            BluetoothIndex bluetoothIndex = JSONObject.toJavaObject(jsonObject, BluetoothIndex.class);
            mapper.getBluetoothIndexById(bluetoothIndex);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothIndexServiceImpl.getBluetoothIndexById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
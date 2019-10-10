package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.bluetooth.entity.BluetoothNewsCollect;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothNewsCollectMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothNewsCollectService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @description:
 * @author: wanghongliang
 * @create: 2019-09-07 14:51
 **/
@Service
public class BluetoothNewsCollectServiceImpl implements BluetoothNewsCollectService {

    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothNewsCollectServiceImpl.class);
    @Resource
    BluetoothNewsCollectMapper mapper;
    /**
     * @Description 根据id删除用户收藏 删除时需要减去收藏数量
     * @Author wanghongliang
     */
    @Override
    public ResponseJson deleteBluetoothNewsCollect(JSONObject jsonObject) {
        try {
            BluetoothNewsCollect bluetoothNewsCollect = JSONObject.toJavaObject(jsonObject,BluetoothNewsCollect.class);
            int i = mapper.deleteBluetoothNewsCollect(bluetoothNewsCollect);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, i);
        } catch (Exception e) {
            LOGGER.error("BluetoothNewsCollectServiceImpl.deleteBluetoothNewsCollect:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 用户加入收藏 加入收藏时需要更新新闻收藏数量
     * @Author wanghongliang
     */
    @Override
    public ResponseJson insertBluetoothNewsCollect(JSONObject jsonObject) {
        try {
            BluetoothNewsCollect bluetoothNewsCollect = JSONObject.toJavaObject(jsonObject,BluetoothNewsCollect.class);
            int i = mapper.insertBluetoothNewsCollect(bluetoothNewsCollect);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, i);
        } catch (Exception e) {
            LOGGER.error("BluetoothNewsCollectServiceImpl.insertBluetoothNewsCollect:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 查询用户是否收藏
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothNewsCollect(JSONObject jsonObject) {
        try {
            BluetoothNewsCollect bluetoothNewsCollect = JSONObject.toJavaObject(jsonObject,BluetoothNewsCollect.class);
            BluetoothNewsCollect bluetoothNewsCollect1 = mapper.selectBluetoothNewsCollect(bluetoothNewsCollect);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bluetoothNewsCollect1);
        } catch (Exception e) {
            LOGGER.error("BluetoothNewsCollectServiceImpl.getBluetoothNewsCollect:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
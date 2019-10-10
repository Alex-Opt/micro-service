package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.bluetooth.entity.BluetoothUserSuggestions;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothUserSuggestionsMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothUserSuggestionsService;
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
public class BluetoothUserSuggestionsServiceImpl extends BaseServiceImpl implements BluetoothUserSuggestionsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothUserSuggestionsServiceImpl.class);
    @Resource
    BluetoothUserSuggestionsMapper mapper;

    /**
     * @Description 插入BluetoothUserSuggestions
     * @Author wanghongliang
     */
    @Override
    public ResponseJson insertBluetoothUserSuggestions(JSONObject jsonObject) {
        try {
            BluetoothUserSuggestions bluetoothUserSuggestions = JSONObject.toJavaObject(jsonObject, BluetoothUserSuggestions.class);
            String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_BLUETOOTH_USER_SUBSIDIARY);
            bluetoothUserSuggestions.setId(id);
            bluetoothUserSuggestions.setCreate_time(DateUtil.getNowTimeStr());
            int i = mapper.insertBluetoothUserSuggestions(bluetoothUserSuggestions);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserSuggestionsServiceImpl.insertBluetoothUserSuggestions出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
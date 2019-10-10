package com.ly.mt.cabinet.c.Device.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.cabinet.c.Device.service.DeviceCallStartService;
import com.ly.mt.cabinet.c.enumEntity.DeviceServiceEnum;
import com.ly.mt.cabinet.c.tools.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeviceCallStartServiceImpl implements DeviceCallStartService {

    private static final String ENCODING = "UTF-8";
    @Override
    public JSONObject openDeviceChannel(String jsonStr,String token) {
        JSONObject json = JSON.parseObject(jsonStr);
        //json.put("mctId",mctId);
        Map<String,String> header = new HashMap<>();
        header.put("token",token);
        Map<String, String> param = JSON.parseObject(json.toJSONString(), new TypeReference<Map<String, String>>() {
        });
        JSONObject jsonObject = HttpClientUtil.httpPostForm(DeviceServiceEnum.DEVICE_START.getValue(),param,header,ENCODING);
        return jsonObject;
    }
}

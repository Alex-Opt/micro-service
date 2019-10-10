package com.ly.mt.cabinet.c.Device.service;

import com.alibaba.fastjson.JSONObject;

public interface DeviceCallStartService {
    JSONObject openDeviceChannel(String json, String token);
}

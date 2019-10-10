package com.ly.mt.cabinet.c.Device.service;

public interface DeviceCheckService {
    /**
     * 设备启动回调
     * @param json
     * @return
     */
    void deviceCheckCallBack(String json) throws Exception;
}

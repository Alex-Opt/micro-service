package com.ly.mt.cabinet.c.enumEntity;

public enum DeviceServiceEnum {
    GET_TOKEN(1,"http://iot.open.ayilink.com/iot-client/open/api/auth/login"),
    DEVICE_INFO(2,"http://iot.open.ayilink.com/iot-client/open/api/device/getOne"),
    DEVICE_CHECK(3,"http://iot.open.ayilink.com/iot-client/open/api/device/checkAvailable"),
    DEVICE_REAL_CHECK(4,"http://iot.open.ayilink.com/iot-client/open/api/device/isAvailable"),
    DEVICE_START(5,"http://iot.open.ayilink.com/iot-client/open/api/device/start"),
    DEVICE_LIST(6,"http://iot.open.ayilink.com/iot-client/open/api/device/list");
    int key;
    String value;
    DeviceServiceEnum(int key, String value){
        this.value = value;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

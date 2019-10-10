package com.ly.mt.cabinet.c.Device.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.Device.service.DeviceCheckService;
import com.ly.mt.cabinet.c.entity.GzgDeviceCheckLogVo;
import com.ly.mt.core.base.util.DateUtil;
import org.springframework.stereotype.Service;

import static com.ly.mt.core.feign.DataCenterMethod.GZG_DEVICE_CHECK_LOG_INSERT;

@Service
public class DeviceCheckServiceImpl extends BaseServiceImpl implements DeviceCheckService {

    @Override
    public void deviceCheckCallBack(String json) throws Exception {
        JSONObject adviceData = JSON.parseObject(json);
        GzgDeviceCheckLogVo gzgDeviceCheckLog = new GzgDeviceCheckLogVo();
        gzgDeviceCheckLog.setContent(json);
        gzgDeviceCheckLog.setGmt_create(DateUtil.getNowTimeStr());
        gzgDeviceCheckLog.setGmt_modify(DateUtil.getNowTimeStr());
//        deviceCheckMapper.insertDeviceCheckLog(deviceCheckLog);

        callDataCenter(GZG_DEVICE_CHECK_LOG_INSERT, adviceData);//功能未完，待续
    }
}

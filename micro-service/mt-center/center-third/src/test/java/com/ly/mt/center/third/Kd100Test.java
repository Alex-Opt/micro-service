package com.ly.mt.center.third;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.kd100.service.Kd100Service;
import com.ly.mt.core.base.entity.ResponseJson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Kd100Test extends CenterThirdApplicationTest {
    @Autowired
    Kd100Service service;

    @Test
    public void testGetDeliveryInfo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("com", "yuantong");
        jsonObject.put("num", "800398697338673378");
        ResponseJson responseJson = service.getDeliveryInfo(jsonObject);
        System.out.println(responseJson.getCode());
        System.out.println(responseJson.getMsg());
        System.out.println(responseJson.getResult());
    }
}
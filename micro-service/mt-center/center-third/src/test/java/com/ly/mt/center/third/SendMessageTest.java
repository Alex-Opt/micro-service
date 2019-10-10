package com.ly.mt.center.third;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.al.service.AlSmsService;
import com.ly.mt.center.third.fn.service.FnTokenService;
import com.ly.mt.center.third.fn.util.FnRandomUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发短信
 */
public class SendMessageTest extends CenterThirdApplicationTest {
    @Autowired
    AlSmsService alSmsService;

    @Test
    public void testGetToken() {
        List<String> smsList = new ArrayList<>();

        smsList.add("17600142573");



        JSONObject jsonObject = null;
        for (String phone: smsList) {
            jsonObject = new JSONObject();
            jsonObject.put("phone", phone);
            jsonObject.put("signName", "MOTI");
            jsonObject.put("templateCode", "SMS_173230241");
            Map templateMap = new HashMap(1);
            templateMap.put("time", "14");
            String templateParam = JSONObject.toJSONString(templateMap);
            jsonObject.put("templateParam", templateParam);
            alSmsService.sendSms(jsonObject);
        }
    }
}
package com.ly.mt.payment.server.wx;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.payment.server.PaymentServerApplicationTest;
import com.ly.mt.payment.server.wx.service.WxpayService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WxpayServiceTest extends PaymentServerApplicationTest {
    @Autowired
    WxpayService service;

    @Test
    public void testWapPay() throws Exception {
        JSONObject jsonObject = service.wapPay("");
        System.out.println(jsonObject.getString("result"));
    }
}
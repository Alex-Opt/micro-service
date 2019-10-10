package com.ly.mt.payment.server.alipay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.payment.PaymentOrderVo;
import com.ly.mt.payment.server.PaymentServerApplicationTest;
import com.ly.mt.payment.server.alipay.service.AlipayService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AlipayServiceTest extends PaymentServerApplicationTest {
    @Autowired
    AlipayService service;

    @Test
    public void testWapPay() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("returnUrl", "mall.motivape.cn/payment/alipay/notify");
        jsonObject.put("orderName", "魔笛雾化器");
        jsonObject.put("orderNo", "582952298675179521");
        jsonObject.put("orderMoney", "100.01");
        String json = JSON.toJSONString(jsonObject);
        service.wapPay(json);
    }

    @Test
    public void testNotify() throws Exception {
        String json = "{\"gmt_create\":\"2019-06-06 22:13:24\",\"charset\":\"UTF-8\",\"seller_email\":\"lyrpde6341@sandbox.com\",\"notify_time\":\"2019-06-06 22:13:24\",\"functionName\":\"notify\",\"subject\":\"魔笛雾化器\",\"sign\":\"Xp3Ba7zd9FfjFbdZXnioX1LFv7Hcu+lSgKYBCaInrywcTT9zKVKb5OF9x/mqSZpe1wNU/nXHy70G8Vm3W26wYfl/m4jZdCgNMGAUqRPgkIpEBA9oTMq8ozTQXydiqdaPbZUNdi5cn1LEfyeWb8PfcbFYsVC95iJJ4rFHKiO1MROf4S0mRz+314+frf4DQGSNYJg2inLhK3wSF6lgGj1W+ZRBC3ZmZxb/tfdcKBoL2VlSzru8xyaBNHpQcs0GCa6OmmdqONhJnMs5ABVKDQgcRBjGyBzIUdkC1XSNkBWV4aurjH4FHbVFHZmo7z6zzr3oQfJY/fth+oy7uiD7NxNetg==\",\"buyer_id\":\"2088102178765139\",\"serviceName\":\"alipayServiceImpl\",\"version\":\"1.0\",\"notify_id\":\"2019060600222221324065131000295764\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"586316694990360577\",\"total_amount\":\"49.00\",\"trade_status\":\"WAIT_BUYER_PAY\",\"trade_no\":\"2019060622001465131000046609\",\"auth_app_id\":\"2016092800617095\",\"buyer_logon_id\":\"fcs***@sandbox.com\",\"app_id\":\"2016092800617095\",\"sign_type\":\"RSA2\",\"seller_id\":\"2088102177675260\"}";
        service.notify(json);
    }

    @Test
    public void testStatus() throws  Exception {
        PaymentOrderVo vo = new PaymentOrderVo();
        vo.setOrderNo("588817912442654721");
        String json = JSONObject.toJSONString(vo);
        JSONObject jsonObject = service.status(json);
        System.out.println(jsonObject.toJSONString());
    }
}

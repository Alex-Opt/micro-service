package com.ly.mt.open.wechat.po;

import com.ly.mt.open.wechat.util.EncryptUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Sign {

    public static Map<String, String> sign(String jsapi_ticket, String url)
    {
        Map<String, String> ret = new HashMap();

        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();

        String signature = "";

        String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;

        signature = EncryptUtils.SHA1(string1);

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String create_nonce_str()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static String create_timestamp()
    {
        return Long.toString(System.currentTimeMillis() / 1000L);
    }
}

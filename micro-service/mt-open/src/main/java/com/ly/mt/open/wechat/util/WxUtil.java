package com.ly.mt.open.wechat.util;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.open.wechat.po.Sign;
import com.ly.mt.open.wechat.po.TicketJson;
import com.ly.mt.open.wechat.po.TokenJson;
import com.ly.mt.open.wechat.po.WxParams;

import java.util.Map;

public class WxUtil {
/*    public static String APPID = "wx114064aafb0919fa";
    public static String SECRET = "1f077e20b7ddc89dd254289b2eda72a3";*/
    public static String APPID = "wx80a7401a02e0f8ec";
    public static String SECRET = "f2db2177474c44575f6522932db0a1f3";

    private static TokenJson getAccess_token() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" +
                SECRET;
        try {

            String result = HttpRequestUtil.doGet(url);
            JSONObject jsonObject = JSONObject.parseObject(result);
            return JSONObject.toJavaObject(jsonObject,TokenJson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static TicketJson getTicket(String token) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token + "&type=jsapi";
        try {
            String result = HttpRequestUtil.doGet(url);
            JSONObject jsonObject = JSONObject.parseObject(result);
            return JSONObject.toJavaObject(jsonObject,TicketJson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> getSign(String url) {
        try {
            if (WxParams.token == null) {
                TokenJson tokenJson = getAccess_token();
                if (tokenJson != null) {
                    WxParams.token = tokenJson.getAccess_token();
                    WxParams.tokenTime = String.valueOf(System.currentTimeMillis());
                    WxParams.tokenExpires = String.valueOf(tokenJson.getExpires_in());
                }
            }
            long tokenTimeLong = Long.valueOf(WxParams.tokenTime);
            long tokenExpiresLong = Long.valueOf(WxParams.tokenExpires);

            long differ = (System.currentTimeMillis() - tokenTimeLong) / 1000L;
            if (differ > tokenExpiresLong - 1800L) {
                TokenJson tokenJson = getAccess_token();
                if (tokenJson != null) {
                    WxParams.token = tokenJson.getAccess_token();
                    WxParams.tokenTime = String.valueOf(System.currentTimeMillis());
                    WxParams.tokenExpires = String.valueOf(tokenJson.getExpires_in());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (WxParams.ticket == null) {
                TicketJson ticketJson = getTicket(WxParams.token);
                if (ticketJson != null) {
                    WxParams.ticket = ticketJson.getTicket();
                    WxParams.ticketTime = String.valueOf(System.currentTimeMillis());
                    WxParams.ticketExpires = ticketJson.getExpires_in();
                }
            }
            long ticketTimeLong = Long.parseLong(WxParams.ticketTime);

            long ticketExpiresLong = Long.valueOf(WxParams.ticketExpires);

            long differ = (System.currentTimeMillis() - ticketTimeLong) / 1000L;
            if (differ > ticketExpiresLong - 1800L) {
                TicketJson ticketJson = getTicket(WxParams.token);
                if (ticketJson != null) {
                    WxParams.ticket = ticketJson.getTicket();
                    WxParams.ticketTime = String.valueOf(System.currentTimeMillis());
                    WxParams.ticketExpires = ticketJson.getExpires_in();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> ret = Sign.sign(WxParams.ticket, url);
        return ret;
    }

}

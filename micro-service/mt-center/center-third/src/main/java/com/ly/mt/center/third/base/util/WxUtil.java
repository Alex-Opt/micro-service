package com.ly.mt.center.third.base.util;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.userCertificate.service.impl.UserCertificateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: my-blue-tooth
 * @description: 微信工具类
 * @author: wanghongliang
 * @create: 2019-08-23 14:50
 **/
public class WxUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(WxUtil.class);

    /**
     * 获取微信公众号token
     * @param appid
     * @param secret
     * @param accessTokenurl 获取accessTokenurl
     * @return
     */
    public static String getAccessToken(String appid,String secret,String accessTokenurl) {
        String access_token = "";
        accessTokenurl +="&appid="+appid+"&secret="+secret;//获取token接口
        try {
            //获取code后，请求以下链接获取access_token
            URL urlGet = new URL(accessTokenurl);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject accessTokenObject = JSONObject.parseObject(message);
            LOGGER.info("微信返回token为："+String.valueOf(accessTokenObject));
            access_token = accessTokenObject.getString("access_token");
            is.close();
            return access_token;
        } catch (Exception e) {
            LOGGER.error("获取微信accessToken失败",e);
        }
        return access_token;
    }

    /**
     * 获取
     * @param access_token
     * @return
     */
    public static String getTicket(String access_token,String ticketUrl) {
        String ticket = null;
        ticketUrl = ticketUrl+"?access_token="+ access_token +"&type=jsapi";//这个url链接和参数不能变
        try {
            URL urlGet = new URL(ticketUrl);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject ticketObject = JSONObject.parseObject(message);
            LOGGER.info("微信返回ticket为："+String.valueOf(ticketObject));
            ticket = ticketObject.getString("ticket");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    /**
     * 微信签名加密
     * @param decript
     * @return
     */
    public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("微信加密字符串失败失败",e);
        }
        return "";
    }
}
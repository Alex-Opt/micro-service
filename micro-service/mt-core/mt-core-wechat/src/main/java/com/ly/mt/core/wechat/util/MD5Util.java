package com.ly.mt.core.wechat.util;

import java.security.MessageDigest;

/**
 * MD5加密
 *
 * @author taoye
 */
public class MD5Util {
    public static String md5(String str) throws Exception {
        StringBuilder result = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(str.getBytes("UTF-8"));
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                result.append("0");
            }
            result.append(hex.toUpperCase());
        }
        return result.toString();
    }
}
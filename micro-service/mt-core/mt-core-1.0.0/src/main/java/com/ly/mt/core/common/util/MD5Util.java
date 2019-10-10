package com.ly.mt.core.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description MD5加密
 * @Author taoye
 */
public class MD5Util {

    private static MessageDigest mdigest = null;

    private static char digits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

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

    private static MessageDigest getMdInst() {
        if (null == mdigest) {
            try {
                mdigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return mdigest;
    }

    /**
     * 快递100MD5专用
     * @param s
     * @return
     */
    public static String encode(String s) {
        if(null == s) {
            return "";
        }
        try {
            byte[] bytes = s.getBytes();
            getMdInst().update(bytes);
            byte[] md = getMdInst().digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = digits[byte0 >>> 4 & 0xf];
                str[k++] = digits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
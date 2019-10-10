package com.ly.mt.core.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
}
package com.ly.mt.core.base.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 加解密工具类
 * @author  wanglong
 */
public class AESUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(AESUtil.class);

    /**
     * 加密算法
     * @param strKey
     * @param strIn
     * @return
     * @throws Exception
     */
    public static String encrypt(String strKey, String strIn, byte[] vi) {
        Cipher cipher = null;
        LOGGER.info("未编码之前字符前格式:"+getEncoding(strIn));
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(
                    Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(strKey.getBytes("UTF-8"), "AES"),
                    new IvParameterSpec(vi)
            );

            byte[] encrypted = cipher.doFinal(strIn.getBytes("UTF-8"));
            String encryptedString = Base64.encodeBase64String(encrypted);
            LOGGER.info("编码之后字符格式:"+getEncoding(encryptedString));

            //return   java.util.Base64.getEncoder().encodeToString(encrypted);
            //String encoded = encryptedString.replaceAll("[\\s*\t\n\r]", "");
            return encryptedString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GB2312
                String s = encode;
                return s; //是的话，返回“GB2312“，以下代码同理
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是ISO-8859-1
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是UTF-8
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GBK
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }
        /**
         * 解密算法
         * @param strKey
         * @param strIn
         * @return
         */
    public static String decrypt(String strKey, String strIn, byte[] vi) {
        try {
//                strIn = URLDecoder.decode(strIn,"utf-8");
            byte[] encrypted1 = Base64.decodeBase64(strIn);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keyspec = new SecretKeySpec(strKey.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(vi);
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

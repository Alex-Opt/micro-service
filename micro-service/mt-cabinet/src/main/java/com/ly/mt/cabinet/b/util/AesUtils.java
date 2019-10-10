package com.ly.mt.cabinet.b.util;

import com.ly.mt.cabinet.b.common.exception.AESException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 加解密
 */
public class AesUtils {
    /**
     * AES加解密
     */
    private static final String ALGORITHM = "AES";
    /**
     * 默认的初始化向量值
     */
    private static final String IV_DEFAULT = "dasj72kjdkkdNNdhd29o0O";
    /**
     * 默认加密的KEY
     */
    private static final String KEY_DEFAULT = "dsakSD9Jsk2qhd24HHh";
    /**
     * 工作模式：CBC
     */
    private static final String TRANSFORM_CBC_PKCS5 = "AES/CBC/PKCS5Padding";

    /**
     * 工作模式：ECB
     */
    private static final String TRANSFORM_ECB_PKCS5 = "AES/ECB/PKCS5Padding";

    /**
     * 基于CBC工作模式的AES加密
     * @param value 待加密字符串
     * @param iv 初始化向量值，如果不填则使用默认值
     * @return java.lang.String
     */
    public static String encryptCbcMode(final String value,String iv){
        if(!StringUtils.isEmpty(value)){
            if(iv == null || iv.length() != 16){
                iv = IV_DEFAULT;
            }

            //密码
            final SecretKeySpec keySpec = getSecretKey(KEY_DEFAULT);
            //初始化向量器
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(getUTF8Bytes(iv));
            try {
                Cipher encipher = Cipher.getInstance(TRANSFORM_CBC_PKCS5);
                //加密模式
                encipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
                //使用AES加密
                byte[] encrypted = encipher.doFinal(getUTF8Bytes(value));
                //然后转成BASE64返回
                return Base64.encodeBase64String(encrypted);
            } catch (Exception e) {
                throw new AESException("encryptCbcMode，加密异常，e=%s",e.getMessage());
            }
        }

        return null;
    }

    /**
     * 基于CBC工作模式的AES解密
     * @param encryptedStr AES加密之后的字符串
     * @param iv 初始化向量值，如果不填则使用默认值
     * @return java.lang.String
     */
    public static String decryptCbcMode(final String encryptedStr, String iv){
        if(!StringUtils.isEmpty(encryptedStr)){
            if(iv == null || iv.length() != 16){
                iv = IV_DEFAULT;
            }

            //密码
//            final SecretKeySpec keySpec = new SecretKeySpec(getUTF8Bytes(key),"AES");
            final SecretKeySpec keySpec = getSecretKey(KEY_DEFAULT);
//            初始化向量器
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(getUTF8Bytes(iv));

            try {
                Cipher encipher = Cipher.getInstance(TRANSFORM_CBC_PKCS5);

                //加密模式
                encipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
                //先用BASE64解密
                byte[] encryptedBytes = Base64.decodeBase64(encryptedStr);
                //然后再AES解密
                byte[] originalBytes = encipher.doFinal(encryptedBytes);
                //返回字符串
                return new String(originalBytes);
            } catch (Exception e) {
                throw new AESException("decryptCbcMode 解密异常, e=%s",e.getMessage());
            }
        }

        return null;
    }


    /**
     * 基于ECB工作模式的AES加密
     * @param value 待加密字符串
     * @return java.lang.String
     */
    public static String encryptEcbMode(final String value){
        if(!StringUtils.isEmpty(value)){
            //密码
            final SecretKeySpec keySpec = getSecretKey(KEY_DEFAULT);

            try {
                Cipher encipher = Cipher.getInstance(TRANSFORM_ECB_PKCS5);

                //加密模式
                encipher.init(Cipher.ENCRYPT_MODE, keySpec);
                //使用AES加密
                byte[] encrypted = encipher.doFinal(getUTF8Bytes(value));
                //然后转成BASE64返回
                return Base64.encodeBase64String(encrypted);
            } catch (Exception e) {
                throw new AESException("encryptEcbMode 加密异常, e=%s",e.getMessage());
            }
        }

        return null;
    }

    /**
     * 基于ECB工作模式的AES解密
     * @param encryptedStr AES加密之后的字符串
     * @return java.lang.String
     */
    public static String decryptEcbMode(final String encryptedStr){
        if(!StringUtils.isEmpty(encryptedStr)){

            //密码
            final SecretKeySpec keySpec = getSecretKey(KEY_DEFAULT);

            try {
                Cipher encipher = Cipher.getInstance(TRANSFORM_ECB_PKCS5);

                //加密模式
                encipher.init(Cipher.DECRYPT_MODE, keySpec);
                //先用BASE64解密
                byte[] encryptedBytes = Base64.decodeBase64(encryptedStr);
                //然后再AES解密
                byte[] originalBytes = encipher.doFinal(encryptedBytes);
                //返回字符串
                return new String(originalBytes);
            } catch (Exception e) {
                throw new AESException("decryptEcbMode 解密异常, e=%s",e.getMessage());
            }
        }

        return null;
    }

    /**
     * 将字符串转化为UTF8格式的byte数组
     *
     * @param input the input string
     * @return UTF8 bytes
     */
    private static byte[] getUTF8Bytes(String input) {
        return input.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 生成加密秘钥
     * @param KEY 明文秘钥
     * @return SecretKeySpec
     */
    private static SecretKeySpec getSecretKey(final String KEY) {
        //生成指定算法密钥
        KeyGenerator generator = null;

        try {
            generator = KeyGenerator.getInstance(ALGORITHM);

            //指定AES密钥长度（128、192、256）
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(getUTF8Bytes(KEY));
            generator.init(256, random);

            //生成一个密钥
            SecretKey secretKey = generator.generateKey();
            //转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {

        }

        return null;
    }

}

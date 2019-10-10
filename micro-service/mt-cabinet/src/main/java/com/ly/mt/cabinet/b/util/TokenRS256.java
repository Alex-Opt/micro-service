package com.ly.mt.cabinet.b.util;

import com.ly.mt.cabinet.b.login.vo.TokenInfo;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import net.minidev.json.JSONObject;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenRS256 {
    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(TokenRS256.class);

    /**
     * 这个方法采取的是RS256 非对称加密算法
     * @param userId 用户ID
     * @param phone  用户手机号s
     * @param rsaJWK
     * @return
     */
    public static String createToken(String userId,String phone,RSAKey rsaJWK) {
        //获取生成token
        Map<String, Object> map = new HashMap<>();
        Date date = new Date();

        //建立载荷，这些数据根据业务，自己定义。
        map.put("userId", userId);
        map.put("phone", phone);
        //生成时间
        map.put("startTime", date.getTime());
        map.put("project","GZG-B");//格子柜B
        try {
            String token = TokenUtils.creatTokenRS256(map,rsaJWK);
            return token;
        } catch (JOSEException e) {
            Logger.error("GZG-B-APP-token创建token失败",e);
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 解析token业务逻辑
     */
    public static TokenInfo validToken(String token, RSAKey rsaJWK) {
        TokenInfo tokenInfo = new TokenInfo();
        //解析token
        try {
            if (token != null) {
                Map<String, Object> validMap = TokenUtils.validRS256(token,rsaJWK);
                int i = (int) validMap.get("result");
                if (i == 0) {
                    Logger.info("GZG-B-APP-token解析成功");
                    JSONObject jsonObject = (JSONObject) validMap.get("data");
                    tokenInfo.setUserId(String.valueOf(jsonObject.get("userId")));
                    tokenInfo.setStartTime(String.valueOf(jsonObject.get("startTime")));
                    tokenInfo.setProject(String.valueOf(jsonObject.get("project")));
                    tokenInfo.setPhone(String.valueOf(jsonObject.get("phone")));
                    tokenInfo.setToken(token);
                    Logger.info("GZG-B-APP-userId是：" + jsonObject.get("userId")+" 生成时间是："+jsonObject.get("startTime")+" project为："+jsonObject.get("project"));
                    return tokenInfo;
                }
//                else if (i == 2) { //暂且不会出现2的情况
//                    System.out.println("token已经过期");
//                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return tokenInfo;
    }

    public static void main(String[] args) throws JOSEException {
        //获取token
        String uid = "603232323399536640";
        //获取钥匙
        RSAKey key = TokenUtils.getKey();

        //获取token
        String token = createToken(uid,uid,key);
        System.out.println("非对称加密的token："+token);
        //解析token
      //  ValidToken(token,key);
    }
}

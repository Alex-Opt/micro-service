package com.ly.mt.cabinet.b.util;

import com.ly.mt.cabinet.b.login.vo.TokenInfo;
import com.nimbusds.jose.JOSEException;
import net.minidev.json.JSONObject;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenHS256 {

    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(TokenHS256.class);

    /**
     * 这个方法采取的是HS256 对称加密算法
     */

    public static String createToken(String userId, String phone) {
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
            String token = TokenUtils.creatTokenHS256(map);
            System.out.println("token="+token);
            return token;
        } catch (JOSEException e) {
            Logger.error("GZG-B-APP-token创建token失败",e);
            e.printStackTrace();
        }
        return null;

    }

    //处理解析的业务逻辑
    public static TokenInfo validToken(String token) {
        //解析token
        TokenInfo tokenInfo = new TokenInfo();
        try {
            if (token != null) {

                Map<String, Object> validMap = TokenUtils.validHS256(token);
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
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return tokenInfo;
    }

    public static void main(String[] ages) {

        //获取token
        String uid = "kkksuejrmf";
        String token = createToken(uid,"1111");
        //解析token
        //ValidToken(token);

    }
}

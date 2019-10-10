package com.ly.mt.core.base.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.ly.mt.core.base.dict.YHHDAccessKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * @author wanglong
 */
//@Component
public class JwtUtil {
    public static String getEncodedJWT (String secret,String key) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withIssuer(key)
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400L * 1000))
                .sign(algorithm);
        return token;
    }

    /**
     * 解密
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser().setSigningKey(base64Security.getBytes()).parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 前三个参数为自己用户token的一些信息比如id，权限，名称等。不要将隐私信息放入（大家都可以获取到）
     *
     * @param map
     * @param base64Security
     * @return
     */
    public static String createJWT(Map<String, Object> map, String base64Security) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setPayload(new Gson().toJson(map)).signWith(signatureAlgorithm, base64Security.getBytes());
        return builder.compact();
    }
}

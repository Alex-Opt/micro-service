package com.ly.mt.home.tob.shopuser.service.impl;

import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.home.base.constant.JwtConstant;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.shopuser.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class TokenServiceImpl extends BaseServiceImpl implements TokenService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final Long serialVersionUID = -1830506273546l;

    private String secret = "secret";
    private Long defaultExpiration = 604800L;


    @Override
    public String generateToken(String shopId, String userId, String mobile, String userName, Long expiration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtConstant.CLAIM_KEY_USER, userId);
        claims.put(JwtConstant.CLAIM_KEY_SHOP, shopId);
        claims.put(JwtConstant.CLAIM_KEY_MOBILE, mobile);
        claims.put(JwtConstant.CLAIM_KEY_CREATED, new Date());
        claims.put(JwtConstant.CLAIM_KEY_USERNAME, userName);
        Date expirationDate;
        if (expiration == null) {
            expirationDate = new Date(System.currentTimeMillis() + defaultExpiration * 1000);
        } else {
            expirationDate = new Date(System.currentTimeMillis() + expiration * 1000);
        }

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        redisService.set(RedisKey.LOGIN_TOKEN_REDIS, mobile, token);
        return token;
    }

    @Override
    public Claims getClaimsFromToken(String token) {
        logger.info("getShopId request header Authorization={}", token);
        try {
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            return claims;
        } catch (Exception e) {
            logger.error("商家未登录:{}", e);
            throw new JwtException(e.getMessage());
        }
    }

    @Override
    public Boolean isTokenExpire(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expire = claims.getExpiration();
            return !expire.before(new Date());
        } catch (Exception e) {
            logger.error("商家未登录:{}", e);
            throw new JwtException(e.getMessage());
        }
    }
}

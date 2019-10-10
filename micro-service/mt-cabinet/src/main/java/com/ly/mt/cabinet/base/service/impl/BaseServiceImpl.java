package com.ly.mt.cabinet.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.login.vo.TokenInfo;
import com.ly.mt.cabinet.b.util.AesUtils;
import com.ly.mt.cabinet.base.service.BaseService;
import com.ly.mt.core.feign.service.impl.FeignServiceImpl;
import com.ly.mt.core.mq.service.MqService;
import com.ly.mt.core.redis.service.RedisService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 模块公共实现类，处理本模块公共方法
 * @Author taoye
 */
@Service
public class BaseServiceImpl extends FeignServiceImpl implements BaseService {

    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Resource
    public RedisService redisService;
    @Resource
    public MqService mqService;

    /**
     * @Description 解析token获取登录用户信息
     * @Author whl
     */
//    @Override
//    public TokenInfo getTokenInfo() {
//        TokenInfo tokenInfo = new TokenInfo();
//        try {
//            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            HttpServletRequest request = sra.getRequest();
//            String token = request.getHeader("Authorization");//解析token
//            //获取钥匙
//            tokenInfo = TokenHS256.validToken(token);
//            return tokenInfo;
//        } catch (Exception e) {
//            Logger.error("解析token报错：",e);
//        }
//        return tokenInfo;
//    }

    /**
     * 兼容老代码改造
     * @Description 解析token获取登录用户信息
     * @Author whl
     */
    @Override
    public TokenInfo getTokenInfo() {
        TokenInfo tokenInfo = new TokenInfo();
        try {
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
             HttpServletRequest request = sra.getRequest();
            String userId = (String) request.getAttribute("user_id");
            String phoneNo = (String) request.getAttribute("phoneNo");
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(phoneNo)) {
                String token = request.getHeader("token");
                String s = AesUtils.decryptEcbMode(token);
                TokenUserMessage userMessage = JSONObject.toJavaObject(JSONObject.parseObject(s), TokenUserMessage.class);
                if (StringUtils.isEmpty(userMessage.getUserId()) || StringUtils.isEmpty(userMessage.getPhoneNo())){
                    throw new IllegalArgumentException("非法登录");
                }
                userId = String.valueOf(userMessage.getUserId());
                phoneNo = userMessage.getPhoneNo();

            }
            tokenInfo.setPhone(phoneNo);
            tokenInfo.setUserId(userId);
            return tokenInfo;
        } catch (Exception e) {
            Logger.error("解析token报错：",e);
       }
        return tokenInfo;
    }
}
package com.ly.mt.cabinet.b.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.exception.AuthLoginException;
import com.ly.mt.cabinet.b.util.AesUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础信息controller
 */
public abstract class BaseMessageController {


    /**
     * 获取基础用户登录信息
     *
     * @param request
     * @return
     */
    public TokenUserMessage getTokenUserMessage(HttpServletRequest request) {
        try {
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
            return new TokenUserMessage(Long.valueOf(userId), phoneNo);
        } catch (IllegalArgumentException e) {
            throw new AuthLoginException(" It's not login");
        }
    }


    /**
     * 不带request的取token方法
     * @return
     */
    public TokenUserMessage getTokenUserMessage(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getTokenUserMessage(request);
    }


}

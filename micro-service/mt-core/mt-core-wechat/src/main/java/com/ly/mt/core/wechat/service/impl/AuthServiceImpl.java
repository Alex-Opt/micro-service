package com.ly.mt.core.wechat.service.impl;


import com.ly.mt.core.wechat.service.AuthService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 微信公众号认证登录
 *
 * @author taoye
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private WxMpService wxMpService;

    @Override
    public String getOpenId(String code) throws Exception {
        Assert.notNull(code, "WeChartAuthServiceImpl.getOpenId code must not be null");
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        return wxMpOAuth2AccessToken.getOpenId();
    }
}

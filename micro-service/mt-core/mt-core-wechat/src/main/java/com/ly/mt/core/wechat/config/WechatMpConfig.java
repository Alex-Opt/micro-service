package com.ly.mt.core.wechat.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static com.ly.mt.core.wechat.config.WeChatConstant.APP_ID;
import static com.ly.mt.core.wechat.config.WeChatConstant.APP_SECRET;

/**
 * WxMpService注册bean
 *
 * @author taoye
 */
@Component
public class WechatMpConfig {
    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId(APP_ID);
        wxMpDefaultConfig.setSecret(APP_SECRET);
        return wxMpDefaultConfig;
    }
}
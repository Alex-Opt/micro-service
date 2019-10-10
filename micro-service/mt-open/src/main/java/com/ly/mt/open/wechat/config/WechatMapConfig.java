package com.ly.mt.open.wechat.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信公众号信息注册成bean管理类
 *
 * @author pjt
 */
@Component
public class WechatMapConfig {


    @Autowired
    WechatAccountConfig config;

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        //WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId(config.getMpAppId());
        wxMpDefaultConfig.setSecret(config.getMpAppSecret());
       /* wxMpConfigStorage.setAppId(config.getMpAppId());
        wxMpConfigStorage.setSecret(config.getMpAppSecret());*/
        return wxMpDefaultConfig;
    }


}

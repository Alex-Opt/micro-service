package com.ly.mt.core.wechat.config;

import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

/**
 * 静态常量配置
 *
 * @author taoye
 */
public class WeChatConstant {
    private final static Logger LOGGER = LoggerFactory.getLogger(WeChatConstant.class);
    /**
     * appId
     */
    public static final String APP_ID = "wx80a7401a02e0f8ec";
    /**
     * appSecret
     */
    public static final String APP_SECRET = "f2db2177474c44575f6522932db0a1f3";
    /**
     * merchantId
     */
    public static final String MERCHANT_ID = "1519739731";
    /**
     * merchantKey
     */
    public static final String MERCHANT_KEY = "58Lei2Yan95kE42jI17mo87TI5312640";
    /**
     * 接口API——发放红包接口
     */
    public static final String WE_CHAT_API_SEND_RED_PACK = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
    /**
     * 接口API——查询红包记录
     */
    public static final String WE_CHAT_API_GET_HB_INFO = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo";
    /**
     * docker容器ip和宿主机ip对应关系
     */
    public static final Map IP_MPA = new HashMap() {{
        put("10.11.10.71", "39.96.184.163");
        put("10.11.10.72", "39.96.193.53");
        put("10.11.10.75", "39.96.162.35");
        put("10.11.10.76", "39.96.173.83");
        put("10.11.10.79", "39.106.191.160");
    }};
    /**
     * 回调地址
     */
    private static String WE_CHAT_NOTIFY;
    private static final String PROFILE_DEV = "dev";
    private static final String PROFILE_TEST = "test";
    private static final String PROFILE_GRAY = "gray";
    private static final String PROFILE_PROD = "prod";
    private static final String WE_CHAT_NOTIFY_TEST = "http://testopen.motivape.cn/api/open/notify/wxPay";
    private static final String WE_CHAT_NOTIFY_GRAY = "https://opengray.motivape.cn/open/notify/wxPay";
    private static final String WE_CHAT_NOTIFY_PROD = "https://open.motivape.cn/open/notify/wxPay";
    /**
     * 证书
     */
    private static final String P12_FILE_PATH = "com/ly/mt/core/wechat/config/20366D508A564BDCB13E7E2B5A46B6EA.p12";
    private static KeyStore KEY_STORE;


    /**
     * 根据环境获取回调地址
     *
     * @author taoye
     */
    public static String getWeChatNotifyByProfile(String profile) throws Exception {
        if (StringUtil.isEmpty(WE_CHAT_NOTIFY)) {
            if (PROFILE_DEV.equals(profile) || PROFILE_TEST.equals(profile)) {
                WE_CHAT_NOTIFY = WE_CHAT_NOTIFY_TEST;
            } else if (PROFILE_GRAY.equals(profile)) {
                WE_CHAT_NOTIFY = WE_CHAT_NOTIFY_GRAY;
            } else if (PROFILE_PROD.equals(profile)) {
                WE_CHAT_NOTIFY = WE_CHAT_NOTIFY_PROD;
            } else {
                LOGGER.error("WeChatConstant.getWeChatNotifyByProfile error profile = {} not in [dev,test,gray,prod]", profile);
            }
        }
        LOGGER.info("WeChatConstant.getWeChatNotifyByProfile profile = {}, WE_CHAT_NOTIFY = {}", profile, WE_CHAT_NOTIFY);
        return WE_CHAT_NOTIFY;
    }


    /**
     * 获取证书
     *
     * @author taoye
     */
    public static KeyStore getKeyStore() throws Exception {
        if (KEY_STORE == null) {
            KEY_STORE = KeyStore.getInstance("PKCS12");
            ClassPathResource classPathResource = new ClassPathResource(P12_FILE_PATH);
            InputStream instream = classPathResource.getInputStream();
            KEY_STORE.load(instream, MERCHANT_ID.toCharArray());
            instream.close();
        }
        return KEY_STORE;
    }
}
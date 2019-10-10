package com.ly.mt.core.wechat.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static com.ly.mt.core.wechat.config.WeChatConstant.MERCHANT_ID;
import static com.ly.mt.core.wechat.config.WeChatConstant.getKeyStore;

/**
 * 微信接口调用工具类
 *
 * @author taoye
 */
public class WeChatUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(WeChatUtil.class);

    /**
     * 获取微信接口签名
     *
     * @author taoye
     */
    public static String getWeChatSign(TreeMap<String, Object> map, String merchantKey) throws Exception {
        StringBuffer sb = new StringBuffer();
        // 所有参与传参的参数按照accsii排序（升序）
        Set es = map.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + merchantKey);
        return MD5Util.md5(sb.toString()).toUpperCase();
    }


    /**
     * 证书调用微信接口
     *
     * @param requestXml 请求参数
     * @return 响应参数
     * @throws Exception
     * @author taoye
     */
    public static String postWeChatWithCert(String requestXml, String url) throws Exception {
        LOGGER.info("RedPacketServiceImpl.postWeChat requestXml = {}", requestXml);
        // 指定读取证书格式为PKCS12
        KeyStore keyStore = getKeyStore();
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, MERCHANT_ID.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        // 设置httpclient的SSLSocketFactory
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        HttpPost httppost = new HttpPost(url);
        // 这里要设置编码，不然xml中有中文的话会提示签名失败或者展示乱码
        httppost.addHeader("Content-Type", "text/xml");
        StringEntity se = new StringEntity(requestXml, "UTF-8");
        httppost.setEntity(se);
        CloseableHttpResponse response = httpClient.execute(httppost);
        String responseXml = EntityUtils.toString(response.getEntity(), "UTF-8");
        LOGGER.info("RedPacketServiceImpl.postWeChat responseXml = {}", responseXml);
        response.close();
        httpClient.close();
        return responseXml;
    }
}
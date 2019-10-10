package com.ly.mt.core.common.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 *调用外部接口的工具类
 */
public class HttpUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 快递100要求调用为application/x-www-form-urlencoded类型的post请求
     * @param params
     * @param uri
     * @return
     */
    public static  String postQuery(List<NameValuePair> params, String uri) {
        String jsonOut = null;
        HttpPost post = new HttpPost(uri);
        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse resp = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(params));
            resp = client.execute(post);
            if (null != resp && null != resp.getEntity()) {
                jsonOut = EntityUtils.toString(resp.getEntity());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        } finally {
            try {
                resp.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(),e);
            }
        }
        return jsonOut;
    }
}

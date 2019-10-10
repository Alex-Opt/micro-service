package com.ly.mt.core.logistics.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.List;

/**
 * 调用外部接口的工具类
 *
 * @author zhanglifeng
 */
public class Kd100HttpUtils {
    /**
     * 快递100要求调用为application/x-www-form-urlencoded类型的post请求
     */
    public static String postKd100(List<NameValuePair> params, String uri) throws Exception {
        HttpPost post = new HttpPost(uri);
        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpClient client = HttpClientBuilder.create().build();
        String jsonOut = null;
        CloseableHttpResponse resp = client.execute(post);
        if (null != resp && null != resp.getEntity()) {
            jsonOut = EntityUtils.toString(resp.getEntity());
        }
        resp.close();
        return jsonOut;
    }
}
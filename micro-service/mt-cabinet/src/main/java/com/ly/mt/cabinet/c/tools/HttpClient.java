package com.ly.mt.cabinet.c.tools;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author evan
 * @date 2019-06-02 21:32
 * create by intellij 2019
 */
@Component
public class HttpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);
    public static final int SUCCESS = 200;

    CloseableHttpClient httpClient = null;
    CloseableHttpResponse response = null;

    private static class HttpClientHolder {
        private static HttpClient instance = new HttpClient();
    }

    private HttpClient() {
    }

    public static HttpClient getInstance() {
        return HttpClientHolder.instance;
    }

    public String doGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        return executeGet(httpGet);
    }

    public String doGet(String url, Map<String, Object> param) {
        HttpGet httpGet = new HttpGet(url);
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), (String) entry.getValue()));
        }
        String packParam = null;
        try {
            packParam = EntityUtils.toString(new UrlEncodedFormEntity(nvps, "utf-8"));
            httpGet.setURI(new URIBuilder(httpGet.getURI().toString() + "?" + param).build());
        } catch (Exception e) {
            LOGGER.error("call doGet with String.class and Map.class has error , the error message is {}", e.getMessage());
            e.printStackTrace();
        }
        return executeGet(httpGet);

    }

    public CloseableHttpResponse doPost(String url) {
        HttpPost httpPost = new HttpPost(url);
        return executePost(httpPost);
    }

    public CloseableHttpResponse doPost(String url, Map<String, Object> map) {
        HttpPost post = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), (String) entry.getValue()));
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("call doPost with String.class and Map.class has error , the error message is {} ", e.getMessage());
            e.printStackTrace();
        }
        return executePost(post);
    }

    public CloseableHttpResponse doPost(String url, String xml) {
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "application/xml");
        StringEntity entity = null;
        entity = new StringEntity(xml, "UTF-8");
        post.setEntity(entity);
        return executePost(post);
    }

    private CloseableHttpResponse executePost(HttpPost httpPost) {
        HttpEntity entity = null;
        String responseContent = null;
        httpClient = HttpClientBuilder.create().build();
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String executeGet(HttpGet httpGet) {
        HttpEntity httpEntity = null;
        String responseContent = null;
        httpClient = HttpClientBuilder.create().build();
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseContent;
    }
}

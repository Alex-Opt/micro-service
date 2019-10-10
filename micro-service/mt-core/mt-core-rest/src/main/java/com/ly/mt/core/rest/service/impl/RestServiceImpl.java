package com.ly.mt.core.rest.service.impl;


import com.ly.mt.core.rest.service.RestService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 外部接口调用服务
 *
 * @author taoye
 */
@Service
public class RestServiceImpl implements RestService {
    @Resource
    private RestTemplate restTemplate;


    @Override
    public String restTemplatePost(String serverUrl, String param) {
        return restTemplate.postForObject(serverUrl, param, String.class);
    }


    @Override
    public String restTemplateGet(String serverUrl, Map<String, Object> map) {
        return restTemplate.getForObject(serverUrl, String.class);
    }
}
package com.ly.mt.center.third.base.service.impl;

import com.ly.mt.center.third.base.service.BaseService;
import com.ly.mt.core.feign.service.impl.FeignServiceImpl;
import com.ly.mt.core.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description 模块公共实现类，处理本模块公共方法
 * @Author taoye
 */
@Service
public class BaseServiceImpl extends FeignServiceImpl implements BaseService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
    @Resource
    public RedisService redisService;
    @Resource
    public RestTemplate restTemplate;

    /**
     * @Description 调用外部接口
     * @Author taoye
     */
    @Override
    public String restTemplatePost(String serverUrl, String param) {
        try {
            LOGGER.info("调用第三方接口serverUrl={},param={}", serverUrl, param);
            return restTemplate.postForObject(serverUrl, param, String.class);
        } catch (Exception e) {
            LOGGER.error("调用第三方接口serverUrl={},param={}出错:", serverUrl, param, e);
            throw new RuntimeException(e);
        }
    }


    /**
     * @Description 调用外部接口
     * @Author taoye
     */
    @Override
    public String restTemplateGet(String serverUrl, Map<String, Object> map) {
        try {
            serverUrl = getServerUrl(serverUrl, map);
            LOGGER.info("调用第三方接口serverUrl={}", serverUrl);
            return restTemplate.getForObject(serverUrl, String.class);
        } catch (Exception e) {
            LOGGER.error("调用第三方接口serverUrl={}出错:", serverUrl, e);
            throw new RuntimeException(e);
        }
    }


    /**
     * @Description get请求拼接参数
     * @Author taoye
     */
    private String getServerUrl(String serverUrl, Map<String, Object> map) {
        if (null == map) {
            return serverUrl;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(serverUrl).append("?");
        map.forEach(
                (k, v) -> sb.append(k).append("=").append(v).append("&")
        );
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
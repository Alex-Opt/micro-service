package com.ly.mt.core.server.service.impl;

import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.rabbit.RabbitSendServer;
import com.ly.mt.core.server.service.CoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Primary
@Service
public class CoreServiceImpl implements CoreService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CoreServiceImpl.class);
    @Resource
    public RedisServer redisServer;
    @Resource
    public RestTemplate restTemplate;
    @Resource
    public RabbitSendServer mqServer;

    /**
     * @Description 调用外部接口
     * @Author taoye
     */
    @Override
    public String postExternalServer(String serverUrl, String param) {
        try {
            LOGGER.info("调用外部接口serverUrl={},param={}", serverUrl, param);
            return restTemplate.postForObject(serverUrl, param, String.class);
        } catch (Exception e) {
            LOGGER.error("调用外部接口serverUrl={},param={}出错:", serverUrl, param, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description 调用外部接口
     * @Author taoye
     */
    @Override
    public String getExternalServer(String serverUrl, Map<String, Object> map) {
        try {
            if (null != map) {
                StringBuilder sb = new StringBuilder();
                sb.append(serverUrl).append("?");
                map.forEach(
                        (k, v) -> sb.append(k).append("=").append(v).append("&")
                );
                sb.deleteCharAt(sb.length() - 1);
                serverUrl = sb.toString();
            }
            LOGGER.info("调用外部接口serverUrl={}", serverUrl);
            return restTemplate.getForObject(serverUrl, String.class);
        } catch (Exception e) {
            LOGGER.error("调用外部接口serverUrl={}出错:", serverUrl, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description 获取用户登录用户id
     * @Author taoye
     */
    @Override
    public String getLoginUserId() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("userId"));
    }

    /**
     * @Description 获取用户登录用户name
     * @Author taoye
     */
    @Override
    public String getLoginUserName() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("userName"));
    }

    /**
     * @Description 获取用户登录用户手机
     * @Author taoye
     */
    @Override
    public String getLoginUserMobile() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("mobile"));
    }

    /**
     * @Description 获取用户登录Ip
     * @Author taoye
     */
    @Override
    public String getLoginIpAddress() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("ipAddress"));
    }

    @Override
    public String getLoginShopId() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("shopId"));
    }
}
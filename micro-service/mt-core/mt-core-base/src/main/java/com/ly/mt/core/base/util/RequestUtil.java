package com.ly.mt.core.base.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Map;

/**
 * request操作工具类
 *
 * @author taoye
 */
public class RequestUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);

    /**
     * 从request中获取ip地址
     *
     * @author taoye
     */
    public static String getIpAddress(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtil.isNotEmpty(ip)) {
            String[] ips = ip.split(",");
            return ips[0];
        }
        return ip;
    }

    /**
     * 从request中获取参数封装jsonObject
     *
     * @author taoye
     */
    public static JSONObject getJSONObject(HttpServletRequest request) {
        String parameters = "";
        if ("GET".equals(request.getMethod())) {
            parameters = request.getQueryString();
        } else if ("POST".equals(request.getMethod())) {
            String totalParameter = "";
            Map<String, String[]> params = request.getParameterMap();
            int parametersNum = request.getParameterMap().size();
            int flag = 1;
            for (String key : params.keySet()) {
                String[] values = params.get(key);
                for (int i = 0; i < values.length; i++) {
                    String value = values[i];
                    totalParameter += key + "=" + value;
                }
                if (flag < parametersNum) {
                    totalParameter += "&";
                }
                flag += 1;
            }
            parameters = totalParameter;
        }
        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isNotEmpty(parameters)) {
            String[] parameterArr = parameters.split("&");
            for (int i = 0; i < parameterArr.length; i++) {
                String[] paramArr = parameterArr[i].split("=");
                String paramValue = "";
                if (paramArr.length > 1) {
                    String value = paramArr[1];
                    if (StringUtil.isNotEmpty(value)) {
                        try {
                            paramValue = URLDecoder.decode(value, "UTF-8").trim();
                        } catch (Exception e) {
                            LOGGER.error("request参数key={},value={}转json出错:", paramArr[0], value, e);
                        }
                    }
                }
                jsonObject.put(paramArr[0], paramValue);
            }
        }
        return jsonObject;
    }
}
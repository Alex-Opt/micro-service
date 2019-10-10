package com.ly.mt.core.rest.service;

import java.util.Map;

/**
 * 外部接口调用服务
 *
 * @author taoye
 */
public interface RestService {
    /**
     * 调用外部接口——post
     *
     * @param serverUrl 接口地址
     * @param param     请求参数
     * @return 响应参数
     * @author taoye
     */
    String restTemplatePost(String serverUrl, String param);

    /**
     * 调用外部接口——get
     *
     * @param serverUrl 接口地址
     * @param map       请求参数
     * @return 响应参数
     * @author taoye
     */
    String restTemplateGet(String serverUrl, Map<String, Object> map);
}
package com.ly.mt.center.third.base.service;

import java.util.Map;

public interface BaseService {
    /**
     * @Description 调用外部接口——post
     * @Author taoye
     */
    String restTemplatePost(String serverUrl, String param);

    /**
     * @Description 调用外部接口——get
     * @Author taoye
     */
    String restTemplateGet(String serverUrl, Map<String, Object> map);
}
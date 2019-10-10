package com.ly.mt.task.base.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.method.ThirdServerMethodEnum;

/**
 * @author zhanglifeng
 */
public interface BaseService {
    /**
     * @Description m-server调用mt-server服务
     * @Author taoye
     * @deprecated
     */
    JSONObject callFNService(ThirdServerMethodEnum serverEnum, JSONObject jsonObject);

    /**
     * 获取between查询sql
     *
     * @param start between起始值
     * @param end   between截止值
     * @return sql
     * @author taoye
     */
    String getBetweenSql(String start, String end);
}
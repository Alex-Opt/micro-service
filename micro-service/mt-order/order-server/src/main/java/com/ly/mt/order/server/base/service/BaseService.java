package com.ly.mt.order.server.base.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.method.TaskMethodEnum;
import com.ly.mt.core.base.method.ThirdServerMethodEnum;

/**
 * @author zhanglifeng
 */
public interface BaseService {
    /**
     * @Description m-server调用mt-task服务
     * @Author taoye
     */
    JSONObject callMtTask(TaskMethodEnum serverEnum, JSONObject jsonObject);

    /**
     * @Description m-server调用mt-server服务
     * @Author taoye
     */
    JSONObject callFNService(ThirdServerMethodEnum serverEnum, JSONObject jsonObject);


    /**
     * @Description 获取用户登录IP
     * @Author taoye
     */
    String getLoginUserId(String json);

    /**
     * @Description 获取登录用户店铺id
     * @Author taoye
     */
    String getLoginShopId(String json);

    /**
     * @Description 获取用户登录用户手机
     * @Author taoye
     */
    String getLoginUserMobile(String json);


}
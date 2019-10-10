package com.ly.mt.order.server.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.method.TaskMethodEnum;
import com.ly.mt.core.base.method.ThirdServerMethodEnum;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.order.server.base.feign.MtTaskClient;
import com.ly.mt.order.server.base.feign.MtThirdServiceClient;
import com.ly.mt.order.server.base.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_SYSTEM_ERROR;

/**
 * @author zhanglifeng
 */
@Service
public class BaseServiceImpl implements BaseService {

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
    @Resource
    public RedisService redisServer;

    @Resource
    public PushMessageServer pushMessageServer;

    @Autowired
    private MtTaskClient mtTaskClient;

    @Autowired
    private MtThirdServiceClient mtThirdServiceClient;

    /**
     * @Description m-server调用mt-task服务
     * @Author taoye
     */
    @Override
    public JSONObject callMtTask(TaskMethodEnum serverEnum, JSONObject jsonObject) {
        String serviceName = serverEnum.getServiceName();
        String functionName = serverEnum.getFunctionName();
        try {
            jsonObject.put("serviceName", serviceName);
            jsonObject.put("functionName", functionName);
            long start = System.currentTimeMillis();
            jsonObject = mtTaskClient.requestDistribute(jsonObject);
            long end = System.currentTimeMillis();
            LOGGER.info("调用mt-task:接口={},方法={},耗时={}", serviceName, functionName, String.valueOf(end - start));
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("调用mt-task:接口={}方法={},异常={}", serviceName, functionName, e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    /**
     * @Description m-server调用center-third服务
     * @Author taoye
     */
    @Override
    public JSONObject callFNService(ThirdServerMethodEnum serverEnum, JSONObject jsonObject) {
        String serviceName = serverEnum.getServiceName();
        String functionName = serverEnum.getFunctionName();
        try {
            JSONObject json = new JSONObject();
            json.put("serverName", serviceName);
            json.put("functionName", functionName);
            json.put("jsonObject", jsonObject);
            long start = System.currentTimeMillis();
            jsonObject = mtThirdServiceClient.requestDistribute(json);
            long end = System.currentTimeMillis();
            LOGGER.info("调用center-third:接口={},方法={},耗时={}", serviceName, functionName, String.valueOf(end - start));
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("调用center-third:接口={}方法={},异常={}", serviceName, functionName, e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    /**
     * @Description 获取登录用户ID
     * @Author taoye
     */
    @Override
    public String getLoginUserId(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject.getString("userId");
    }

    /**
     * @Description 获取登录用户店铺id
     * @Author taoye
     */
    @Override
    public String getLoginShopId(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject.getString("shopId");
    }

    /**
     * @Description 获取用户登录用户手机
     * @Author taoye
     */
    @Override
    public String getLoginUserMobile(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject.getString("mobile");
    }
}
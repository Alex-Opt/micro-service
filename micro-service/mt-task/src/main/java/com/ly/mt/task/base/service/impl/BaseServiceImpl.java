package com.ly.mt.task.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.aliyun.sms.service.SmsService;
import com.ly.mt.core.base.method.ThirdServerMethodEnum;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.core.rest.service.RestService;
import com.ly.mt.task.base.feign.MtThirdServiceClient;
import com.ly.mt.task.base.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * @author zhanglifeng
 */
@Service
public class BaseServiceImpl implements BaseService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
    @Resource
    public SmsService smsService;
    @Resource
    public RedisService redisService;
    @Resource
    public RestService restService;
    @Autowired
    private MtThirdServiceClient mtThirdServiceClient;

    /**
     * @Description mt-task调用third-server服务
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
            LOGGER.info("调用third-server:接口={},方法={},耗时={}", serviceName, functionName, String.valueOf(end - start));
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("调用third-server:接口={}方法={},异常={}ctionName, e");
            return JsonUtil.getErrorJson(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public String getBetweenSql(String start, String end) {
        if (StringUtil.isDate(start) && start.length() <= 10) {
            start += " 00:00:00";
        }
        if (StringUtil.isDate(end) && end.length() <= 10) {
            end += " 23:59:59";
        }
        if (StringUtil.isNotEmpty(start) && StringUtil.isNotEmpty(end)) {
            return " BETWEEN '" + start + "' AND '" + end + "'";
        } else if (StringUtil.isNotEmpty(start)) {
            return " >= '" + start + "'";
        } else if (StringUtil.isNotEmpty(end)) {
            return " <= '" + end + "'";
        } else {
            return null;
        }
    }
}
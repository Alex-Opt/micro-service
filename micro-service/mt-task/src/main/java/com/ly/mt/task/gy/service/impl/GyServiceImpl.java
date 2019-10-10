package com.ly.mt.task.gy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.MD5Util;
import com.ly.mt.task.base.config.MtTaskYml;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.gy.entity.GYResult;
import com.ly.mt.task.gy.service.GyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GyServiceImpl extends BaseServiceImpl implements GyService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GyServiceImpl.class);
    @Resource
    public MtTaskYml yml;

    /**
     * @Description 获取调用管易接口公共参数
     * @Author taoye
     */
    @Override
    public JSONObject getReqJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appkey", yml.getAppKey());
        jsonObject.put("sessionkey", yml.getSessionKey());
        return jsonObject;
    }

    /**
     * @Description 获取管易签名
     * @Author taoye
     */
    @Override
    public String getSign(String str) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(yml.getSecret());
            sb.append(str);
            sb.append(yml.getSecret());
            return MD5Util.md5(sb.toString());
        } catch (Exception e) {
            LOGGER.error("获取管易签名出错:", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description 调用管易接口
     * @Author taoye
     */
    @Override
    public String postGY(JSONObject jsonObject) {
        try {
            LOGGER.info("post gy server params:{}", jsonObject);
            String json = restService.restTemplatePost(yml.getServerUrl(), jsonObject.toJSONString());
            LOGGER.info("gy server response json:{}", json);
            GYResult result = JSONObject.parseObject(json, GYResult.class);
            if (!result.getSuccess()) {
                LOGGER.error(
                        "调用管易接口requestMethod={},返回错误:errorCode={},subErrorCode={},errorDesc={},subErrorDesc={}",
                        result.getRequestMethod(), result.getErrorCode(), result.getSubErrorCode(), result.getErrorDesc(), result.getSubErrorDesc()
                );
            }
            return json;
        } catch (Exception e) {
            LOGGER.error("调用管易接口出错:", e);
            return null;
        }
    }
}
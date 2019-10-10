package com.ly.mt.center.third.gy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.third.gy.constant.Kd100Com;
import com.ly.mt.center.third.gy.constant.MethodEnum;
import com.ly.mt.center.third.gy.entity.GYResult;
import com.ly.mt.center.third.gy.service.GyService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.MD5Util;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @description: 管易实现接口
 * @author: linan
 * @date: 2019/7/22
 */
@Service
public class GyServiceImpl extends BaseServiceImpl implements GyService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    YmlConfig yml;

    @Override
    public ResponseJson gyDeliverInfo(JSONObject jsonObject) {
        try {
            JSONObject gyJson = new JSONObject();
            gyJson.put("appkey", yml.getGyAppKey());
            gyJson.put("sessionkey", yml.getGySessionKey());
            gyJson.put("page_no", "1");
            gyJson.put("page_size", "5");
            gyJson.put("delivery", "1");
            gyJson.put("outer_code", jsonObject.getString("orderNo"));
            gyJson.put("method", MethodEnum.GY_ERP_TRADE_DELIVERYS_GET.getMethodName());
            gyJson.put("sign", getSign(gyJson.toJSONString()));

            logger.info("post gy params:{}",gyJson);
            String result = restTemplate.postForObject(yml.getGyServerUrl(), gyJson.toJSONString(), String.class);
            logger.info("gy response result:{}", result);
            JSONObject resultJson = JSONObject.parseObject(result);
            if (!resultJson.getBoolean("success")) {
                logger.error("调用管易接口返回错误:{}", result);
                throw new RuntimeException("调用管易接口出错:" + resultJson.getString("errorCode"));
            }

            List<JSONObject> deliverysList = JSONArray.parseArray(resultJson.getString("deliverys"), JSONObject.class);
            if(null != deliverysList){
                JSONObject deliverysJson = deliverysList.get(0);
                logger.info("formate deliverysJson:{}",deliverysJson);

                String expressNo = deliverysJson.getString("express_no");
                String expressCompanyCode = deliverysJson.getString("express_code");
                // formate gy com to kd100 com
                expressCompanyCode = Kd100Com.getCom(expressCompanyCode);

                JSONObject response = new JSONObject();
                response.put("express_no", expressNo);
                response.put("express_code", expressCompanyCode);
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, response);
            }
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            logger.error("调用管易接口出错:", e);
            throw new RuntimeException(e);
        }

    }

    private String getSign(String str) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(yml.getGySecret());
            sb.append(str);
            sb.append(yml.getGySecret());
            return MD5Util.md5(sb.toString());
        } catch (Exception e) {
            logger.error("获取管易签名出错:", e);
            throw new RuntimeException(e);
        }
    }
}

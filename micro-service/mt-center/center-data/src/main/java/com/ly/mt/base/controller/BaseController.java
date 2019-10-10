package com.ly.mt.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.util.SpringBeanUtil;
import com.ly.mt.core.base.entity.RequestJson;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@RestController
@RequestMapping("/center/data")
public class BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @PostMapping("/requestDistribute")
    public ResponseJson requestDistribute(@RequestBody RequestJson requestJson) {
        try {
            LOGGER.info("center-data:requestJson={}", requestJson.toString());
            Object obj = SpringBeanUtil.getBean(requestJson.getServerName());
            if (null == obj) {
                LOGGER.error("center-data:请求接口不存在");
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            JSONObject jsonObject = requestJson.getJsonObject();
            String xid = jsonObject.getString("xid");
            if (StringUtil.isNotEmpty(xid)) {
                LOGGER.info("事务xid={}", xid);
                TxcContext.bind(xid, null);
            }
            Method method = obj.getClass().getDeclaredMethod(requestJson.getFunctionName(), new Class[]{JSONObject.class});
            ResponseJson responseJson = (ResponseJson) method.invoke(obj, requestJson.getJsonObject());
            LOGGER.info("center-data:responseJson={}", responseJson.toString());
            if (StringUtil.isNotEmpty(xid)) {
                TxcContext.unbind();
            }
            return responseJson;
        } catch (Exception e) {
            LOGGER.error("center-data:请求执行异常=", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
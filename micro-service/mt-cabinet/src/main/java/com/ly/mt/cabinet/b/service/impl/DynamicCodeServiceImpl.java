package com.ly.mt.cabinet.b.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.cache.RedisServer;
import com.ly.mt.cabinet.b.common.dict.RedisEnum;
import com.ly.mt.cabinet.b.service.DynamicCodeService;
import com.ly.mt.cabinet.b.util.DateUtil;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.dict.SmsTemplateEnum;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.feign.ThirdCenterMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class DynamicCodeServiceImpl extends BaseServiceImpl implements DynamicCodeService {

    private static final Logger log = LoggerFactory.getLogger(DynamicCodeServiceImpl.class);

    @Resource
    private RedisServer redisServer;

    @Override
    public Resp sendDynamicCode(String phoneNo) {
        try {
            long start = System.currentTimeMillis();
            log.info("call mehtod sendDynamicCode of DynamicCodeServiceImpl at {},the param={}", DateUtil.currentDateTime(),phoneNo);
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("phoneNo",phoneNo);
            jsonParam.put("signName","MOTI");
            jsonParam.put("templateCode", SmsTemplateEnum.SMS_TEMPLATE_CODE_LOGIN.getTemplateCode());
            String dynamicCode = StringUtil.getRandomIntByLength(6);
            JSONObject templateParams = new JSONObject();
            templateParams.put("code",dynamicCode);
            jsonParam.put("templateParam",JSON.toJSONString(templateParams));
            String s = callThirdCenter(ThirdCenterMethod.AL_SMS_SEND, jsonParam);
            redisServer.putCacheByKeyTime(RedisEnum.LOGIN_CODE,phoneNo,dynamicCode,60, TimeUnit.SECONDS);
            //调用第三方发送短信验证码
            long end = System.currentTimeMillis();
            log.info("call mehtod sendDynamicCode of DynamicCodeServiceImpl cost {} mills",(end - start));
            return Resp.createBySuccess();
        }catch (Exception e){
            log.info("call mehtod sendDynamicCode of DynamicCodeServiceImpl has some errors,the errors message is {}",e);
            return Resp.createByError();
        }
    }
}

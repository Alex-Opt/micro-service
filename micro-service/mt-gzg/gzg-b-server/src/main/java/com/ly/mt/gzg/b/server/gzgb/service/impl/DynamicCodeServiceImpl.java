package com.ly.mt.gzg.b.server.gzgb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.SmsTemplateEnum;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.core.sms.SmsServer;
import com.ly.mt.gzg.b.server.gzgb.service.DynamicCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class DynamicCodeServiceImpl implements DynamicCodeService {

    @Autowired
    private RedisServer redisServer;

    @Autowired
    private SmsServer smsServer;
    @Override
    public JSONObject send(String json) {
        log.info("发送验证码参数:{}",json);
        long functionStart = System.currentTimeMillis();
        Map<String, Object> map = JsonUtil.json2Map(json);
        String phoneNo = (String) map.get("phoneNo");
        int dynamicType = (Integer) map.get("dynamicType");
        String dynamicCode = StringUtil.getRandomIntByLength(6);
        //String dynamicCodeCacheKey = Constant.USER_LOGIN_DYNAMIC_CODE_PRE+phoneNo;
        if (dynamicType == 0) {
            long smsStart = System.currentTimeMillis();
            smsServer.sendDynamicCodeSms(phoneNo,dynamicCode, SmsTemplateEnum.SMS_TEMPLATE_CODE_REGIST);
            long smsEnd = System.currentTimeMillis();
            log.info("发送短信耗时:{}",(smsEnd - smsStart));
            redisServer.set(SmsTemplateEnum.SMS_TEMPLATE_CODE_REGIST, phoneNo, dynamicCode);
        }else {
            long smsStart = System.currentTimeMillis();
            smsServer.sendDynamicCodeSms(phoneNo,dynamicCode, SmsTemplateEnum.SMS_TEMPLATE_CODE_LOGIN);
            long smsEnd = System.currentTimeMillis();
            log.info("发送短信耗时:{}",(smsEnd - smsStart));
            redisServer.set(SmsTemplateEnum.SMS_TEMPLATE_CODE_LOGIN, phoneNo, dynamicCode);
        }
        long functionEnd = System.currentTimeMillis();
        log.info("发送验证码耗时:{}",(functionEnd-functionStart));
        return JsonUtil.getSuccessJson();
    }
}

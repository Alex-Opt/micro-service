package com.ly.mt.user.server.code.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.SmsTemplateEnum;
import com.ly.mt.core.common.entity.code.DynamicCodeVo;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.user.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.user.server.code.service.DynamicCodeService;
import org.springframework.stereotype.Service;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_DYNAMIC_CODE_REPEAT_ERROR;
import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;

@Service
public class DynamicCodeServiceImpl extends BaseServiceImpl implements DynamicCodeService {
    /**
     * @Description 获取动态验证码发送到手机
     * @Author taoye
     */
    @Override
    public JSONObject getDynamicCode(String json) throws Exception {
        DynamicCodeVo code = JSONObject.parseObject(json, DynamicCodeVo.class);
        // 获取验证码类型枚举
        SmsTemplateEnum smsTemplateEnum = SmsTemplateEnum.getDynamicCodeEnumByCodeType(code.getDynamicCodeType());
        if (null == smsTemplateEnum) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        // redis缓存key
        String mobile = code.getMobile();
        String redisJson = redisServer.get(smsTemplateEnum, mobile);
        // 校验90秒内重复获取
        if (StringUtil.isNotEmpty(redisJson)) {
            return JsonUtil.getErrorJson(RESULT_CODE_DYNAMIC_CODE_REPEAT_ERROR);
        }
        // 生成并发送验证码
        String dynamicCode = StringUtil.getRandomIntByLength(6);
        smsServer.sendDynamicCodeSms(mobile, dynamicCode, smsTemplateEnum);
        // 放入redis
        redisServer.set(smsTemplateEnum, mobile, dynamicCode);
        redisServer.expire(smsTemplateEnum, mobile, 90);
        return JsonUtil.getSuccessJson();
    }
}
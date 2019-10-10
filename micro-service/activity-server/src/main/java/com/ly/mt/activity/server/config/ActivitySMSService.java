package com.ly.mt.activity.server.config;


import com.ly.mt.core.common.constant.SmsTemplateEnum;
import com.ly.mt.core.common.entity.hd.model.HdSmsMsg;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.core.sms.SmsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description 门店活动短信操作service
 *
 * @author panjingtian
 * @date 2019/6/11 6:03 PM
 */
@Service
public class ActivitySMSService {

    @Autowired
    SmsServer smsServer;
    @Autowired
    RedisServer redisServer;

    /**
     * 发送验证 成功返回true，验证码未超时则返回false
     *
     * @param phone       手机号
     * @param sms         {@link SmsTemplateEnum} 短信模版
     * @param dynamicCode 短信验证码
     * @param seconds     验证码过期时间 0为不加过期时间 负数直接抛非法参数
     * @return
     */
    public HdSmsMsg sendDynamicCode(String phone, SmsTemplateEnum sms, String dynamicCode, Long seconds) {
        String dynamicCodeType = sms.getDynamicCodeType();
        if (StringUtil.isEmpty(dynamicCodeType)) {
            throw  new IllegalArgumentException("非法模版参数");
        }
        String redisKey =  redisServer.getVauel(sms.getRedisKey()+phone);
        //验证码未过期情况
        if (!StringUtil.isEmpty(redisKey)) {
            return new HdSmsMsg(false,dynamicCode);
        }
        //发送验证码
        smsServer.sendDynamicCodeSms(phone, dynamicCode, sms);
        //redis缓存验证码，60秒失效
        redisServer.setEx(sms,phone,dynamicCode,seconds);
        return new HdSmsMsg(true,dynamicCode);
    }

    /**
     * 发货取货码
     * @param phone
     * @param sms
     * @param code
     * @return
     */
    public HdSmsMsg sendGetProductCode(String phone, SmsTemplateEnum sms, String code){
        try {
            String dynamicCodeType = sms.getDynamicCodeType();
            if (StringUtil.isEmpty(dynamicCodeType)) {
                throw  new IllegalArgumentException("非法模版参数");
            }
            //发送验证码
            smsServer.sendDynamicCodeSms(phone, code, sms);
            return new HdSmsMsg(true,code);
        } catch (IllegalArgumentException e) {
            return new HdSmsMsg(false,null);
        }
    }


    /**
     * 校验短信验证码是否正确
     * @param key
     * @param targetDynamicCode 用户输入的短信验证码
     * @return
     */
    public HdSmsMsg verifyDynamicCode(String key,String targetDynamicCode) {
        String result = redisServer.getVauel(key);
        if (StringUtil.isEmpty(result) && !targetDynamicCode.equals(result)){
            return new  HdSmsMsg(false,targetDynamicCode);
        }
        return new  HdSmsMsg(true,targetDynamicCode);
    }

}

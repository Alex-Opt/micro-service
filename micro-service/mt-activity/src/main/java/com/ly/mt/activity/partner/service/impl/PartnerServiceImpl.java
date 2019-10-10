package com.ly.mt.activity.partner.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.base.service.impl.BaseServiceImpl;
import com.ly.mt.activity.partner.service.PartnerService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.ly.mt.core.base.dict.PrimaryKey.HD_PARTNER;
import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.feign.DataCenterMethod.HD_PARTNER_ADD;
import static com.ly.mt.core.feign.DataCenterMethod.HD_PARTNER_QEURY_BY_MOBILE;

/**
 * @author zhanglifeng
 * @description 合伙人业务实现处理层
 */
@Service
public class PartnerServiceImpl extends BaseServiceImpl implements PartnerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PartnerServiceImpl.class);

    @Override
    public ResponseJson addPartnerInfo(String partnerName, String partnerMobile, String agentCityName, String source) {
        try {
            JSONObject jsonObject = new JSONObject();
            String id = SnowflakeUtil.getPrimaryKey(HD_PARTNER);
            jsonObject.put("partner_mobile", partnerMobile);
            String flag = callDataCenter(HD_PARTNER_QEURY_BY_MOBILE, jsonObject);
            LOGGER.info("-----------------------------------根据手机号查询的结果：" + flag);
            if ("true".equals(flag)) {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_REPEAT_SUBMIT);
            }
            jsonObject.put("id", id);
            jsonObject.put("partner_name", partnerName);
            jsonObject.put("agent_city_name", agentCityName);
            jsonObject.put("source", source);
            jsonObject.put("status", "1");
            jsonObject.put("create_time", DateUtil.getNowTimeStr());
            callDataCenter(HD_PARTNER_ADD, jsonObject);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.info("执行保存出现异常：" + e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }

    }
}

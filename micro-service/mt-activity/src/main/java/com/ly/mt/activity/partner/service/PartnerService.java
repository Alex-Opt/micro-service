package com.ly.mt.activity.partner.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @author zhanglifeng
 * @description 合伙人业务接口处理层
 * @date 2019-08-21
 */
public interface PartnerService {
    /**
     * 根据合伙人姓名，手机号，代理城市，数据来源保存合伙人信息
     *
     * @param partnerName
     * @param partnerMobile
     * @param agentCityName
     * @param source
     * @return
     */
    ResponseJson addPartnerInfo(String partnerName, String partnerMobile, String agentCityName, String source);


}

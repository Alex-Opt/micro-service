package com.ly.mt.center.data.hd.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @author zhanglifeng
 * @date 2019-08-21
 */
public interface HdPartnerService {
    /**
     * 新增一个MOTI合伙人
     *
     * @param jsonObject
     * @return
     */
    ResponseJson addPartner(JSONObject jsonObject);

    /**
     * 根据手机号查询合伙人是否已经添加过的接口
     *
     * @param jsonObject
     * @return
     */
    ResponseJson queryPartnerByMobile(JSONObject jsonObject);

}

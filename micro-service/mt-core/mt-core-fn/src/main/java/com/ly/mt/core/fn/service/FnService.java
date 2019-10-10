package com.ly.mt.core.fn.service;

import com.ly.mt.core.fn.entity.FnCarrierInfo;

/**
 * 蜂鸟服务接口
 *
 * @author taoye
 */
public interface FnService {
    /**
     * 查询骑手信息
     *
     * @param partnerOrderCode 订单号
     * @return 骑手信息
     * @author taoye
     */
    FnCarrierInfo carrierQuery(String partnerOrderCode) throws Exception;
}
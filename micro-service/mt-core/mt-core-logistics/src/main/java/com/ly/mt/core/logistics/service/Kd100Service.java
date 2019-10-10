package com.ly.mt.core.logistics.service;

import com.ly.mt.core.logistics.entity.LogisticsInfo;

/**
 * 快递100服务接口
 *
 * @author taoye
 */
public interface Kd100Service {
    /**
     * 查询物流信息
     *
     * @param code   物流公司编码
     * @param no   快递单号
     * @param mobile 手机号
     * @throws Exception
     */
    LogisticsInfo getLogisticsInfo(String code, String no, String mobile) throws Exception;
}
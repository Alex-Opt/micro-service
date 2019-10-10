package com.ly.mt.core.wechat.service;

import java.util.Map;

/**
 * 微信红包接口
 *
 * @author taoye
 */
public interface RedService {
    /**
     * 发放现金红包
     *
     * @param mchBillno   订单号
     * @param reOpenid    接收红包用户的openid
     * @param totalAmount 红包金额,单位分
     * @param totalNum    红包发放个数
     * @param wishing     红包祝福语
     * @param actName     活动名称
     * @param remark      备注
     * @param sceneId     场景id
     * @throws Exception 异常
     * @author taoye
     */
    void sendRed(String mchBillno, String reOpenid, int totalAmount, int totalNum, String wishing, String actName, String remark, String sceneId) throws Exception;

    /**
     * 查询红包发放结果
     *
     * @param mchBillno 订单号
     * @return 红包信息
     * @throws Exception 异常
     */
    Map<String, Object> getRedStatus(String mchBillno) throws Exception;
}
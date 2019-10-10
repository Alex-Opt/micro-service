package com.ly.mt.cabinet.c.alipay.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.c.order.entity.GzgOrderItem;
import com.ly.mt.cabinet.c.order.entity.GzgOrder;
import com.ly.mt.cabinet.c.enumEntity.GzgPayTypeEnum;
import com.ly.mt.core.base.entity.ResponseJson;

import java.util.List;

/**
 * alipay
 * @author evan
 * @date 2019-06-04
 */
public interface GzgPayCommonService {
    /**
     * 判断超时
     * @param gzgOrderItemVos
     * @return
     */
    public Boolean isTimeOut(List<GzgOrderItem> gzgOrderItemVos) throws Exception ;

    /**
     * 打开柜子失败后的数据的更新
     * @param
     */
    public void afterFailOpenDevice(GzgOrder gzgOrderVo1, List<GzgOrderItem> gzgOrderItemVos, String transaction_id, GzgPayTypeEnum gzgPayTypeEnum)throws Exception ;

    /**
     * 打开柜子成功后的数据的更新
     * @param
     * @return
     */
    public void afterSuccessOpenDevice(GzgOrder gzgOrderVo1, List<GzgOrderItem> gzgOrderItemVos, String transaction_id, GzgPayTypeEnum gzgPayTypeEnum) throws Exception;
    /**
     *支付失败更新数据
     */
    public void afterPayFail(GzgOrder gzgOrder, List<GzgOrderItem> gzgOrderItemList, String transaction_id, GzgPayTypeEnum gzgPayTypeEnum) throws Exception;

    public ResponseJson queryGzgOrderPayInfoByGzgOrderId(String gzgOrderId) throws Exception;


    public void updateGzgOrder(GzgOrder gzgOrder,List<GzgOrderItem> gzgOrderItemList) throws Exception;


    /**
     * 更新新人优惠卷
     * @param gzgOrder
     */
    void updateGzgUserCoupon(GzgOrder gzgOrder);
}

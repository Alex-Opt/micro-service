package com.ly.mt.cabinet.c.Device.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.c.order.entity.GzgOrderItem;
import com.ly.mt.cabinet.c.order.entity.GzgOrder;
import com.ly.mt.cabinet.c.enumEntity.GzgPayTypeEnum;

import java.util.List;

//import com.ly.mt.cabinet.c.order.entity.GzgOrderVo;

/**
 * 格子柜交互接口
 *
 * @author gongjy
 * @date 2019-05-31
 */
public interface GzgExchangeService {

    /**
     * 登录亿联API获取Token
     *
     * @return
     * @throws Exception
     */

    public JSONObject getToken(String json) throws Exception;

    /**
     * 获取亿联设备是否可用
     *
     * @param json
     * @return
     * @throws Exception
     */
    public JSONObject getIsAvailable(String json) throws Exception;

    /**
     * 支付完成打开格子柜
     *
     * @return
     */
    public Boolean openDevice(GzgOrder gzgOrderVo, List<GzgOrderItem> gzgOrderItemVos, GzgPayTypeEnum gzgPayTypeEnum);


    /**
     * 通过code值查询柜子是否可用
     *
     * @param imei 格子柜code值
     * @return
     */
    public Boolean getIsAvailableBycode(String imei) throws Exception;


    /**
     * 库管维护时打开柜门,仅限库管补货使用
     *
     * @param imei 格子柜code值
     * @return
     */
    Boolean maintainOpenDevice(String imei) throws Exception;


}

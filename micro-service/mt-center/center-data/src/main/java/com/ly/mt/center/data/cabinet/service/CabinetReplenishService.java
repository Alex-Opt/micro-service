package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 补货数据中心层
 * @author zhanglifeng
 * @date 2019-08-26
 */
public interface CabinetReplenishService {
    /**
     * 插入一条补货单
     * @param jsonObject
     * @return
     */
    ResponseJson insertCabinetReplenish(JSONObject jsonObject);

    /**
     * 查询补货列表
     * @param
     * @return
     */
    ResponseJson getCabinetReplenishList(JSONObject jsonObject);

    /**
     * 通过订单id查询补货订单详情
     * @param jsonObject
     * @return
     */
    ResponseJson getCabinetReplenishById(JSONObject jsonObject);

    /**
     * 通过skuid查询spu+sku名称
     * @param
     * @return
     */
    ResponseJson getGoodsInfoNameBySkuId(JSONObject jsonObject);

    /**
     * 通过补货订单id更新
     * @param
     * @return
     */
    ResponseJson updateCabinetReplenishById(JSONObject jsonObject);
}

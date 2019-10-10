package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgOrderPayService {
    /**
     * @Description 保存GzgOrderPay
     * @Author taoye
     */
    ResponseJson insertGzgOrderPay(JSONObject jsonObject);

    /**
     * @Description 删除GzgOrderPay
     * @Author taoye
     */
    ResponseJson deleteGzgOrderPay(JSONObject jsonObject);

    /**
     * @Description 更新GzgOrderPay
     * @Author taoye
     */
    ResponseJson updateGzgOrderPay(JSONObject jsonObject);

    /**
     * @Description 查询GzgOrderPay
     * @Author taoye
     */
    ResponseJson getGzgOrderPay(JSONObject jsonObject);
}
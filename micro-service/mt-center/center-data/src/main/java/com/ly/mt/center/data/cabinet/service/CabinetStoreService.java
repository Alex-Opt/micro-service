package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.entity.CabinetStore;
import com.ly.mt.core.base.entity.ResponseJson;

public interface CabinetStoreService {
    /**
     * 插入
     * @param cabinetStore
     * @return
     */
    ResponseJson insert(CabinetStore cabinetStore);

    /**
     * 库管通过user_id查询自己所负责区域格子柜的相关信息
     * @param jsonObject
     * @return
     */
    ResponseJson getCabinetStoreByUserId(JSONObject jsonObject);

     ResponseJson updateCreateStatusById(JSONObject jsonObject) ;

    ResponseJson update(CabinetStore cabinetStore);


    /**
     * bd下面的全部店铺
     * @param jsonObject
     * @return
     */
    ResponseJson findBdShops(JSONObject jsonObject);


    /**
     * bd数据统计汇总详情信息
     * @param jsonObject
     * @return
     */
    ResponseJson findBdDataDetail(JSONObject jsonObject);

    /**
     * bd店铺的数据统计详情集合
     * @param jsonObject
     * @return
     */
    ResponseJson findBdDataStatistics(JSONObject jsonObject);

    /**
     * bd指定店铺的数据统计
     * @param jsonObject
     * @return
     */
    ResponseJson findBdStoreOrders(JSONObject jsonObject);

    /**
     * 根据id查询店铺信息
     * @param jsonObject
     * whl
     * @return
     */
    ResponseJson getCabinetStoreById(JSONObject jsonObject);
}

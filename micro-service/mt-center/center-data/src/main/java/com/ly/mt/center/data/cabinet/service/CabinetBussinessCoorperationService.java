package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.entity.CabinetBussinessCoorperation;
import com.ly.mt.core.base.entity.ResponseJson;

public interface CabinetBussinessCoorperationService {
    /**
     * 创建合作
     * @param data
     * @return
     */
    //ResponseJson createBussinessCoorperation(JSONObject data);

    /**
     * 格子柜保存
     * @param data
     * @return
     */
    ResponseJson cabCoorperationSave(JSONObject data);

    /**
     * 格子柜创建
     * @param data
     * @return
     */
    ResponseJson cabCoorperationCreate(JSONObject data);

    /**
     * 展柜保存
     * @param data
     * @return
     */
    ResponseJson caseCoorperationSave(JSONObject data);

    /**
     * 展柜保存
     * @param data
     * @return
     */
    ResponseJson caseCoorperationCreate(JSONObject data);


    /**
     * 合作信息
     * @param data
     * @return
     */
    ResponseJson coorperationInfo(JSONObject data);

    /**
     * 待创建
     * @param data
     * @return
     */
    ResponseJson coorperationTreatCreate(JSONObject data);

    /**
     * 已创建
     * @param data
     * @return
     */
    ResponseJson coorperationCreated(JSONObject data);

    /**
     * 已使用
     * @param data
     * @return
     */
    ResponseJson coorperationAlreadyUsed(JSONObject data);

    /**
     * 全部
     * @param data
     * @return
     */
    ResponseJson coorperationAll(JSONObject data);

    /**
     * 搜索
     * @param data
     * @return
     */
    ResponseJson search(JSONObject data);

    /**
     * 通过店铺id查询商务合作信息
     * @param data
     * @return
     */
    ResponseJson coorperationGetByStroreId(JSONObject data);

    /**
     * 店铺地址
     * @param data
     * @return
     */
    ResponseJson basicArea(JSONObject data);

    /**
     * 根据店铺id和status查询
     * @param data
     * @return
     */
    ResponseJson selectByStoreIdAndStatus(JSONObject data);

    /**
     * 
     * @param data
     * @return
     */
    ResponseJson findBdName(JSONObject data);

}

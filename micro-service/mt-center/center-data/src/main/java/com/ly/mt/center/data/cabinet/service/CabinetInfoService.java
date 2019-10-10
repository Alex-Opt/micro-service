package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.requestdto.SquareCabinatRequestDto;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @description
 * 格子柜表操作
 * @author panjingtian
 * @date 2019/8/28 6:04 PM
 */
public interface CabinetInfoService {

    /**
     * 分页查询格子柜信息
     * @param jsonObject {@link SquareCabinatRequestDto}
     * @return
     */
    ResponseJson cabinatsByUser(JSONObject jsonObject);

    /**
     * @Description 保存 CabinetInfo
     * @Author taoye
     */
    ResponseJson insertCabinetInfo(JSONObject jsonObject);

    /**
     * @Description 删除 CabinetInfo
     * @Author taoye
     */
    ResponseJson deleteCabinetInfo(JSONObject jsonObject);

    /**
     * @Description 更新 CabinetInfo
     * @Author taoye
     */
    ResponseJson updateCabinetInfo(JSONObject jsonObject);

    /**
     * @Description 查询 CabinetInfo
     * @Author taoye
     */
    ResponseJson getCabinetInfo(JSONObject jsonObject);

    /**
     * @Description 查询 CabinetInfo
     * @Author taoye
     */
    ResponseJson getCabinetInfoByImei(JSONObject jsonObject);


    /**
     * @Description 查询 CabinetInfo
     * @Author taoye
     */
    ResponseJson getCabinetInfoByStoreId(JSONObject jsonObject);


    /**
     * 查询所有的配货方案
     * @param jsonObject
     * @return
     */
     ResponseJson getCabinetProgrammeList(JSONObject jsonObject) ;

    /**
     * 制定bd格子柜的合作信息详情
     * @param  imei String 格子柜唯一编码
     * @param userId string
     * @return
     */
    ResponseJson cabinatsCoopMessage(JSONObject jsonObject);


    /**
     * 格子柜下架
     * @param jsonObject
     * @return
     */
    ResponseJson downSquareCabinet(JSONObject jsonObject);

    /**
     * bd展柜信息列表
     * @param jsonObject
     * @return
     */
    ResponseJson showCabinatsByUser(JSONObject jsonObject);


    /**
     * bd展柜合作信息
     * @param jsonObject
     * @return
     */
    ResponseJson showcaseCabinatsCoopMsg(JSONObject jsonObject);

    /**
     * 下架单个展柜
     * @param jsonObject
     * @return
     */
    ResponseJson downShowcaseCabinet(JSONObject jsonObject);

    /**
     * 上架单个展柜
     * @param jsonObject
     * @return
     */
    ResponseJson upShowcaseCabinet(JSONObject jsonObject);



}

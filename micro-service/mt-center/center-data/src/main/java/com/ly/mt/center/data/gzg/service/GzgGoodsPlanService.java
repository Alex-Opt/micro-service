package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.entity.GzgGoodsPlan;
import com.ly.mt.core.base.entity.ResponseJson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GzgGoodsPlanService {


    /**
     * @Description 根据条件查询GzgPlan
     * @Author taoye
     */
    ResponseJson getGzgGoodsPlan(JSONObject jsonObject);

    /**
     * 插入一条配货方案
     * @param jsonObject
     * @return
     */
    ResponseJson insertGzgGoodsPlan(JSONObject jsonObject);

    /**
     * 更新格子的库存和销量
     * @param jsonObject
     * @return
     */
    ResponseJson updateGzgGoodsPlan(JSONObject jsonObject);

    /**
     * 更新展柜的库存和销量
     * @param jsonObject
     * @return
     */
    ResponseJson updateGzgZgGoodsPlanNum(JSONObject jsonObject);

    /**
     *绑定格子柜时，批量添加相关信息志gzg_goods_plan
     */
    ResponseJson insertBatchGzgGoodsPlan(JSONObject jsonObject);


    /**
     *绑定格子柜时，批量更新相关信息志gzg_goods_plan
     */
    ResponseJson updateBatchGzgGoodsPlan(JSONObject jsonObject);

    /**
     *  展柜补货 在原库存基础上加上本次添加的库存
     * @return
     */
    ResponseJson updateGzgGoodsPlanStock(JSONObject jsonObject);

    /**
     *  BD补货 原先计划没有的情况下批量插入
     * @return
     */
    ResponseJson insertBatchGzgGoodsPlans(JSONObject jsonObject);

    /**
     *  查询货柜中是否包含套装商品
     * @return
     */
    ResponseJson getGzgGoodsPlanSuitNum(JSONObject jsonObject);

    /**
     * 查询展柜top3是否都已经卖完
     * @param jsonObject
     * @return
     */
    ResponseJson getTop3GzgGoodsPlan(JSONObject jsonObject);


    /**
     * 更新库存如金小圆桶和伊莲，展柜
     * @param jsonObject
     * @return
     */
    ResponseJson updateGzgGoodsPlanNumByCondation(JSONObject jsonObject);
}
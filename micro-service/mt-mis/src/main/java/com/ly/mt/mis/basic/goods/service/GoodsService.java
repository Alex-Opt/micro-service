package com.ly.mt.mis.basic.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 商品管理
 *
 * @author taoye
 */
public interface GoodsService {
    /**
     * SKU图片加载
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadSkuPicture(JSONObject jsonObject) throws Exception;

    /**
     * 商品信息分页表格
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadGoodsDatagrid(JSONObject jsonObject) throws Exception;
}

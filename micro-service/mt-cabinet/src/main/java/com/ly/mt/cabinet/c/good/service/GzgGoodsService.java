package com.ly.mt.cabinet.c.good.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 格子柜商品service
 * @author evan
 * @date 2019-05-29 16:30
 * create by intellij ideaa 2019
 */
public interface GzgGoodsService {
    /**
     * 格子柜商品列表
     * @param gzgCode
     * @return
     */
    ResponseJson gzgGoodsList(String gzgCode,String userId,String tname) throws Exception;

    /**
     * gzg落地页轮播商品详情
     * @param skuid
     * @return
     */
    ResponseJson gzgGoodsDetail(String skuid);
}

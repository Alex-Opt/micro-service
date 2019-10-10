package com.ly.mt.mis.basic.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 商品类目管理
 *
 * @author taoye
 */
public interface GoodsCategroyService {
    /**
     * 商品类目下拉树
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadGoodsCategroyCombotree(JSONObject jsonObject) throws Exception;
}

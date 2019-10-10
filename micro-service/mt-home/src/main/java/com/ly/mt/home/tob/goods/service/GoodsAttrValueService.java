package com.ly.mt.home.tob.goods.service;


import com.ly.mt.home.tob.goods.vo.GoodsAttrValueVo;

import java.util.List;

/**
 * 商品attr业务层
 *
 * @author: linan
 * @date: 2019/9/19
 **/
public interface GoodsAttrValueService {

    /**
     * 查询商品规格
     *
     * @param spuId
     * @return
     */
    List<GoodsAttrValueVo> getGoodsSpuAttrValueBySpuId(String spuId);

}

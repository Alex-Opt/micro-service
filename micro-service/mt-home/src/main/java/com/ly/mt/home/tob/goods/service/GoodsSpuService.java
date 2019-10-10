package com.ly.mt.home.tob.goods.service;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.home.tob.goods.vo.GoodsSpuVo;

/**
 * 商品spu业务层
 *
 * @author: linan
 * @date: 2019/9/19
 **/
public interface GoodsSpuService {


    /**
     * 查询商品spu数据
     *
     * @param id
     * @return
     */
    GoodsSpuVo queryGoodsSpuInfoById(String id);

    /**
     * 查询商品spu数据
     *
     * @param id
     * @return
     */
    ResponseJson queryGoodsSpuById(String id);

    /**
     * 根据类目查询商品spu数据
     *
     * @param
     * @return
     */
    ResponseJson queryGoodsSpuByCategroy(String cid, int page, int rows, String sort);

    /**
     * 根据雾化单类目查询商品spu数据
     *
     * @param
     * @return
     */
    ResponseJson queryGoodsSpuByAerosol(int page, int rows);

    /**
     * 查询商品类目数据
     *
     * @param parentId
     * @return
     */
    ResponseJson queryCategroyList(String parentId);

    /**
     * 查询top
     *
     * @param cId
     * @return
     */
    ResponseJson getListTop5ByCid(String cId);

}

package com.ly.mt.cabinet.b.replenish.service;

import com.ly.mt.cabinet.b.replenish.vo.GoodsAddZgVo;
import com.ly.mt.cabinet.c.order.entity.GzgOrder;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 展柜补货流程
 */
public interface ReplenishmentShowCaseService {
    /**
     * 该接口完成的操作有
     * 1.生成展柜的补货订单,以供展柜所在的店员，店主，或者BD进行补货操作。(目前这期只有BD补货)
     * 2.更新gzg_goods_plan表stock库存字段，若stock字段为0，前端页面这个地方会是灰色，用户无法购买
     * 3.当满足三个条件中一种 则生成对应的展柜补货订单
     * 1、TOP3商品售罄（运营给）
     * 2、套装售罄
     * 3、该展柜库存剩余小于7
     * 4.如果有押金，推送消息给给该柜所在门店店员发送补货push，否则push给BD
     *
     * @param gzgOrder
     * @return
     */
    ResponseJson createReplenishZgOrder(GzgOrder gzgOrder);

    /**
     * 获取展柜待配货列表
     * @return
     */
    ResponseJson getReplenishZgOrderList();

    /**
     * 获取Spu列表
     * @return
     */
    ResponseJson getSpuInfoList();

    /**
     * 获取sku列表
     * @return
     */
    ResponseJson getSkuInfoList(String spuId, String cabinetCode);

    /**
     * 添加商品库存
     * @return
     */
    ResponseJson addGoodsStock(GoodsAddZgVo goodsAddZgVo);

}

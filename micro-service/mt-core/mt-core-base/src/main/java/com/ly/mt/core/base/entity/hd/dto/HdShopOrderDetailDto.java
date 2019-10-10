package com.ly.mt.core.base.entity.hd.dto;

import com.ly.mt.core.base.entity.hd.HdShopAttOrderDetail;
/**
 * @description
 *
 * 订单详情dto
 *
 * @author panjingtian
 * @date 2019/6/15 12:51 PM
 */
/** @deprecated */
public class HdShopOrderDetailDto extends HdShopAttOrderDetail {

    private HdgoodsSkuDto skuDto;

    public HdgoodsSkuDto getSkuDto() {
        return skuDto;
    }

    public void setSkuDto(HdgoodsSkuDto skuDto) {
        this.skuDto = skuDto;
    }
}

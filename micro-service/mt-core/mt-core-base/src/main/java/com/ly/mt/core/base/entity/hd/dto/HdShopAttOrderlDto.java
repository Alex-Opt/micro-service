package com.ly.mt.core.base.entity.hd.dto;

import com.ly.mt.core.base.entity.hd.HdGoodsSkuInfo;
import com.ly.mt.core.base.entity.hd.HdShopAttOrderDetail;

import java.util.List;
/**
 * @description
 *
 * 活动订单详情dto
 *
 * @author panjingtian
 * @date 2019/6/14 11:14 AM
 */
/** @deprecated */
public class HdShopAttOrderlDto extends HdShopAttOrderDetail {

    /**
     * 活动用户
     */
    private HdActivityUserDto hdActivityUserDto;


    /**
     * 订单表详情
     */
    private List<HdGoodsSkuInfo> skuInfos;

    /**
     * 活动买家个人信息
     */
    private HdActivityUserDto activityUserDto;


    public HdActivityUserDto getHdActivityUserDto() {
        return hdActivityUserDto;
    }

    public void setHdActivityUserDto(HdActivityUserDto hdActivityUserDto) {
        this.hdActivityUserDto = hdActivityUserDto;
    }

    public List<HdGoodsSkuInfo> getSkuInfos() {
        return skuInfos;
    }

    public void setSkuInfos(List<HdGoodsSkuInfo> skuInfos) {
        this.skuInfos = skuInfos;
    }

    public HdActivityUserDto getActivityUserDto() {
        return activityUserDto;
    }

    public void setActivityUserDto(HdActivityUserDto activityUserDto) {
        this.activityUserDto = activityUserDto;
    }
}

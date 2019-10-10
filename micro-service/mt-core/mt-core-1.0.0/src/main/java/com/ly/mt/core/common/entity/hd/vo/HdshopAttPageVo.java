package com.ly.mt.core.common.entity.hd.vo;


import java.util.List;

/**
 * @author panjingtian
 * @description 门店活动买家进入店铺首页的vo
 * @date 2019/6/14 2:46 PM
 */
public class HdshopAttPageVo {


    /**
     * 活动门店注册表id
     * hd_shop_att_detail表
     */
    private Long HdshopAttDetailId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动简介
     */
    public String activityMsg;

    /**
     * 门店地址
     */
    private String shopAddress;

    /**
     * 商品
     */
    private List<HdGoodsSpuInfoVo> goods;

    public Long getHdshopAttDetailId() {
        return HdshopAttDetailId;
    }

    public void setHdshopAttDetailId(Long hdshopAttDetailId) {
        HdshopAttDetailId = hdshopAttDetailId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityMsg() {
        return activityMsg;
    }

    public void setActivityMsg(String activityMsg) {
        this.activityMsg = activityMsg;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public List<HdGoodsSpuInfoVo> getGoods() {
        return goods;
    }

    public void setGoods(List<HdGoodsSpuInfoVo> goods) {
        this.goods = goods;
    }

    public HdshopAttPageVo() {
    }

    public HdshopAttPageVo(Long hdshopAttDetailId, String activityName, String activityMsg, String shopAddress, List<HdGoodsSpuInfoVo> goods) {
        HdshopAttDetailId = hdshopAttDetailId;
        this.activityName = activityName;
        this.activityMsg = activityMsg;
        this.shopAddress = shopAddress;
        this.goods = goods;
    }
}

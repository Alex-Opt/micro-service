package com.ly.mt.core.base.entity.hd.model;


/**
 * @description
 * 活动商品sku模型
 * @author panjingtian
 * @date 2019/6/18 11:09 AM
 *//** @deprecated */
public class HdGoodsSkuModel {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;


    /**
     * 商品spuid
     */
    private Long spuId;

    /**
     * 1正常使用 2停用
     */
    private Integer skuStatus;


    /**
     * 零售价
     */
    private Double marketPrice;

    /**
     * 购买数量
     */
    private Integer buyNum;



    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public HdGoodsSkuModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Integer getSkuStatus() {
        return skuStatus;
    }

    public void setSkuStatus(Integer skuStatus) {
        this.skuStatus = skuStatus;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }
}

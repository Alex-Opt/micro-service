package com.ly.mt.core.common.entity.hd.model;


/**
 * @description
 * 活动商品sku模型
 * @author panjingtian
 * @date 2019/6/18 11:09 AM
 */
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

    /**
     * 前端展示商品id
     */
    private Long frontId;

    public Long getFrontId() {
        return frontId;
    }

    public void setFrontId(Long frontId) {
        this.frontId = frontId;
    }

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

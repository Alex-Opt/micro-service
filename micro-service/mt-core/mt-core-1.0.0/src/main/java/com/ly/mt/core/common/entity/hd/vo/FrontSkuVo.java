package com.ly.mt.core.common.entity.hd.vo;

import java.util.ArrayList;
import java.util.List;

public class FrontSkuVo {


    /**
     * 前端显示的id
     */
    private Long frontId;

    /**
     * sku id
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

    public Long getFrontId() {
        return frontId;
    }

    public void setFrontId(Long frontId) {
        this.frontId = frontId;
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

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public static FrontSkuBody convert(FrontSkuVo vo){
        FrontSkuBody b = new FrontSkuBody();
        b.setId(String.valueOf(vo.getId()));
        b.setBuyNum(vo.getBuyNum());
        b.setFrontId(String.valueOf(vo.getFrontId()));
        b.setMarketPrice(vo.getMarketPrice());
        b.setName(vo.getName());
        b.setSkuStatus(vo.getSkuStatus());
        if (vo.getSpuId()!= null || vo.getSpuId()<0){
            b.setSpuId(String.valueOf(vo.getSpuId()));
        }
        return b;
    }

    public static List<FrontSkuBody> converts(List<FrontSkuVo> source){
        if (source!= null && source.size()>0){
            List<FrontSkuBody> target = new ArrayList<>(source.size());
            source.forEach(s->{
                target.add(convert(s));
            });
            return target;
        }
        return null;
    }

}


package com.ly.mt.core.common.entity.hd.vo;

import java.util.ArrayList;
import java.util.List;

public class FrontSkuBody {


    /**
     * 前端显示的id
     */
    private String frontId;

    /**
     * sku id
     */
    private String id;

    /**
     * 名称
     */
    private String name;


    /**
     * 商品spuid
     */
    private String spuId;

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

    public String getFrontId() {
        return frontId;
    }

    public void setFrontId(String frontId) {
        this.frontId = frontId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
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


    public static FrontSkuVo convert(FrontSkuBody body){
        FrontSkuVo vo = new FrontSkuVo();
        vo.setId(Long.valueOf(body.getId()));
        vo.setBuyNum(body.getBuyNum());
        vo.setFrontId(Long.valueOf(body.getFrontId()));
        vo.setMarketPrice(body.getMarketPrice());
        vo.setName(body.getName());
        vo.setSkuStatus(body.skuStatus);
        vo.setSpuId(Long.valueOf(body.getSpuId()));
        return vo;
    }

    public static List<FrontSkuVo> converts(List<FrontSkuBody> source){
        List<FrontSkuVo> target = new ArrayList<>(source.size());
        source.forEach(s->{
            target.add(convert(s));
        });
        return target;
    }

}

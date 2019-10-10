package com.ly.mt.core.common.entity.hd.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 兼容v1版本中前端的数据格式
 */
public class FrontCategoryVoFace{
    /**
     * 主键
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;


    /**
     * 零售价格
     */
    private Double marketPrice;

    /**
     * 图片地址
     */
    private String pictureUrl;

    /**
     * 简介地址
     */
    private String describeUrl;
    private List<FrontSkuVo> skus;

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

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescribeUrl() {
        return describeUrl;
    }

    public void setDescribeUrl(String describeUrl) {
        this.describeUrl = describeUrl;
    }

    public List<FrontSkuVo> getSkus() {
        return skus;
    }

    public void setSkus(List<FrontSkuVo> skus) {
        this.skus = skus;
    }

    public static FrontCategoryVoBody convert(FrontCategoryVoFace face){
        FrontCategoryVoBody body = new FrontCategoryVoBody();
        body.setId(String.valueOf(face.getId()));
        body.setDescribeUrl(face.getDescribeUrl());
        body.setMarketPrice(face.getMarketPrice());
        body.setName(face.getName());
        body.setPictureUrl(face.getPictureUrl());
        body.setSkus(FrontSkuVo.converts(face.getSkus()));
        if (face.getSkus().size()>0){
            body.setMarketPrice(face.getSkus().get(0).getMarketPrice());
        }
        return body;
    }

    public static List<FrontCategoryVoBody> converts(List<FrontCategoryVoFace> source){
        if (source.size()>0){
            List<FrontCategoryVoBody> target = new ArrayList<>(source.size());
            source.forEach(s->{
                target.add(convert(s));
            });
            return target;
        }
        return null;
    }

}

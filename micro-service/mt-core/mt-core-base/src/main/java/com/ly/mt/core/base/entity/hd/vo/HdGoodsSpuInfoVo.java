package com.ly.mt.core.base.entity.hd.vo;

import com.ly.mt.core.base.entity.hd.HdGoodsSpuInfo;
import com.ly.mt.core.base.entity.hd.model.HdGoodsSkuModel;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author panjingtian
 * @description 活动商品spu vo
 * @date 2019/6/14 3:30 PM
 *//** @deprecated */
public class HdGoodsSpuInfoVo {

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

    /**
     * 商品sku集合
     */
    private List<HdGoodsSkuModel> skus;

    public List<HdGoodsSkuModel> getSkus() {
        return skus;
    }

    public void setSkus(List<HdGoodsSkuModel> skus) {
        this.skus = skus;
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

    public HdGoodsSpuInfoVo() {
    }



    /**
     * 将商品spu转换成活动spuvo
     *
     * @param source
     * @return
     */
    public static HdGoodsSpuInfoVo goodsSpuInfoConvertHdGoodsSpuInfoVo(HdGoodsSpuInfo source) {
        HdGoodsSpuInfoVo target = new HdGoodsSpuInfoVo();
        BeanUtils.copyProperties(source, target);
        return target;
    }
}

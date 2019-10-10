package com.ly.mt.core.common.entity.hd.dto;


import com.ly.mt.core.common.entity.hd.model.HdGoodsSkuModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 *  活动sku 接受页面string类型的进行中专的对象
 * @author panjingtian
 * @date 2019/6/15 1:00 PM
 */
@ApiModel("购买商品sku请求体")
public class HdgoodsSkuRequestBody {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "sku主键id")
    private String id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "sku名称")
    private String name;


    /**
     * 商品spuid
     */
    @ApiModelProperty(value = "商品spuid")
    private String spuId;

    /**
     * 1正常使用 2停用
     */
    @ApiModelProperty(value = "sku状态")
    private Integer skuStatus;


    /**
     * 零售价
     */
    @ApiModelProperty(value = "价格")
    private Double marketPrice;

    /**
     * 购买数量
     */
    @ApiModelProperty(value = "购买数量")
    private Integer buyNum;

    /**
     * 前端展示商品id
     */
    @ApiModelProperty(value = "商品展示名称id")
    private String frontId;


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

    public String getFrontId() {
        return frontId;
    }

    public void setFrontId(String frontId) {
        this.frontId = frontId;
    }

    /**
     * 转换成java需要的对象
     * @param body
     * @return
     */
    public static HdGoodsSkuModel convert(HdgoodsSkuRequestBody body){
        HdGoodsSkuModel m = new HdGoodsSkuModel();
        m.setId(Long.valueOf(body.getId()));
        m.setBuyNum(body.getBuyNum());
        m.setFrontId(Long.valueOf(body.getFrontId()));
        m.setMarketPrice(body.getMarketPrice());
        m.setName(body.getName());
        m.setSkuStatus(body.getSkuStatus());
        m.setSpuId(Long.valueOf(body.getFrontId()));
        return m;
    }
}

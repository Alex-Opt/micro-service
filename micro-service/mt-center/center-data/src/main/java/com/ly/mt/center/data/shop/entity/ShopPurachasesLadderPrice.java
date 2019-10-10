package com.ly.mt.center.data.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopPurachasesLadderPrice {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("商品SPU")
    private String spu_id;
    @ApiModelProperty("商品SKU（目前没有用）")
    private String sku_id;
    @ApiModelProperty("进货数量（目前按数量计算）")
    private String purachases_num;
    @ApiModelProperty("进货金额（目前不按金额计算）")
    private String purachases_money;
    @ApiModelProperty("优惠金额（三个优惠是互斥的）")
    private String promotion_money;
    @ApiModelProperty("优惠折扣（三个优惠是互斥的）")
    private String promotion_rate;
    @ApiModelProperty("阶梯价格（三个优惠是互斥的）")
    private String promotion_price;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getPurachases_num() {
        return purachases_num;
    }

    public void setPurachases_num(String purachases_num) {
        this.purachases_num = purachases_num;
    }

    public String getPurachases_money() {
        return purachases_money;
    }

    public void setPurachases_money(String purachases_money) {
        this.purachases_money = purachases_money;
    }

    public String getPromotion_money() {
        return promotion_money;
    }

    public void setPromotion_money(String promotion_money) {
        this.promotion_money = promotion_money;
    }

    public String getPromotion_rate() {
        return promotion_rate;
    }

    public void setPromotion_rate(String promotion_rate) {
        this.promotion_rate = promotion_rate;
    }

    public String getPromotion_price() {
        return promotion_price;
    }

    public void setPromotion_price(String promotion_price) {
        this.promotion_price = promotion_price;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}
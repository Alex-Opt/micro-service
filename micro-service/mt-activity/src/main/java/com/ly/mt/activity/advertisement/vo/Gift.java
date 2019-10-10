package com.ly.mt.activity.advertisement.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//赠品实体类
@ApiModel("赠品实体类")
public class Gift {

    //赠品sku
    @ApiModelProperty("赠品sku")
    private String skuId;


    //赠品商品价格
    @ApiModelProperty("赠品商品单价")
    private String price;


    //sku个数
    @ApiModelProperty("赠品个数")
    private String count;

    @Override
    public String toString() {
        return "Gift{" +
                "skuId='" + skuId + '\'' +
                ", price='" + price + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}

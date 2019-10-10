package com.ly.mt.core.common.entity.buycar;

import com.ly.mt.core.common.entity.goods.GoodsAttrValue;
import com.ly.mt.core.common.entity.goods.GoodsSkuPicture;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车SKU类
 *
 * @author add by ypmu
 * @date 20190520
 */
public class CarSku implements Serializable {

    /**
     * SPU编码
     */
    private String spuId;

    /**
     * SKU编码
     */
    private String skuId;

    /**
     * spu名称
     */
    private String spuName;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 购买数量
     */
    private String num;

    /**
     * 单价
     */
    private String singlePrice;

    /**
     * 合计价格
     */
    private String price;

    /**
     * 是否选中
     */
    private boolean selected;

    private List<GoodsSkuPicture> pictureList;//图片

    /**
     * @Description 商品属性
     */
    private String attributes;

    private List<GoodsAttrValue> AttrValueList;//属性值

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(String singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<GoodsSkuPicture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<GoodsSkuPicture> pictureList) {
        this.pictureList = pictureList;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<GoodsAttrValue> getAttrValueList() {
        return AttrValueList;
    }

    public void setAttrValueList(List<GoodsAttrValue> attrValueList) {
        AttrValueList = attrValueList;
    }

    @Override
    public String toString() {
        return "CarSku{" +
                "spuId='" + spuId + '\'' +
                ", skuId='" + skuId + '\'' +
                ", spuName='" + spuName + '\'' +
                ", skuName='" + skuName + '\'' +
                ", num='" + num + '\'' +
                ", singlePrice='" + singlePrice + '\'' +
                ", price='" + price + '\'' +
                '}';
    }


}

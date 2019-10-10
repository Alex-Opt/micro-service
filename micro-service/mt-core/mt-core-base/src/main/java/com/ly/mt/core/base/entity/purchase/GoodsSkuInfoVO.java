package com.ly.mt.core.base.entity.purchase;

import java.io.Serializable;
/**
 * 商品详情
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-17 23:37:37
 */
/** @deprecated */
public class GoodsSkuInfoVO implements Serializable {

    private static final long serialVersionUID = -3620701106770970573L;

    /**
     * 类目id
     */
    private String cid;

    /**
     * 类目名称
     */
    private String cname;

    /**
     * 商品spu+sku名称
     */
    private String name;

    /**
     * 商品spuId
     */
    private String spuId;

    /**
     * 商品skuId
     */
    private String skuId;

    /**
     * 商品销量
     */
    private String skuNum;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(String skuNum) {
        this.skuNum = skuNum;
    }

    @Override
    public String toString() {
        return "GoodsSkuInfoVO{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", name='" + name + '\'' +
                ", spuId='" + spuId + '\'' +
                ", skuId='" + skuId + '\'' +
                ", skuNum='" + skuNum + '\'' +
                '}';
    }
}

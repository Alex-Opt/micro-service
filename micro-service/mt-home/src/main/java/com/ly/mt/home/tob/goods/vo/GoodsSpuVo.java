package com.ly.mt.home.tob.goods.vo;

import java.util.List;

public class GoodsSpuVo implements Comparable<GoodsSpuVo> {
    private String id;
    private String nameGy;
    private String name;
    private String code;
    private String cid;
    private String brandId;
    private String marketPrice;
    private String wholesalePrice;
    private String weight;
    private String pictureUrl;
    private String pictureUrlC;
    private String describeUrl;
    private String spuAttr;
    private String spuStatus;
    private String dataSource;
    private String createTime;
    private String modifyTime;
    private String remark;
    private List<GoodsPictureVo> pictureList;
    private List<GoodsSpuAttrVo> attr;

    // 购物车sku种类数
    private String buyCarSkuCategoryNum;
    // 促销msg
    private String promotionMsg;
    // 30天销量
    private String saleNum;

    public GoodsSpuVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameGy() {
        return nameGy;
    }

    public void setNameGy(String nameGy) {
        this.nameGy = nameGy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrlC() {
        return pictureUrlC;
    }

    public void setPictureUrlC(String pictureUrlC) {
        this.pictureUrlC = pictureUrlC;
    }

    public String getDescribeUrl() {
        return describeUrl;
    }

    public void setDescribeUrl(String describeUrl) {
        this.describeUrl = describeUrl;
    }

    public String getSpuAttr() {
        return spuAttr;
    }

    public void setSpuAttr(String spuAttr) {
        this.spuAttr = spuAttr;
    }

    public String getSpuStatus() {
        return spuStatus;
    }

    public void setSpuStatus(String spuStatus) {
        this.spuStatus = spuStatus;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<GoodsPictureVo> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<GoodsPictureVo> pictureList) {
        this.pictureList = pictureList;
    }

    public List<GoodsSpuAttrVo> getAttr() {
        return attr;
    }

    public void setAttr(List<GoodsSpuAttrVo> attr) {
        this.attr = attr;
    }

    public String getPromotionMsg() {
        return "累计进货满2～16件，享9.5～6折";
    }

    public void setPromotionMsg(String promotionMsg) {
        this.promotionMsg = promotionMsg;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getBuyCarSkuCategoryNum() {
        return buyCarSkuCategoryNum;
    }

    public void setBuyCarSkuCategoryNum(String buyCarSkuCategoryNum) {
        this.buyCarSkuCategoryNum = buyCarSkuCategoryNum;
    }

    @Override
    public int compareTo(GoodsSpuVo o) {
        return Integer.parseInt(o.getSaleNum()) - Integer.parseInt(this.saleNum);
    }
}

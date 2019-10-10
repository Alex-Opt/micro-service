package com.ly.mt.core.data.goods.entity;

/**
 * goods_spu_info
 *
 * @author taoye
 */
public class GoodsSpuInfo {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品代码
     */
    private String code;
    /**
     * 商品状态
     *
     * @see com.ly.mt.core.base.dict.SpuStatus
     */
    private String spuStatus;
    /**
     * 类目id
     */
    private String cid;
    /**
     * 品牌Id
     */
    private String brandId;
    /**
     * 市场零售价
     */
    private String marketPrice;
    /**
     * 市场批发价
     */
    private String wholesalePrice;
    /**
     * 重量(克)
     */
    private String weight;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifyTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 商品类型
     */
    private String goodsType;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpuStatus() {
        return spuStatus;
    }

    public void setSpuStatus(String spuStatus) {
        this.spuStatus = spuStatus;
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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
}
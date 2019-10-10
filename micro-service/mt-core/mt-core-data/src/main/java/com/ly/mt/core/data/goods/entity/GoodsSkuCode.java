package com.ly.mt.core.data.goods.entity;

/**
 * goods_sku_code
 *
 * @author taoye
 */
public class GoodsSkuCode {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 商品SKU
     */
    private String skuId;
    /**
     * 商品SKU条形码
     */
    private String barCode;
    /**
     * 父标识码
     */
    private String parentCode;
    /**
     * 唯一标识码/追踪码
     */
    private String code;
    /**
     * 验证次数
     */
    private String checkNum;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
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
}

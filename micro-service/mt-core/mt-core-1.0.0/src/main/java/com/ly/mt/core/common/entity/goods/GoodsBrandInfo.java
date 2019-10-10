package com.ly.mt.core.common.entity.goods;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * 品牌信息
 */
public class GoodsBrandInfo extends BaseEntity {

    private String brandName;//品牌名称
    private String brandNameEn;// 品牌英文名称
    private String brandLogoUrl;//品牌logo地址
    private String status;//品牌状态 1：有效， 2：停用
    private String remark;//备注

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandNameEn() {
        return brandNameEn;
    }

    public void setBrandNameEn(String brandNameEn) {
        this.brandNameEn = brandNameEn;
    }

    public String getBrandLogoUrl() {
        return brandLogoUrl;
    }

    public void setBrandLogoUrl(String brandLogoUrl) {
        this.brandLogoUrl = brandLogoUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
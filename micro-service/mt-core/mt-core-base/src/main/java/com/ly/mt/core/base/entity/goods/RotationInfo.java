package com.ly.mt.core.base.entity.goods;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
public class RotationInfo extends BaseEntity {

    /**
     * @Description 图片地址
     */
    private String pictureUrl;
    /**
     * @Description 排序
     */
    private String sortNumber;
    /**
     * @Description 备注
     */
    private String remark;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(String sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
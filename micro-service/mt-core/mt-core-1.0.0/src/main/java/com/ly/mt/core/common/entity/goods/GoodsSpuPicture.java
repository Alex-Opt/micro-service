package com.ly.mt.core.common.entity.goods;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * @Description goods_spu_picture
 * @Author taoye
 */
public class GoodsSpuPicture extends BaseEntity {
    private String spuId;// 商品SPU编号
    private String pictureUrl;// 图片url
    private String sortNumber;// 排序号

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

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
}
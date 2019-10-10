package com.ly.mt.core.common.entity.hd.model;

/**
 * @author panjingtian
 * @description 门店注册结果返回model
 * @date 2019/6/12 4:21 PM
 */
public class ShopRegistResultModel {


    /**
     * 活动图片url，一般是二维码
     */
    private String imgUrl;


    /**
     * -------------------- 其他参数需要的话在补齐 -------------
     */

    public String getImgUrl() {
        return imgUrl;
    }

    private ShopRegistResultModel(Builder builder) {
        this.imgUrl = builder.imgUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String imgUrl;

        public Builder imgUrl(String url) {
            this.imgUrl = url;
            return this;
        }

        public ShopRegistResultModel build() {
            return new ShopRegistResultModel(this);
        }
    }

}

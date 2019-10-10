package com.ly.mt.core.dict;

/**
 * @author zhanglifeng
 * 图片展位图枚举类
 */
public enum PicturePlaceholder {
    PICTURE_PLACEHOLDER_SKU("https://moti-dev.oss-cn-beijing.aliyuncs.com/image/placeholder/placeholderSKU.png","SKU占位图"),
    PICTURE_PLACEHOLDER_SPU("https://moti-dev.oss-cn-beijing.aliyuncs.com/image/placeholder/placeholderSPU.png","首页SPU占位图"),
    PICTURE_PLACEHOLDER_SPU_DETAIL("https://moti-dev.oss-cn-beijing.aliyuncs.com/image/placeholder/placeholderSPUDetail.png","占位图详情页主图"),
    PICTURE_PLACEHOLDER_DEFAULT_AVATAR("https://moti-dev.oss-cn-beijing.aliyuncs.com/image/placeholder/defaultAvatar.png","默认头像占位图"),
    PICTURE_PLACEHOLDER_ORDER_GOODS_DETAIL("https://moti-dev.oss-cn-beijing.aliyuncs.com/image/placeholder/orderDetailGoodsPicture.png","订单详情页商品图");


    private final String id;

    private final String name;

     PicturePlaceholder(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

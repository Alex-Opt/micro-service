package com.ly.mt.core.common.constant;

/**
 * @Description 文件路径枚举类
 * @Author taoye
 */
public enum FilePathEnum {
    FILE_PATH_AVATAR("image/user/avatar", "用户头像图片路径"),
    FILE_PATH("0", "文件路径"),
    FILE_PATH_ROTATION("image/user/rotation", "轮播图路径"),
    FILE_PATH_GOODS_SKU("image/user/goods/sku", "GoodsSku图片路径"),
    FILE_PATH_ACTIVITY("image/shop/activity", "门店活动活动图片路径"),
    FILE_PATH_ACTIVITY_URL("image/activity/shareUrl", "门店活动活动图片路径"),
    GZG_B_CABINET_CHECK_PIC("image/user/cabinet","格子柜校验图片路径"),
    FILE_PATH_SHOP_LICENSES("image/user/shop/licenses", "b端用户营业执照"),
    FILE_PATH_SHOP_IDCARD("image/user/shop/idcard", "b端用户身份信息"),
    FILE_PATH_SHOP_FEEDBAK("image/user/shop/feedbak", "b端用户意见反馈"),
    FILE_PATH_ACTIVITY_ZHULI("image/activity/bluetoothzhuli", "蓝牙助力图片路径");


    private final String path;
    private final String describe;

    FilePathEnum(String path, String describe) {
        this.path = path;
        this.describe = describe;
    }

    public String getPath() {
        return path;
    }
}
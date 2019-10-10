package com.ly.mt.core.base.dict;

/**
 * @Description 文件路径枚举类
 * @Author taoye
 */
public enum AlOssPath {
    AL_OSS_PATH_IMAGE_MALL_H5_AVATAR("image/mall/h5/avatar", "H5商城用户头像图片路径"),
    AL_OSS_PATH_IMAGE_MALL_H5_ROTATION("image/mall/h5/rotation", "H5商城轮播图路径"),
    AL_OSS_PATH_IMAGE_BLUETOOTH_AVATAR("image/bluetooth/avatar", "蓝牙APP-用户头像图片路径"),
    AL_OSS_PATH_IMAGE_WXSHOP_ICON("image/wxshop/logo", "微信店铺门头照片"),
    AL_OSS_PATH_IMAGE_USER_IDCARD_FRONT("image/user/idCard/front", "用户身份证正面图片路径"),
    AL_OSS_PATH_IMAGE_USER_IDCARD_BACK("image/user/idCard/back", "用户身份证背面图片路径"),
    AL_OSS_PATH_IMAGE_REPLENIS_BD("image/cabinet-b/replenish/bd", "BD角色补货图片"),
    AL_OSS_PATH_IMAGE_REPLENIS_OWEN("image/cabinet-b/replenish/owen", "店铺管理员/店铺员工角色补货图片");

    private final String path;
    private final String desc;

    AlOssPath(String path, String desc) {
        this.path = path;
        this.desc = desc;
    }

    public String getPath() {
        return path;
    }
}
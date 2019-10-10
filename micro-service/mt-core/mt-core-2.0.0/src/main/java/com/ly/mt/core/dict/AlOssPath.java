package com.ly.mt.core.dict;

/**
 * @Description 文件路径枚举类
 * @Author taoye
 */
public enum AlOssPath {
    AL_OSS_PATH_IMAGE_MALL_H5_AVATAR("image/mall/h5/avatar", "H5商城用户头像图片路径"),
    AL_OSS_PATH_IMAGE_MALL_H5_ROTATION("image/mall/h5/rotation", "H5商城轮播图路径"),
    AL_OSS_PATH_IMAGE_BLUETOOTH_AVATAR("image/bluetooth/avatar", "蓝牙APP-用户头像图片路径");

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
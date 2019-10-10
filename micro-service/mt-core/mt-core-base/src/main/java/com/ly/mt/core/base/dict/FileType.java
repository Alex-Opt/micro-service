package com.ly.mt.core.base.dict;

/**
 * 文件类型的枚举类
 * @author zhanglifeng
 * @date 2019-09-05
 */
public enum FileType {
    FILE_TYPE_IMAGE_EXT_JPG("jpg","图像的文件类型,后缀为jpg"),
    FILE_TYPE_IMAGE_EXT_JPEG("jpeg","图像的文件类型,后缀为jpg"),
    FILE_TYPE_IMAGE_EXT_BMP("bmp","图像的文件类型,后缀为bmp"),;

    private String id;
    private String name;

    FileType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }}

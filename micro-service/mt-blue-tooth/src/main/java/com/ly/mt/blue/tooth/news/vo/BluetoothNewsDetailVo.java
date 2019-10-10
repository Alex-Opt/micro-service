package com.ly.mt.blue.tooth.news.vo;

/**
 * 蓝牙新闻列表实体
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="新闻详情对象")
public class BluetoothNewsDetailVo {
    @ApiModelProperty(value = "新闻id", required = true)
    private String id;
    @ApiModelProperty(value = "新闻内容", required = true)
    private String newsContent;//新闻内容
    @ApiModelProperty(value = "阅读量", required = true)
    private String readingQuantity;//阅读量
    @ApiModelProperty(value = "文章收藏数量", required = true)
    private String collectTheNumber;//文章收藏数量
    @ApiModelProperty(value = "用户是否收藏 0:未收藏 1:已收藏", required = true)
    private String isCollect;//文章收藏数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getReadingQuantity() {
        return readingQuantity;
    }

    public void setReadingQuantity(String readingQuantity) {
        this.readingQuantity = readingQuantity;
    }

    public String getCollectTheNumber() {
        return collectTheNumber;
    }

    public void setCollectTheNumber(String collectTheNumber) {
        this.collectTheNumber = collectTheNumber;
    }

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }
}
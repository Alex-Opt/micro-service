package com.ly.mt.blue.tooth.news.vo;

/**
 * 蓝牙新闻列表实体
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="新闻列表对象")
public class BluetoothNewsListVo {
    @ApiModelProperty(value = "新闻id", required = true)
    private String id;
    @ApiModelProperty(value = "新闻标题", required = true)
    private String newsTitle;//新闻标题
    @ApiModelProperty(value = "阅读量", required = true)
    private String readingQuantity;//阅读量
    @ApiModelProperty(value = "小图url", required = true)
    private String newsSmallIcon;//小图url

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getReadingQuantity() {
        return readingQuantity;
    }

    public void setReadingQuantity(String readingQuantity) {
        this.readingQuantity = readingQuantity;
    }

    public String getNewsSmallIcon() {
        return newsSmallIcon;
    }

    public void setNewsSmallIcon(String newsSmallIcon) {
        this.newsSmallIcon = newsSmallIcon;
    }
}
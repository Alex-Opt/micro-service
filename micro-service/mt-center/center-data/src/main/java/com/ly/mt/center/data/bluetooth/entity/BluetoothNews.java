package com.ly.mt.center.data.bluetooth.entity;

/**
 * 蓝牙新闻实体
 */
public class BluetoothNews {
    private String id;
    private String news_title;//新闻标题
    private String news_content;//新闻内容
    private String reading_quantity;//阅读量
    private String news_small_icon;//小图url
    private String collect_the_number;//文章收藏数量
    private String create_time;//创建时间
    private String update_time;//修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollect_the_number() {
        return collect_the_number;
    }

    public void setCollect_the_number(String collect_the_number) {
        this.collect_the_number = collect_the_number;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_content() {
        return news_content;
    }

    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }

    public String getReading_quantity() {
        return reading_quantity;
    }

    public void setReading_quantity(String reading_quantity) {
        this.reading_quantity = reading_quantity;
    }

    public String getNews_small_icon() {
        return news_small_icon;
    }

    public void setNews_small_icon(String news_small_icon) {
        this.news_small_icon = news_small_icon;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
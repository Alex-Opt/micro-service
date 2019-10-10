package com.ly.mt.center.data.bluetooth.entity;

/**
 * 蓝牙新闻收藏实体
 */
public class BluetoothNewsCollect {
    private String id;
    private String news_id;//新闻标题
    private String user_id;//新闻内容
    private String create_time;//创建时间
    private String update_time;//修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
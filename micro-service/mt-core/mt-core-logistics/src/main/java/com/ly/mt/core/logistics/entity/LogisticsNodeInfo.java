package com.ly.mt.core.logistics.entity;

/**
 * 物流详细节点信息
 *
 * @author taoye
 */
public class LogisticsNodeInfo {
    /**
     * 内容
     */
    private String context;
    /**
     * 时间 原始格式
     */
    private String time;
    /**
     * 时间 格式化后
     */
    private String ftime;


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }
}
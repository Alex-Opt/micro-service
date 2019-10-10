package com.ly.mt.center.data.cabinet.requestdto;

/**
 * 数据统计请求模型
 */
public class SquareDataStatisticsRequestDto extends BasePageInfoDto{


    /**
     * 用户id
     */
    public Long userId;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     *  例：开始时间，2019-5-1
     */
    private String start_time;

    /**
     * 结束时间
     */
    private String end_time;

    /**
     * cabinet_store 表
     * 店铺表主键id
     */
    private String cabinet_store_id;

    /**
     * 展柜库存数
     */
    private String stock_num;

    public String getStock_num() {
        return stock_num;
    }

    public void setStock_num(String stock_num) {
        this.stock_num = stock_num;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCabinet_store_id() {
        return cabinet_store_id;
    }

    public void setCabinet_store_id(String cabinet_store_id) {
        this.cabinet_store_id = cabinet_store_id;
    }

    public String getFullStartTime(){
        return this.start_time + " 00:00:00";
    }

    public String getFullEndTime(){
        return this.end_time + " 23:59:59";
    }
}

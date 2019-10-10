package com.ly.mt.blue.tooth.log.vo;

/**
 * 蓝牙APP-蓝牙数据DB
 * 申明此对象批量插入/为了不在数据中心进行转换
 * @author WHL
 */
public class BluetoothLogDB {
    private String id;
    private String user_id;
    private String mac_address;//烟弹剩余
    private String time_stamp;//时间戳
    private String power;//功率
    private String remaining_battery;//剩余电量/mh
    private String time;//持续时间(s)
    private String create_time;//持续时间(s)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getRemaining_battery() {
        return remaining_battery;
    }

    public void setRemaining_battery(String remaining_battery) {
        this.remaining_battery = remaining_battery;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
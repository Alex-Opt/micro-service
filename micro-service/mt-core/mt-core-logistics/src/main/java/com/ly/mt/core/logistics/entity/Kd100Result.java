package com.ly.mt.core.logistics.entity;

/**
 * 快递100返参
 *
 * @author taoye
 */
public class Kd100Result {
    /**
     * 消息体，请忽略
     */
    private String message;
    /**
     * 快递单当前状态
     *
     * @see com.ly.mt.core.logistics.dict.Kd100State
     */
    private String state;
    /**
     * 通讯状态，请忽略
     */
    private String status;
    /**
     * 快递单明细状态标记，暂未实现，请忽略
     */
    private String condition;
    /**
     * 是否签收标记，请忽略，明细状态请参考state字段
     */
    private String ischeck;
    /**
     * 快递公司编码,一律用小写字母
     */
    private String com;
    /**
     * 单号
     */
    private String nu;
    /**
     * 最新查询结果
     */
    private String data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
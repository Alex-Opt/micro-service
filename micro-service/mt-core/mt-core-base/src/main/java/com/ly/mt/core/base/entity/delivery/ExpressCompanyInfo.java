package com.ly.mt.core.base.entity.delivery;

/**
 * 物流公司信息表
 * @author zhanglifeng
 * @date 2019-09-19
 */
public class ExpressCompanyInfo {
    private String id;
    /**
     * 快递公司码
     */
    private String code;
    /**
     * 快递公司名称
     */
    private String name;
    /**
     *快递公司所在城市
     */
    private String city;
    /**
     * 使用状态  1：未使用，2：已使用
     */
    private String status;
    /**
     * 创建时间
     */
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

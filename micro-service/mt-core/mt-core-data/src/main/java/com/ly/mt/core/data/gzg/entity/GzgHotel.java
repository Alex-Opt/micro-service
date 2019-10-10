package com.ly.mt.core.data.gzg.entity;

/**
 * gzg_hotel
 */
public class GzgHotel {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 酒店名称
     */
    private String name;
    /**
     * 酒店地址
     */
    private String address;
    /**
     * 省CODE
     */
    private String provinceCode;
    /**
     * 市CODE
     */
    private String cityCode;
    /**
     * 区CODE
     */
    private String area;
    /**
     * 酒店管理员id
     */
    private String hotelAdminId;
    /**
     * BD用户id
     */
    private String bdId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHotelAdminId() {
        return hotelAdminId;
    }

    public void setHotelAdminId(String hotelAdminId) {
        this.hotelAdminId = hotelAdminId;
    }

    public String getBdId() {
        return bdId;
    }

    public void setBdId(String bdId) {
        this.bdId = bdId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
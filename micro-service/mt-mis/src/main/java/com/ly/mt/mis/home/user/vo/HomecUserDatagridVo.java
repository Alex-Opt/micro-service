package com.ly.mt.mis.home.user.vo;

/**
 * HomecUserDatagridVo
 *
 * @author taoye
 */
public class HomecUserDatagridVo {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 性别
     */
    private String sex;
    /**
     * 性别名称
     */
    private String sexName;
    /**
     * 省（直辖市）代码
     */
    private String provinceCode;
    /**
     * 省（直辖市）名称
     */
    private String provinceName;
    /**
     * 省辖市代码
     */
    private String cityCode;
    /**
     * 省辖市名称
     */
    private String cityName;
    /**
     * 县区代码
     */
    private String districtCode;
    /**
     * 县区名称
     */
    private String districtName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 最近订单ID
     */
    private String orderId;
    /**
     * 最近订单时间
     */
    private String orderTime;
    /**
     * 最近订单商品
     */
    private String goodsNames;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getGoodsNames() {
        return goodsNames;
    }

    public void setGoodsNames(String goodsNames) {
        this.goodsNames = goodsNames;
    }
}
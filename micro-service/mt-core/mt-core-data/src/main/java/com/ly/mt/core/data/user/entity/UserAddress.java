package com.ly.mt.core.data.user.entity;

/**
 * user_address
 *
 * @author taoye
 */
public class UserAddress {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 收货人姓名
     */
    private String receiveName;
    /**
     * 收货人电话
     */
    private String receivePhone;
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
     * 地址经度
     */
    private String lon;
    /**
     * 地址维度
     */
    private String lat;
    /**
     * 前端定位显示的定位值
     */
    private String pickerValue;
    /**
     * 详细地址
     */
    private String userAddress;
    /**
     * 是否默认地址
     */
    private String isDefault;
    /**
     * 有效状态
     *
     * @see com.ly.mt.core.base.dict.ValidStatus
     */
    private String validStatus;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
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

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getPickerValue() {
        return pickerValue;
    }

    public void setPickerValue(String pickerValue) {
        this.pickerValue = pickerValue;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}

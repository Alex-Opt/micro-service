package com.ly.mt.core.common.entity.YHHDHttpResponse;


/**
 * 三要素检测返回值dto
 * @Wanglong
 */
public class ThreeEleDetectionResponseDto {
    //状态码
    private Integer code;
    //返回的提示信息
    private String message;
    //流水号
    private String serialNo;
    /**
     * 返回结果  0 未知 1 一致  -1 证件姓名 不一致
     * -2 证件一致  姓名不一致  -3 证件不一致  姓名一致
     * -4 不一致但匹配项未知
     */
    private Integer data;

    public ThreeEleDetectionResponseDto() {}


    public ThreeEleDetectionResponseDto(Integer code, String message, String serialNo, Integer data) {
        this.code = code;
        this.message = message;
        this.serialNo = serialNo;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}

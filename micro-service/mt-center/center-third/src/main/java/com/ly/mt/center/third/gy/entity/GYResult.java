package com.ly.mt.center.third.gy.entity;

/**
 * @Description 管易接口响应参数
 * @Author taoye
 */
public class GYResult {
    // 成功与否
    private boolean success;
    // 错误代码
    private String errorCode;
    // 子错误代码
    private String subErrorCode;
    // 错误描述
    private String errorDesc;
    // 子错误描述
    private String subErrorDesc;
    // 请求方法
    private String requestMethod;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getSubErrorCode() {
        return subErrorCode;
    }

    public void setSubErrorCode(String subErrorCode) {
        this.subErrorCode = subErrorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getSubErrorDesc() {
        return subErrorDesc;
    }

    public void setSubErrorDesc(String subErrorDesc) {
        this.subErrorDesc = subErrorDesc;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Override
    public String toString() {
        return "GYResult{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", subErrorCode='" + subErrorCode + '\'' +
                ", errorDesc='" + errorDesc + '\'' +
                ", subErrorDesc='" + subErrorDesc + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                '}';
    }
}
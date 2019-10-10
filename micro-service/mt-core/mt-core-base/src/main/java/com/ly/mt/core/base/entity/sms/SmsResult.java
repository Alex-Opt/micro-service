package com.ly.mt.core.base.entity.sms;
/** @deprecated */
public class SmsResult {
    /**
     * @Description 发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态
     */
    private String BizId;
    /**
     * @Description 请求状态码 返回OK代表请求成功。
     */
    private String Code;
    /**
     * @Description 状态码的描述
     */
    private String Message;
    /**
     * @Description 请求ID
     */
    private String RequestId;

    public String getBizId() {
        return BizId;
    }

    public void setBizId(String bizId) {
        BizId = bizId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }
}
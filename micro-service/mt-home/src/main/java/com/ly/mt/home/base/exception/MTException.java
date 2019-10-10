package com.ly.mt.home.base.exception;

import com.ly.mt.core.base.entity.ResponseCode;
import com.netflix.hystrix.exception.HystrixBadRequestException;

// TODO: 2019/9/10 Exception Enum
/**
 * 业务异常
 *
 * @author: linan
 * @date: 2019/8/26
 **/
public class MTException extends HystrixBadRequestException {

    private String code;

    private String message;

    public MTException() {
        this("This is blank message");
    }

    public MTException(String msg) {
        this(ResponseCode.RESPONSE_CODE_ERROR.getCode(), msg);
    }

    public MTException(String code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }

    public MTException(ResponseCode responseCode) {
        this(responseCode.getCode(), responseCode.getMsg());
    }

    public MTException(ResponseCode responseCode, String msg) {
        this(responseCode.getCode(), msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.ly.mt.core.base.entity.hd.common;


import com.ly.mt.core.base.entity.ResponseCode;
/**
 * @description
 * 活动模块封装统一结果集vv
 * @author panjingtian
 * @date 2019/6/14 1:22 AM
 */
/** @deprecated */
public class HdResultVo<T> {


    /**
     * 结果代码
     */
    private ResponseCode resultCodeEnum;

    /**
     * 数据对象
     */
    private T t;

    public HdResultVo() {
    }

    public HdResultVo(ResponseCode resultCodeEnum, T t) {
        this.resultCodeEnum = resultCodeEnum;
        this.t = t;
    }

    public ResponseCode getResultCodeEnum() {
        return resultCodeEnum;
    }

    public void setResultCodeEnum(ResponseCode resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

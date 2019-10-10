package com.ly.mt.core.common.entity.hd.common;


import com.ly.mt.core.common.constant.ResultCodeEnum;

/**
 * @description
 * 活动模块封装统一结果集vv
 * @author panjingtian
 * @date 2019/6/14 1:22 AM
 */
public class HdResultVo<T> {


    /**
     * 结果代码
     */
    private ResultCodeEnum resultCodeEnum;

    /**
     * 数据对象
     */
    private T t;

    public HdResultVo() {
    }

    public HdResultVo(ResultCodeEnum resultCodeEnum, T t) {
        this.resultCodeEnum = resultCodeEnum;
        this.t = t;
    }

    public ResultCodeEnum getResultCodeEnum() {
        return resultCodeEnum;
    }

    public void setResultCodeEnum(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

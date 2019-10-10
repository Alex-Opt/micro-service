package com.ly.mt.core.base.dict;

/**
 *@Description  收益日志状态枚举
 *@Author  zhuyh
 */
public enum ShopProfitsLogStatus {
    /**
     * 待结算
     */
    NORMAL(1),
    /**
     * 正常
     */
    SUC(2),
    /**
     * 取消
     */
    CANCEL(3)
    ;
    private Integer id;

    ShopProfitsLogStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

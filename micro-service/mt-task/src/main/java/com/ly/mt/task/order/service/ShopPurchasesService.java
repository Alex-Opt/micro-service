package com.ly.mt.task.order.service;

public interface ShopPurchasesService {
    /**
     * 更新完成状态
     * @return
     */
    void updCompleteStatus();

    /**
     * 更新取消状态
     * @return
     */
    void updCancelStatus();

}
package com.ly.mt.core.data.shop.dao;

import com.ly.mt.core.data.shop.entity.ShopPurchases;

import java.util.List;

/**
 * ShopPurchases操作接口
 *
 * @author taoye
 */
public interface ShopPurchasesDao {
    /**
     * 更新ShopPurchases,id不能为空
     *
     * @param shopPurchases 更新数据
     * @author taoye
     */
    void updateShopPurchases(ShopPurchases shopPurchases);

    /**
     * 自动更新完成状态
     *
     * @author taoye
     */
    void updateFinishStatus(String time);

    /**
     * 自动更新取消状态
     *
     * @author taoye
     */
    void updateCancelStatus(List<String> ids);

    /**
     * 专用方法，需优化
     *
     * @author taoye
     * @deprecated
     */
    List<ShopPurchases> getPurchasesList();

    /**
     * 专用方法，需优化
     *
     * @author taoye
     * @deprecated
     */
    List<ShopPurchases> getShopPurchasesForTask(String time);

}
package com.ly.mt.core.data.shop.mapper;

import com.ly.mt.core.data.shop.entity.ShopPurchases;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ShopPurchases操作接口
 *
 * @author taoye
 */
@Mapper
public interface ShopPurchasesMapper {
    /**
     * 更新ShopPurchases
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
     * @deprecated
     */
    List<ShopPurchases> getPurchasesList();


    /**
     * 获取更新商家订单集合
     * @param time
     * @author linan
     */
    List<ShopPurchases> getShopPurchasesForTask(String time);

}
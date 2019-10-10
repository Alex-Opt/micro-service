package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopPurchasesItems;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopPurchasesItemsMapper {
    /**
     * @Description 保存ShopPurchasesItems
     * @Author taoye
     */
    void insertShopPurchasesItems(ShopPurchasesItems shopPurchasesItems);

    /**
     * @Description 删除ShopPurchasesItems
     * @Author taoye
     */
    void deleteShopPurchasesItems(ShopPurchasesItems shopPurchasesItems);

    /**
     * @Description 更新ShopPurchasesItems
     * @Author taoye
     */
    int updateShopPurchasesItems(ShopPurchasesItems shopPurchasesItems);

    /**
     * @Description 查询ShopPurchasesItems
     * @Author taoye
     */
    ShopPurchasesItems getShopPurchasesItems(ShopPurchasesItems shopPurchasesItems);

    /**
     * 获取进货单商品列表
     * @param shoupPurchasesId
     * @return
     */
    List<Map<String,Object>> getItemList(long shoupPurchasesId);

    /**
     * @Description 查询商家累计进货数
     * @Author linan
     */
    String getItemsNum(ShopPurchasesItems shopPurchasesItems);

    /**
     * @Description 查询spu销量
     * @Author linan
     */
    String getSpuNum(ShopPurchasesItems shopPurchasesItems);
}
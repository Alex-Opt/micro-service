package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopPurachasesLadderPrice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopPurachasesLadderPriceMapper {
    /**
     * @Description 保存ShopPurachasesLadderPrice
     * @Author taoye
     */
    void insertShopPurachasesLadderPrice(ShopPurachasesLadderPrice shopPurachasesLadderPrice);

    /**
     * @Description 删除ShopPurachasesLadderPrice
     * @Author taoye
     */
    void deleteShopPurachasesLadderPrice(ShopPurachasesLadderPrice shopPurachasesLadderPrice);

    /**
     * @Description 更新ShopPurachasesLadderPrice
     * @Author taoye
     */
    int updateShopPurachasesLadderPrice(ShopPurachasesLadderPrice shopPurachasesLadderPrice);

    /**
     * @Description 查询ShopPurachasesLadderPrice
     * @Author taoye
     */
    ShopPurachasesLadderPrice getShopPurachasesLadderPrice(ShopPurachasesLadderPrice shopPurachasesLadderPrice);

    /**
     * @Description 查询ShopPurachasesLadderPrice list
     * @Author linan
     */
    List<ShopPurachasesLadderPrice> getLadderList();
}
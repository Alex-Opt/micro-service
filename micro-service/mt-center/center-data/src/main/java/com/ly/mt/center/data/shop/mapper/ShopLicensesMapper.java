package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopLicenses;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopLicensesMapper {
    /**
     * @Description 保存ShopLicenses
     * @Author taoye
     */
    void insertShopLicenses(ShopLicenses shopLicenses);

    /**
     * @Description 删除ShopLicenses
     * @Author taoye
     */
    void deleteShopLicenses(ShopLicenses shopLicenses);

    /**
     * @Description 更新ShopLicenses
     * @Author taoye
     */
    int updateShopLicenses(ShopLicenses shopLicenses);

    /**
     * @Description 查询ShopLicenses
     * @Author taoye
     */
    ShopLicenses getShopLicenses(ShopLicenses shopLicenses);
}
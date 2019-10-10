package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopAddressMapper {
    /**
     * @Description 插入ShopAddress
     * @Author taoye
     */
    void insertShopAddress(ShopAddress shopAddress);

    /**
     * @Description 根据id删除ShopAddress
     * @Author taoye
     */
    void deleteShopAddressById(ShopAddress shopAddress);

    /**
     * @Description 根据id更新ShopAddress
     * @Author taoye
     */
    int updateShopAddressById(ShopAddress shopAddress);

    /**
     * @Description 根据条件查询ShopAddress
     * @Author taoye
     */
    ShopAddress getShopAddress(ShopAddress shopAddress);

    /**
     * @Description 根据id查询ShopAddress
     * @Author taoye
     */
    ShopAddress getShopAddressById(ShopAddress shopAddress);

    /**
     * @Description 根据shop_id查询店铺默认的ShopAddress
     * @Author zhanglfeng
     */
    ShopAddress getShopDefaultAddressByShopId(Long shop_id);

    /**
     * @Description 根据条件查询ShopAddress
     * @Author linan
     */
    List<ShopAddress> getShopAddressList(ShopAddress shopAddress);

}
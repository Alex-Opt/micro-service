package com.ly.mt.home.tob.address.service;

import com.ly.mt.home.tob.address.vo.ShopAddressVo;

import java.util.List;

/**
 * 商家收货地址接口类
 *
 * @author: linan
 * @date: 2019/7/17
 */
public interface ShopAddressService {

    /**
     * 根据id查询商家地址
     *
     * @param id
     * @return
     */
    ShopAddressVo getShopAddressById(String id);

    /**
     * 根据商家id查询地址
     *
     * @return
     */
    List<ShopAddressVo> getShopAddressList();

    /**
     * 获取商家默认收货地址
     *
     * @return
     */
    ShopAddressVo getDefaultShopAddress();

    /**
     * 添加收货地址
     *
     * @param shopAddressVo
     */
    void addShopAddress(ShopAddressVo shopAddressVo);

    /**
     * 添加收货地址
     *
     * @param shopAddressVo
     */
    void updateShopAddress(ShopAddressVo shopAddressVo);

    /**
     * 删除收货地址
     *
     * @param id
     */
    void deleteShopAddress(String id);
}

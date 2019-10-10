package com.ly.mt.core.data.user.dao;

import com.ly.mt.core.data.user.entity.UserAddress;

/**
 * UserAddress操作接口
 *
 * @author taoye
 */
public interface UserAddressDao {
    /**
     * 从redis根据id查询UserAddress
     * redis不存在则查询mysql
     *
     * @param id 地址ID
     * @return UserAddress
     * @author taoye
     */
    UserAddress getUserAddressByIdFromRedis(String id);
}
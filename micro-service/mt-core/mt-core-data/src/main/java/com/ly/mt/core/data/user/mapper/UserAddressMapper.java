package com.ly.mt.core.data.user.mapper;

import com.ly.mt.core.data.user.entity.UserAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserAddress操作接口
 *
 * @author taoye
 */
@Mapper
public interface UserAddressMapper {
    /**
     * 查询UserAddress
     *
     * @param userAddress 查询条件
     * @return UserAddress
     * @author taoye
     */
    UserAddress getUserAddress(UserAddress userAddress);
}
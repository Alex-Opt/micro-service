package com.ly.mt.order.server.user.mapper;

import com.ly.mt.core.base.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserAddressMapper {
    UserAddress getUserAddress(@Param("addressId") Long addressId);
}

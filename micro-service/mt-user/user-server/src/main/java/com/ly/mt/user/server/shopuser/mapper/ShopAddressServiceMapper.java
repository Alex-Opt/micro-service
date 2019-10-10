package com.ly.mt.user.server.shopuser.mapper;

import com.ly.mt.core.common.entity.shop.ShopAddress;
import org.apache.ibatis.annotations.Param;

public interface ShopAddressServiceMapper {

    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(ShopAddress record);

    //int insertSelective(shopAddress record);

    ShopAddress selectByPrimaryKey(@Param("id")Long id);

    int updateByPrimaryKeySelective(ShopAddress record);

    int updateByPrimaryKey(ShopAddress record);
}
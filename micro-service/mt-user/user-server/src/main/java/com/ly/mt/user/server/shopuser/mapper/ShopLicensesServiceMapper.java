package com.ly.mt.user.server.shopuser.mapper;


import com.ly.mt.core.common.entity.shop.ShopLicenses;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShopLicensesServiceMapper {

    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(ShopLicenses record);

    int insertSelective(ShopLicenses record);


    ShopLicenses selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(ShopLicenses record);

    int updateByPrimaryKey(ShopLicenses record);
}
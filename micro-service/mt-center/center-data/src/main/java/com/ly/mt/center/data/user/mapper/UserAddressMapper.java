package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAddressMapper {
    /**
     * @Description 插入UserAddress
     * @Author taoye
     */
    void insertUserAddress(UserAddress userAddress);

    /**
     * @Description 根据id删除UserAddress
     * @Author taoye
     */
    void deleteUserAddressById(UserAddress userAddress);

    /**
     * @Description 根据id更新UserAddress
     * @Author taoye
     */
    int updateUserAddressById(UserAddress userAddress);

    /**
     * @Description 根据条件查询UserAddress
     * @Author taoye
     */
    UserAddress getUserAddress(UserAddress userAddress);

    /**
     * @Description 根据id查询UserAddress
     * @Author taoye
     */
    UserAddress getUserAddressById(Long id);

    /**
     * 根据userId获取用户默认收货地址
     * @param userAddress
     * @return
     */
    UserAddress  getDefaultUserAddressByUserId(UserAddress userAddress);

    /**
     * 根据userId获取用户收货地址(分页)
     * @param userAddress
     * @return
     */
    List<UserAddress> getUserAddressByUserId(UserAddress userAddress);

    /**
     * 根据条件，修改用户收货地址数据
     * @param userAddress
     * @return
     */
    int updateUserAddress(UserAddress userAddress);

   /* *//**
     * 设置用户收货地址，是否为默认收货地址
     * @param userAddress
     * @return
     *//*
    int updateUserAddressDefaultByUserId(UserAddress userAddress);

    *//**
     * 根据id,设置收货地址是否是默认收货地址
     * @param userAddress
     * @return
     *//*
    int updateUserAddressDefaultById(UserAddress userAddress);

    *//**
     * 根据id,修改收货地址状态
     * @param userAddress
     * @return
     *//*
    int updateValidStatusById(UserAddress userAddress);*/
}
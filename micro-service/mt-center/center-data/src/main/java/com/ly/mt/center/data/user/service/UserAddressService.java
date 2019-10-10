package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface UserAddressService {
    /**
     * @Description 插入UserAddress
     * @Author taoye
     */
    ResponseJson insertUserAddress(JSONObject jsonObject);

    /**
     * @Description 根据id删除UserAddress
     * @Author taoye
     */
    ResponseJson deleteUserAddressById(JSONObject jsonObject);

    /**
     * @Description 根据id更新UserAddress
     * @Author taoye
     */
    ResponseJson updateUserAddressById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询UserAddress
     * @Author taoye
     */
    ResponseJson getUserAddress(JSONObject jsonObject);

    /**
     * @Description 根据id查询UserAddress
     * @Author taoye
     */
    ResponseJson getUserAddressById(JSONObject jsonObject);

    /**
     * 根据userId查询用户的默认收货地址
     * @param jsonObject
     * @return
     */
    ResponseJson getDefaultUserAddressByUserId(JSONObject jsonObject);

    /**
     * 根据userId查询用户收货地址数据（分页）
     * @param jsonObject
     * @return
     */
    ResponseJson getUserAddressByUserId(JSONObject jsonObject);

  /*  *//**
     * 设置用户收货地址，是否为默认收货地址
     * @param jsonObject
     * @return
     *//*
    ResponseJson updateUserAddressDefaultByUserId(JSONObject jsonObject);

    *//**
     * 根据id,设置收货地址是否是默认收货地址
     * @param jsonObject
     * @return
     *//*
    ResponseJson updateUserAddressDefaultById(JSONObject jsonObject);

    *//**
     * 根据id修改收货地址状态
     * @param jsonObject
     * @return
     *//*
    ResponseJson updateValidStatusById(JSONObject jsonObject);
*/
    /**
     * 根据条件，修改用户收货地址数据
     * @param jsonObject
     * @return
     */
    ResponseJson updateUserAddress(JSONObject jsonObject);
}
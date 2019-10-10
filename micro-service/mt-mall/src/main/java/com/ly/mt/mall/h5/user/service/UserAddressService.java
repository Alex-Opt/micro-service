package com.ly.mt.mall.h5.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @author zhanglifeng
 * @description 用户中心-收货地址管理
 * @date 2019-05-20
 */
public interface UserAddressService {

    /**
     * 根据用户的姓名，电话，收货地址，收货地址详情保存用户新增的一条地址
     *
     * @param jsonObject 入参
     * @return
     * @throws Exception
     */
    ResponseJson saveAddress(String jsonObject) throws Exception;

    /**
     * 根据用户id查询用户的地址列表
     *
     * @return
     * @throws Exception
     */
    ResponseJson listAddress(int page, int rows) throws Exception;

    /**
     * 根据用户id设置地址对应的id的状态设置为默认地址
     *
     * @param id 入参
     * @return
     * @throws Exception
     */
    ResponseJson defaultAddress(String id) throws Exception;

    /**
     * 根据用户id修改对应addressId的地址信息
     *
     * @param jsonObject 入参
     * @return
     * @throws Exception
     */
    ResponseJson updateAddress(String jsonObject) throws Exception;

    /**
     * 根据用户id,地址id删除该地址信息
     *
     * @param id 入参
     * @return
     * @throws Exception
     */
    ResponseJson deleteAddress(String id) throws Exception;

    /**
     * 根据用户id获取用户的默认地址
     *
     * @return
     * @throws Exception
     */
    ResponseJson getDefaultAddressByUserId() throws Exception;

    /**
     * 根据addressId查询一条记录
     * @param id
     * @return
     * @throws Exception
     */
    ResponseJson getAddressById(String id) throws  Exception;
}

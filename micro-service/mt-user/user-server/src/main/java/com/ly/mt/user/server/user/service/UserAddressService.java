package com.ly.mt.user.server.user.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhanglifeng
 * @description 用户中心-收货地址管理
 * @date 2019-05-20
 */
public interface UserAddressService {

    /**
     * 根据用户的姓名，电话，收货地址，收货地址详情保存用户新增的一条地址
     *
     * @param json 入参
     * @return
     * @throws Exception
     */
    JSONObject saveAddress(String json) throws Exception;

    /**
     * 根据用户id查询用户的地址列表
     *
     * @return
     * @throws Exception
     */
    JSONObject listAddress(String json) throws Exception;

    /**
     * 根据用户id设置地址对应的id的状态设置为默认地址
     *
     * @param json 入参
     * @return
     * @throws Exception
     */
    JSONObject defaultAddress(String json) throws Exception;

    /**
     * 根据用户id修改对应addressId的地址信息
     *
     * @param json 入参
     * @return
     * @throws Exception
     */
    JSONObject updateAddress(String json) throws Exception;

    /**
     * 根据用户id,地址id删除该地址信息
     *
     * @param json 入参
     * @return
     * @throws Exception
     */
    JSONObject deleteAddress(String json) throws Exception;

    /**
     * 根据用户id获取用户的默认地址
     *
     * @return
     * @throws Exception
     */
    JSONObject getDefaultAddressByUserId(String json) throws Exception;

    /**
     * 根据addressId查询一条记录
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getAddressById(String json) throws  Exception;
}

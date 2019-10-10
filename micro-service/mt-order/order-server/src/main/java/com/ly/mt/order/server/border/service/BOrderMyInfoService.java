package com.ly.mt.order.server.border.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 我的模块
 * @author zhanglifeng
 * @date 2019-06-13
 */
public interface BOrderMyInfoService {


    /**
     * 查询商家进货订单列表接口，单条就是查询明细
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getShopPurchaseList(String json) throws Exception;

    /**
     * 查询商家进货库存信息
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getShopPurchaseStack(String json) throws Exception;

    /**
     * 查询我的优惠券信息
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getMyShopCouponInfo(String json) throws Exception;


    /**
     * 获取店铺默认地址接口
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getShopDefaultAddress(String json) throws Exception;

    /**
     * 根据Id获取店铺地址接口
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getShopAddressById(String json) throws Exception;

    /**
     * 根据Id修改店铺地址接口
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject updateShopAddress(String json) throws Exception;

    /**
     *修改用户名接口
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject updateLoginName(String json) throws Exception;


    /**
     * 查询用户账户信息接口
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject userAccountInfo(String json) throws Exception;

    /**
     * 查询店铺地址列表接口
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getShopAddressList(String json) throws Exception;


    /**
     * 新增店铺地址接口
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject addShopAddress(String json) throws Exception;


    /**
     * 修改店铺默认地址接口
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject updateDefaultShopAddress(String json) throws Exception;

    /**
     * 更换手机号接口
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject modifyMobile(String json) throws Exception;


}

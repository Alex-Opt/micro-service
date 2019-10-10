package com.ly.mt.activity.server.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.hd.model.HdShopAttDetailModel;
import com.ly.mt.core.common.entity.hd.model.ShopRegistResultModel;

/**
 * @author panjingtian
 * @description B端
 * 活动门店负责人操作接口
 * 门店负责人登录、退出等
 * @date 2019/6/10 3:07 PM
 */
public interface ShopManagerOperationServer {


    /**
     * 门店活动注册手机号验证
     * 同时也验证活动有没有发布过
     *
     * @param phone           手机号
     * @param dynamicCodeType 短信模版代码
     * @param activityId      活动id
     * @param flag            是否可以重复注册 0为不可以 1为可以
     * @return
     */
    JSONObject sendActivityDynamicCode(JSONObject jsonObject);

    /**
     * 门店注册
     *
     * @param jsonObject hdShopAttDetailModel
     * @return {@link ShopRegistResultModel}
     */
    JSONObject registShopActivity(JSONObject jsonObject) throws Exception;

    /**
     * 活动管理员登录
     *
     * @param jsonObject phone dynamicCode
     */
    JSONObject login(JSONObject jsonObject);

    /**
     * 发送登录验证码
     *
     * @param jsonObject
     * @return
     */
    JSONObject sendLoginCode(JSONObject jsonObject);

    /**
     * 活动管理员登出
     *
     * @param man {@link HdShopAttDetailModel}
     */
    @Deprecated
    void logout(HdShopAttDetailModel man);

    /**
     * 进入商店，获取商品信息
     * @param shopId
     * @param activityId
     */
    //HdshopAttPageVo intoShop(Integer shopId, Integer activityId);

    /**
     * 查询活动信息
     *
     * @param jsonObject integer  shopAttDetailId 活动表主键id
     * @return
     */
    JSONObject findAttDetailById(JSONObject jsonObject);

    /**
     * 展示当前门店活动的商品
     *
     * @param jsonObject Integer activityid 活动id
     *                   Integer shopid  门店id
     * @return 当前门店商品列表
     */
    JSONObject showProducts(JSONObject jsonObject);

    /**
     * 查询制定代理商门店
     *
     * @param jsonObject integer  operatorId 代理商主键
     * @return
     */
    JSONObject findShopByOperator(JSONObject jsonObject);

    /**
     * 获取门店注册信息id
     * integer shopId
     * integer activityId
     *
     * @return
     */
    JSONObject getAttdetailId(JSONObject jsonObject);

    /**
     * 查询所有的代理商
     * @param jsonObject
     * @return
     */
    JSONObject findOperator(JSONObject jsonObject);

    /**
     * 查询门店负责人，做校验账号用
     * @param jsonObject key=phone
     * @return
     */
    JSONObject findManager(JSONObject jsonObject);

    /**
     * 添加代理商
     * @param jsonObject
     * @return
     */
    JSONObject addOperator(JSONObject jsonObject);

    /**
     * 添加门店
     */
    JSONObject addShop(JSONObject jsonObject);

}

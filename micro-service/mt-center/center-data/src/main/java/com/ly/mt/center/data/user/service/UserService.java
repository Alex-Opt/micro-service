package com.ly.mt.center.data.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.cache.annotation.Cacheable;

public interface UserService {
    /**
     * @Description 插入User
     * @Author taoye
     */
    ResponseJson insertUser(JSONObject jsonObject);

    /**
     * @Description 根据id删除User
     * @Author taoye
     */
    ResponseJson deleteUser(JSONObject jsonObject);

    /**
     * @Description 根据id更新User
     * @Author taoye
     */
    ResponseJson updateUser(JSONObject jsonObject);

    /**
     * @Description 根据id更新User
     * @Author WHL
     */
    ResponseJson updateUserById(JSONObject jsonObject);

    /**
     * @Description 根据id查询User
     * @Author taoye
     */
//    @Cacheable(value="User", key = "#id")
    ResponseJson getUserById(JSONObject jsonObject);

    /**
     * @Description 蓝牙-根据条件查询User
     * @Author taoye
     */
    ResponseJson getUserByCondtions(JSONObject jsonObject);

    /**
     * @Description 根据mobile查询User
     * @Author taoye
     */
    ResponseJson getUserByMobile(JSONObject jsonObject);

    /**getUserByMobileForActivity
     * @Description 根据mobile查询User
     * @Author wanglong
     */
    ResponseJson getUserByMobileForActivity(JSONObject jsonObject);

    /**
     * 根据用户手机号查询用户信息，查不到时返回code:0,result:null
     * @param jsonObject
     * @return
     */
    ResponseJson getLoginUserByMobile(JSONObject jsonObject);

    /**
     * @Description 根据login_name查询User
     * @Author taoye
     */
//    @Cacheable(value="User", key = "#login_name")
    ResponseJson getUserByLoginName(JSONObject jsonObject);

    /**
     * 设置密码
     * @param jsonObject
     * @return
     */
    ResponseJson setPassword(JSONObject jsonObject);

    /**
     * 根据openId查询微信小程序的用户是否绑定微信
     * @param jsonObject
     * @return
     */
    ResponseJson getUserByWxOpenId(JSONObject jsonObject);
}
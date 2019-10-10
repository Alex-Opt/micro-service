package com.ly.mt.activity.server.user.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @description
 * 活动用户操作
 * @author panjingtian
 * @date 2019/6/14 1:45 PM
 */
public interface HdShopUserService {


     /**
      * 注册活动用户
      * 1、首先验证下是否是商城的用户了
      * 2、验证下是否已经参加过这个活动了
      * 3、没有的话注册成商城c端用户，然后在注册商活动用户
      *   HdActivityUserDto
      *
      * @return 注册用户
      * HdActivityUser
      */

     JSONObject registAndOrder(JSONObject jsonObject);


     /**
      *  买家下单验证码
      * @param jsonObject
      * integer activityId
      * integer shopId
      * integer shopAttDetailId
      * string phone
      *  //user_status控制状态
      * @return 反正吗
      */
     JSONObject sendCode(JSONObject jsonObject);

     /**
      * 获取用户信息
      * hdShopAttDetailId
      * phone
      * @param jsonObject
      * @return
      */
     JSONObject getUserByAttIdPhone(JSONObject jsonObject);

}
